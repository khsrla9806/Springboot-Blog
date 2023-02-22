package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        // System.out.println("save 호출됨");
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바 오브젝트를 JSON 으로 변경해서 응답을 보내줌
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.update(user);

        // 여기서 UserService의 update 함수가 끝나고, 트랜잭션이 종료되면서 DB에 값이 수정된다.
        // 하지만 세션값은 아직 변경되지 않았기 때문에 우리가 직접 세션값을 변경해줘야 한다.

        // 직접 authenticationManager을 가지고 Authentication 객체를 만들어준다.
        // 이때는 UsernamePasswordAuthenticationToken이 필요하기 때문에 user의 username과 password가 필요하다.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        // 이제 위에서 만들어준 Authentication 객체를 시큐리티 컨텍스트에 직접 넣어주면 세션이 변경될 것이다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    // /auth/loginProc는 여기에 만들지 않을 것이다. 로그인은 스프링 시큐리티가 중간에 가로채갈 것이기 때문에
}


// 아래는 스프링의 전통적인 로그인 방식
// 지금은 사용되지 않는다. Spring Security 사용
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("login 호출됨");
//        User principal = userService.login(user); // principal(접근주체)
//
//        // 로그인 세션 만들기
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
