package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("save 호출됨");
        // TODO: 실제로 DB에 insert 하고 아래에서 리턴이 되도록 작성하면 됨.
        return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바 오브젝트를 JSON 으로 변경해서 응답을 보내줌
    }
}
