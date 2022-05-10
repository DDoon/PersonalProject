package com.example.demo.controller.member;

import com.example.demo.controller.request.CartRequest;
import com.example.demo.controller.request.MemberRequest;
import com.example.demo.controller.request.MemberSearchRequest;
import com.example.demo.entity.Cart.Cart;
import com.example.demo.entity.member.Member;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Member")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders ="*")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public Member registerMember(@Validated @RequestBody MemberRequest memberRequest) {
        log.info("MemberRegister():" +
                memberRequest.getId() + ", " +
                memberRequest.getUserName() + ", " +
                memberRequest.getPw() + "," + memberRequest.getAuth());
        return memberService.register(memberRequest);
    }


    @PostMapping("/login")
    public MemberRequest memberLogin (@RequestBody MemberRequest memberRequest) {
        log.info("MemberLogin()" + memberRequest);

        MemberRequest memberResponse = memberService.login(memberRequest);

        if(memberResponse != null) {
            log.info("성공!");
        }else{
            log.info("실패!");
        }

        return memberResponse;
    }


    @GetMapping("/{memberNo}")
    public Member read (
            @PathVariable("memberNo") Long memberNo) {
        log.info("read()" + memberNo);

        return memberService.read(memberNo);
    }

    @PutMapping("/modify/{memberNo}")
    public Member memberModify(@PathVariable("memberNo") Long memberNo,
                                       @RequestBody Member member) {
        log.info("memberModify :");

        return memberService.modify(memberNo, member);
    }

    @DeleteMapping("/{memberNo}")
    public void memberRemove (
            @PathVariable("memberNo") Long memberNo) {
        log.info("memberRemove()");

        memberService.remove(memberNo);
    }

    @GetMapping("/list")
    public List<Member> memberList () {
        log.info("memberList()");

        return memberService.list();
    }

    @PostMapping("/search")
    public List<Member> search(@RequestBody MemberSearchRequest memberSearchRequest){
        log.info("search()" + memberSearchRequest);
        String keyWord = memberSearchRequest.getKeyWord();
        return memberService.searchName(keyWord);

    }



}
