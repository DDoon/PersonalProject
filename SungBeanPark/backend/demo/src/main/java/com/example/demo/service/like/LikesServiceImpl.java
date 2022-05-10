package com.example.demo.service.like;

import com.example.demo.entity.like.Likes;
import com.example.demo.entity.member.Member;
import com.example.demo.entity.product.ProductComment;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.like.LikesRepository;
import com.example.demo.repository.product.ProductCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductCommentRepository productCommentRepository;

    @Autowired
    LikesRepository likesRepository;

    @Override
    public boolean addToLike(Long commentNo, Long memberNo, Likes likes) {
        ProductComment productComment = productCommentRepository.findById(commentNo).orElseThrow();
        Member member = memberRepository.findById(memberNo).orElseThrow();

        if(likesRepository.findByMemberAndProductComment( member,productComment).isEmpty()) {
            likes.setMember(member);
            likes.setProductComment(productComment);
            likesRepository.save(likes);
            return true;

        } else {
            Optional<Likes> maybeLike = likesRepository.findByMemberAndProductComment(member,productComment);
            likesRepository.deleteById(maybeLike.get().getLikesId());
            return false;
        }
    }


    @Override
    public List<Likes> list(Long commentNo, Long memberNo) {
        return likesRepository.findByCommentNoAndMemberNo(Long.valueOf(commentNo), Long.valueOf(memberNo));
    }



}
