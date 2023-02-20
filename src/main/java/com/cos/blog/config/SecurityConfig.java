package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 빈으로 등록
@EnableWebSecurity // Security 필터가 등록이 된다. 그 설정을 이 클래스에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소를 접근하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()                        // 인증 요청이 들어왔을 때
                .antMatchers("/auth/**")    // 해당 URL 은
                .permitAll()                            // 모두 인증 없이도 허가해주겠다.
                .anyRequest()                           // 그 이외에 다른 요청들은
                .authenticated()                        // 인증이 되어야돼
            .and()
                .formLogin()
                .loginPage("/auth/loginForm");          // 인증이 필요한 요청이 들어오면 /auth/loginForm 으로 보내줘

        return http.build();
    }
}
