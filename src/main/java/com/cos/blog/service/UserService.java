package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 스프링이 컴포넌트 스캔을 통해서 빈으로 등록해줌
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(User user) {
        userRepository.save(user); // 여기서 예외가 발생되면 GlobalExceptionHandler 로 가게 되어있다.
    }

    @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작, 서비스 종료시 종료 (정합성을 유지)
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
