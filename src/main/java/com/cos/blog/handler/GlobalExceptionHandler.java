package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 예외가 발생하면 이 클래스로 들어오게 만든다.
@RestController
public class GlobalExceptionHandler {

    // 스프링이 IllegalArgumentException 발생하면 이 메서드로 보내준다.
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleArgumentException(Exception exception) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
