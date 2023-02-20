package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 빈으로 등록
@EnableWebSecurity // Security 필터가 등록이 된다. 그 설정을 이 클래스에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소를 접근하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig {

    @Bean // 이 메소드가 리턴되는 값을 IoC로 만들어준다.
    public BCryptPasswordEncoder encodePWD() {
        // 스프링 시큐리티가 가지고 있는 클래스이다.
        // 비밀번호를 해쉬화 시켜주는 역할을 한다. ("1234" => 고정길이 문자열로 암호화)
        // String encPassword = new BCryptPasswordEncoder().encode("1234");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // csrf 토큰 비활성화 테스트할 때는 걸어주는 것이 좋음
            .authorizeRequests()                                                        // 인증 요청이 들어왔을 때
                .antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")  // 해당 URL 은
                .permitAll()                                                            // 모두 인증 없이도 허가해주겠다.
                .anyRequest()                                                           // 그 이외에 다른 요청들은
                .authenticated()                                                        // 인증이 되어야돼
            .and()
                .formLogin()
                .loginPage("/auth/loginForm");              // 인증이 필요한 요청이 들어오면 /auth/loginForm 으로 보내줘

        return http.build();
    }
}
