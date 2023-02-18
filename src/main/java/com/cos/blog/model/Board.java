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
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터를 다룰 때 사용한다.
    private String content; // 섬머노트 라이브러리를 사용할 것이다 -> <html> 태그가 섞여서 디자인 된다.

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many(Board) to One(User)
    @JoinColumn(name = "userId") // userId라는 이름으로 DB에 필드가 생성됨
    private User user; // DB는 오브젝트를 사용할 수 없어서 fk를 사용하는데, ORM을 쓰면 오브젝트를 사용해도 된다.

    @CreationTimestamp
    private Timestamp createDate;


    // @OneToOne : 하나에 하나만 매칭됨
    // @OneToMany : 여러개가 하나에 매칭됨
}
