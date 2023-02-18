package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // DAO는 자동으로 Bean 등록이 되어서 생략 가능
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository는 User 테이블을 관리하고, Long이 pk 타입니다.
}
