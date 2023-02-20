package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // DAO는 자동으로 Bean 등록이 되어서 생략 가능
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository는 User 테이블을 관리하고, Long이 pk 타입니다.
}


// login을 위한 함수
// JPA 네이밍 쿼리를 사용 (첫 번째 방법)
// SELECT * FROM user WHERE username = ? AND password = ? 이 자동으로 실행이 됨
// User findByUsernameAndPassword(String username, String password);

// 네이티브 쿼리를 사용 (두 번째 방법)
// @Query(value = "SELECT * FROM user WHERE username=? AND password=?", nativeQuery = true)
// User login(String username, String password);