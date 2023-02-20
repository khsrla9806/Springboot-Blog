package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 나누기 위해서 /auth 추가
// 그냥 주소가 /이면 index.jsp 로 가는 것도 인증 안된 사용자가 출입하는 것을 허용
// static 이하에 있는 /js/** 와 /css/** 와 /image/** 도 모두 허용된다.
@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}
