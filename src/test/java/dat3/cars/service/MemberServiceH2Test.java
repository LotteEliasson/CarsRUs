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

//doc for @DataJpaTest https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html
@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

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
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember savex a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        MemberResponse response
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        //TODO
    }

    @Test
    void testEditMemberWithExistingUsername() {
        //TODO
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        //TODO
    }

    @Test
    void testSetRankingForUser() {
      memberService.setRankingForUser("user1",3);
      assertEquals(3, m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        //TODO
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user2");
        assertFalse(memberRepository.existsById("user2"));

    }

    @Test
    void testDeleteMember_ThatDontExist() {
      memberService.deleteMemberByUsername("user3");

    }
}

