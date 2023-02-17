package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답 (html 파일)을 하고 싶을 때는 @Controller 사용

// 사용자가 요청 -> 응답 (데이터)
@RestController
public class HttpControllerTest {
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest() {
        return "GET 요청";
    }

    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest() {
        return "POST 요청";
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest() {
        return "PUT 요청";
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "DELETE 요청";
    }
}
