package com.example.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberResponse {
    private Long memberNo;
    private String id;
    private String userName;
    private String auth;


    public MemberResponse(Long memberNo, String id, String userName, String auth){
        this.memberNo = memberNo;
        this.id = id;
        this.userName = userName;
        this.auth = auth;

    }
}
