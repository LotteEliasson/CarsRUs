package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//DataJpaTest,Disable full autoconfig og starter kun hvad relevant for test,
// Roll Back af alt inkl DB efter hver test,
// In memory DB her h2,
//doc for @DataJpaTest https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html
@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2, m3;  //Talk about references in Java for why we don't add the "isInitialize flag"
    //Laver 2 testmembers der bliver oprettet i h2 databasen, der bliver anvendt i forb med alle test.
    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2,memberResponses.size(),"Expects 2 members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time, "Expects dates to be set when true is passed");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        assertEquals(2,memberResponses.size(),"Expects 2 members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNull(time, "Expects dates not to be set when false is passed");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse res = memberService.findById("user1");
        assertEquals("user1", res.getUsername());
        assertEquals("email1", res.getEmail());
    }

    @Test
    void testFindByIdNotFound() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, ()-> memberService.findById("I dont exist"));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember savex a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        m3 = memberRepository.save(new Member("newUser", "newPw", "newMail", "newfn", "newln",  "newStreet", "newCity", "newZip"));
        memberService = new MemberService(memberRepository);
        assertEquals("newUser", m3.getUsername());
        assertEquals("newMail", m3.getEmail());
        assertEquals("newStreet", m3.getStreet());
        //etc

    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        MemberRequest memberRequest = new MemberRequest("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2");
        //memberService bruger memberRepository (injected into memberService), som er @Autowired for at tilgå databasen (ved test h2).
        // Her er memberRepository så simpelt at det anvendes i test, ellers ville man have lavet et mock-rep.
        memberService = new MemberService(memberRepository);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> memberService.addMember(memberRequest));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());

    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest request = new MemberRequest(m1);
        request.setFirstName("newName");
        request.setLastName("newLast");

        memberService.editMember(request, "user1");

        //tester at member findes, testen virker selvom denne udelades.
        MemberResponse response = memberService.findById("user1");

        assertEquals("user1", m1.getUsername());
        assertEquals("newName", m1.getFirstName());
        assertEquals("newLast", m1.getLastName());

    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
       MemberRequest request = new MemberRequest();
       ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> memberService.editMember(request,"member"));
       assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest();
        request.setUsername("user1");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> memberService.addMember(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testSetRankingForUser() {
      memberService.setRankingForUser("user1",3);
      assertEquals(3, m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> memberService.setRankingForUser("member",4));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user2");
        assertFalse(memberRepository.existsById("user2"));

    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> memberService.deleteMemberByUsername("member"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}

