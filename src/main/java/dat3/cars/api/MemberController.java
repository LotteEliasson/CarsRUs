package dat3.cars.api;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    //RestController konverterer andre 'sprog' til Java via JSON eksv sql.
    @RestController
    @RequestMapping("api/members")
    public class MemberController {

    MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

        //Security Admin only
        //Gennem service layer laver man et styret memberResponse.
        @GetMapping
        List<MemberResponse> getMembers(){
            return memberService.getMembers(false);
        }


        //Security Admin
        @GetMapping(path = "/{username}")
        MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.findById(username);
        }


        //Security --> anonymous
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        MemberResponse addMember(@RequestBody MemberRequest body){
            return memberService.addMember(body);
        }


        //Security Admin
        @PutMapping("/{username}")
        ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
            return memberService.editMember(body,username);
        }

        //Security Admin
        @PatchMapping("/ranking/{username}/{value}")
        void setRankingForUser(@PathVariable String username, @PathVariable int value) {
        memberService.setRankingForUser(username, value);
        }

        // Security Admin
        @DeleteMapping("/{username}")
        void deleteMemberByUsername(@PathVariable String username) {
        memberService.deleteMemberByUsername(username);
        }


    }


