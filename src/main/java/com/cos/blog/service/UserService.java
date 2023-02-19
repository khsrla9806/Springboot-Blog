package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // 스프링이 컴포넌트 스캔을 통해서 빈으로 등록해줌
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional // 회원가입 전체 서비스가 하나의 트랜잭션이 된다.
    public int join(User user) {
        try {
            userRepository.save(user);
            return 1; // 정상이면 1을 리턴
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService join() : " + e.getMessage());
        }
        return -1; // 비정상이면 -1을 리턴
    }
}
