package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 예외가 발생하면 이 클래스로 들어오게 만든다.
@RestController
public class GlobalExceptionHandler {

    // 스프링이 IllegalArgumentException 발생하면 이 메서드로 보내준다.
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException exception) {
        return "<h1>" + exception.getMessage() + "</h1>";
    }
}
