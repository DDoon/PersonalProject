package com.example.demo.entity.like;

import com.example.demo.entity.member.Member;
import com.example.demo.entity.product.ProductComment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likesId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_No")
    private Member member;

    @JsonIgnoreProperties({"likes", "member"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_No")
    private ProductComment productComment;
}
