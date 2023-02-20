package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 빈으로 등록
@EnableWebSecurity // Security 필터가 등록이 된다. 그 설정을 이 클래스에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소를 접근하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig {
    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean // 이 메소드가 리턴되는 값을 IoC로 만들어준다.
    public BCryptPasswordEncoder encodePWD() {
        // 스프링 시큐리티가 가지고 있는 클래스이다.
        // 비밀번호를 해쉬화 시켜주는 역할을 한다. ("1234" => 고정길이 문자열로 암호화)
        // String encPassword = new BCryptPasswordEncoder().encode("1234");
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인을 할때 password 를 가로채기 하는데
    // 해당 password 가 뭘로 해쉬가 되어 회원가입 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // principalDetailService 를 통해서 로그인을 진행할 때, 비밀번호 암호화를 하는데 사용했던 방법을 알려줘야 한다.
        // 우리는 encodePWD()를 사용해서 암호화를 했기 때문에 아래와 같이 알려주면 된다.
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
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
                .loginPage("/auth/loginForm")              // 인증이 필요한 요청이 들어오면 /auth/loginForm 으로 보내줘
                .loginProcessingUrl("/auth/loginProc")     // 스프링 시큐리티가 해당 주소로 요청하는 로그인을 가로채고, 대신해줌
                .defaultSuccessUrl("/");                   // 로그인이 성공하면 여기로 보내줘.

        return http.build();
    }


}
