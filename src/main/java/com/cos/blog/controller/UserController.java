package com.cos.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



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

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(@RequestParam String code) { // 데이터를 담아서 리턴해주는 컨트롤러

        // POST 방식으로 key:value 타입의 데이터를 요청해야 한다.
        // 이때 필요한 라이브러리가 RestTemplate라는 라이브러리가 존재한다.
        // Http 요청을 간단하게 할 수 있도록 도와준다.
        RestTemplate rt = new RestTemplate();

        // (HttpHeader 생성) Http 헤더를 만들어서 Content-type을 넣어줘야 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // (HttpBody 생성) 데이터를 담을 Map을 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "800387c83eaadaa3fd019eb469e7245d");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        // 데이터와 헤더를 가지고 있는 HttpEntity를 생성한다.
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // (POST 요청 진행) 위에서 만든 데이터와 헤더로 Http 요청을 진행한다.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", // 요청하는 주소
                HttpMethod.POST,                           // 요청 메서드 방식
                kakaoTokenRequest,                         // 보내는 데이터와 헤더 정보
                String.class                               // 응답받으려는 데이터의 타입
        );

        return "카카오 토큰 요청에 대한 응답 : " + response;
    }
}
