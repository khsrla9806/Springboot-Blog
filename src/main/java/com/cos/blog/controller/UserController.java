package com.cos.blog.controller;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 나누기 위해서 /auth 추가
// 그냥 주소가 /이면 index.jsp 로 가는 것도 인증 안된 사용자가 출입하는 것을 허용
// static 이하에 있는 /js/** 와 /css/** 와 /image/** 도 모두 허용된다.
@Controller
@RequiredArgsConstructor
public class UserController {

    @Value("${cos.key}")
    private String cosKey; // 이 키는 중요한 키기 때문에 절대 노출되면 안된다.

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

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
    public String kakaoCallback(@RequestParam String code) { // 데이터를 담아서 리턴해주는 컨트롤러

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

        // 위에서 받은 ResponseEntity의 데이터를 그대로 직접만든 OAuthToken에 넣는 작업을 진행
        // 이때는 여러 라이브러리의 도움을 받을 수 있다. Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException exception) {
            // 파싱할 때, OAuthToken 오브젝트의 필드 값과 response.getBody()의 데이터 이름이 다르면 오류 발생
            exception.printStackTrace();
        } catch (JsonProcessingException exception) {
            // 파싱할 때, OAuthToken에 Getter/Setter가 없으면 오류가 발생할 수 있다.
            exception.printStackTrace();
        }

        // 사용자 정보 요청하기
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+ oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me", // 요청하는 주소
                HttpMethod.POST,                           // 요청 메서드 방식
                kakaoProfileRequest,                         // 보내는 데이터와 헤더 정보
                String.class                               // 응답받으려는 데이터의 타입
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException exception) {
            // 파싱할 때, OAuthToken 오브젝트의 필드 값과 response.getBody()의 데이터 이름이 다르면 오류 발생
            exception.printStackTrace();
        } catch (JsonProcessingException exception) {
            // 파싱할 때, OAuthToken에 Getter/Setter가 없으면 오류가 발생할 수 있다.
            exception.printStackTrace();
        }

        // User 오브젝트 : username, email, password
        // 카카오 유저는 비밀번호가 따로 없기 때문에 UUID를 사용해서 임의의 비밀번호를 사용

        // 카카오 정보를 가지고 우리 블로그 서버에 회원가입
        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .email(kakaoProfile.getKakao_account().getEmail())
                .password(cosKey)
                .oauth("kakao")
                .build();

        // 가입자 혹은 비가입자 확인하는 로직
        User originUser = userService.findUser(kakaoUser.getUsername());

        if (originUser.getUsername() == null) {
            System.out.println("기존회원이 아닙니다.");
            userService.join(kakaoUser);
        }

        // 로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
