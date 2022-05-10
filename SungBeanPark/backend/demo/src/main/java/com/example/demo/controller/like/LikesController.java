package com.example.demo.controller.like;

import com.example.demo.entity.like.Likes;
import com.example.demo.service.like.LikesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @PostMapping("/{commentNo}/{memberNo}")
    public boolean likes(@PathVariable("commentNo") Long commentNo, @PathVariable("memberNo") Long memberNo, @Validated @RequestBody Likes likes) {
        log.info("likes()");
        return likesService.addToLike(commentNo, memberNo, likes);
    }

    @GetMapping("/list/{commentNo}/{memberNo}")
    public List<Likes> likesList(@PathVariable("commentNo") Long commentNo, @PathVariable("memberNo") Long memberNo) {
        log.info("likesList()");

        return likesService.list(commentNo, memberNo);
    }
}