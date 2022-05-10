package com.example.demo.service.like;


import com.example.demo.entity.like.Likes;

import java.util.List;

public interface LikesService {
    public boolean addToLike (Long commentNo, Long memberNo, Likes likes);


    List<Likes> list (Long commentNo, Long memberNo);
}
