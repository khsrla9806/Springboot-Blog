package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping({"","/"}) // 아무것도 안 붙였을 때랑 /를 붙였을 때 작동
    public String index() {
        // /WEB-INF/views/index.jsp 찾아감
        return "index";
    }
}