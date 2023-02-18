package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert 할 때 null 값은 제외 시켜서 insert 할 수 있다. -> 기능은 좋지만 많이 사용하지 않는 것이 좋다.
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

    //@ColumnDefault("'user'") // ColumnDefault 사용시 ''를 사용해서 넣어줘야 한다. (문자라는 것을 알려주기 위해서)
    @Enumerated(EnumType.STRING) // DB에는 RoleType이라는 것은 없기에 DB에 EnumType은 스트링이라고 알려줘야 한다.
    private RoleType role; // ADMIN, USER (도메인 지정)

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;

}
