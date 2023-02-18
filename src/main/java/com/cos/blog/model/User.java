package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 스프링 부트가 실행되면 자동으로 MySQL Table 생성
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB 넘버링 전략을 따라간다. (auto increment)
    private long id;

    @Column(nullable = false, length = 30) // null 없음
    private String username; // 회원 아이디

    @Column(nullable = false, length = 100) // 넉넉하게 주는 이유 -> 해쉬로 암호화하기 위해서
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'") // ColumnDefault 사용시 ''를 사용해서 넣어줘야 한다. (문자라는 것을 알려주기 위해서)
    private String role; // 정확하게 하기 위해서는 Enum 사용이 좋다. (Enum 사용시 도메인(범위) 설정이 가능)

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;

}
