package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

// 사용자가 요청 -> 응답 (html 파일)을 하고 싶을 때는 @Controller 사용

// 사용자가 요청 -> 응답 (데이터)
@RestController
public class HttpControllerTest {
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(TestMember m) { // ?id=1&username=kim&password=1234&email=kim@naver.com 을 Member 에 넣어줌 (스프링)
        return "GET 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post") // application/json MIME type
    public String postTest(@RequestBody TestMember m) { // json 타입의 데이터를 받아서 Member 에 넣어줌 (MessageConverter 에서 자동 파싱)
        return "POST 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody TestMember m) { // 스프링에서는 오브젝트로 매핑해서 받을 수 있다.
        return "PUT 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "DELETE 요청";
    }

    @GetMapping("/http/lombok/builder")
    public String builderTest(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        TestMember m = TestMember.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        return "lombok Test 완료 : ," + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail() + ", " + m.getId();
    }
}
