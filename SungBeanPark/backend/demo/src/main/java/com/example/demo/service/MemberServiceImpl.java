package com.example.demo.service;

import com.example.demo.controller.request.MemberRequest;
import com.example.demo.entity.member.Member;
import com.example.demo.entity.member.MemberAuth;
import com.example.demo.repository.MemberAuthRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.cart.CartRepository;
import com.example.demo.repository.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberAuthRepository memberAuthRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public Member register(MemberRequest memberRequest) {
        Optional<Member> maybeMember = memberRepository.findByUserId(memberRequest.getId());
        if (!maybeMember.equals(Optional.empty())) {
            log.info("아이디 중복!");

            return null;
        } else {
            String encodedPassword = passwordEncoder.encode(memberRequest.getPw());
            memberRequest.setPw(encodedPassword);

            MemberAuth authEntity = new MemberAuth(memberRequest.getAuth());

            Member memberEntity = new Member(
                    memberRequest.getId(),
                    memberRequest.getPw(),
                    memberRequest.getUserName()
                    );

            memberRepository.save(memberEntity);

            memberEntity.addAuth(authEntity);


            return memberEntity;
        }
    }

    @Transactional
    @Override
    public MemberRequest login(MemberRequest memberRequest) {
        Optional<Member> maybeMember = memberRepository.findByUserId(memberRequest.getId());

        if(maybeMember.equals(Optional.empty())) {
            log.info("아이디 없음");
            return null;
        }

        Member loginMember = maybeMember.get();



        if(!passwordEncoder.matches(memberRequest.getPw(), loginMember.getPassword())) {
            log.info("패스워드가 일치하지 않음");
            return null;
        }

        Optional<MemberAuth> maybeMemberAuth = memberAuthRepository.findByMemberNo(loginMember.getMemberNo());

        if(maybeMemberAuth == null) {
            log.info("no auth");
            return null;
        }

        MemberAuth memberAuth = maybeMemberAuth.get();

        if(loginMember.getId().equals(memberRequest.getId())){
            memberRequest.setUserName(loginMember.getUserName());
            memberRequest.setMemberNo(loginMember.getMemberNo());
            memberRequest.setAuth(memberAuth.getAuth());
        }

        MemberRequest response = new MemberRequest(
                memberRequest.getMemberNo(), memberRequest.getId(),null, memberRequest.getUserName(), memberRequest.getAuth());
        log.info("info response" + response);
        return response;
    }

    @Override
    public Member read(Long memberNo) {
        Optional<Member> maybeReadMember = memberRepository.findById(Long.valueOf(memberNo));

        if (maybeReadMember.equals(Optional.empty())){

            return null;
        }
        log.info("info " + maybeReadMember);
        return maybeReadMember.get();
    }

    @Override
    public Member modify(Long memberNo, Member member) {

        member.setMemberNo(Long.valueOf(memberNo));
        memberRepository.save(member);

        return member;
    }

    @Override
    public void remove(Long memberNo) {
        memberRepository.deleteById(Long.valueOf(memberNo));
    }

    @Override
    public List<Member> list() {
        return memberRepository.findAll(Sort.by(Sort.Direction.DESC,"memberNo"));
    }


    @Override
    public List<Member> searchName(String keyWord) {
        List<Member> searchResults = memberRepository.findByUserName(keyWord);

        return searchResults;
    }




}

