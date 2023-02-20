package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 빈으로 등록 (실제로 로그인을 해주는 애)
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username, password 를 가로채는데
    // password 부분 처리는 알아서 한다. (password 가 틀리면 이건 알아서 처리해준다.)
    // 해당 username 이 DB에 있는지만 확인해주면 된다. (아래 함수에서 해당 username 이 존재하는지 확인해야 한다.)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
        });
        return new PrincipalDetail(principal); // 이때 시큐리티 세션에 유저 정보가 저장이 된다.
    }
}
