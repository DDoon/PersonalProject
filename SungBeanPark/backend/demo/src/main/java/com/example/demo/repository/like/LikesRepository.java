package com.example.demo.repository.like;

import com.example.demo.entity.like.Likes;
import com.example.demo.entity.member.Member;
import com.example.demo.entity.product.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberAndProductComment (Member member, ProductComment productComment);

    @Query(value = "SELECT * FROM like WHERE comment_No in(SELECT comment_No FROM productComment WHERE comment_No = :commentNo) AND member_no in(SELECT member_no FROM member WHERE member_no = :memberNo)", nativeQuery = true)
    List<Likes> findByCommentNoAndMemberNo(@Param("commentNo") Long commentNo, @Param("memberNo") Long memberNo);

    Optional<Likes> findByProductComment (ProductComment productComment);


}
