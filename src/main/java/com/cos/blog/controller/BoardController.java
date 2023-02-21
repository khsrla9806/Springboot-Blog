package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping({"","/"}) // 아무것도 안 붙였을 때랑 /를 붙였을 때 작동
    public String index() {
        // /WEB-INF/views/index.jsp 찾아감
        return "index";
    }

    // User 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}

      //@AuthenticationPrincipal 을 이용해서 세션에 접근할 수 있다.
//    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
//        // /WEB-INF/views/index.jsp 찾아감
//        System.out.println("로그인 사용자 아이디: " + principal.getUsername());
//        return "index";
//    }