package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER) // Many(Board) to One(User) // FetchType.EAGER 기본전략 : Board 호출하면 User도 같이 줘
    @JoinColumn(name = "userId") // userId라는 이름으로 DB에 필드가 생성됨
    private User user; // DB는 오브젝트를 사용할 수 없어서 fk를 사용하는데, ORM을 쓰면 오브젝트를 사용해도 된다.

    // 이것을 설정해줘야지 Board select 할때 연관된 Reply를 같이 Join해서 줌
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy는 나는 연관관계의 주인이 아니다 -> 내 주인은 Reply 클래스에 있는 "board"이다.
    // @JoinColumn: 하지만 제 1정규형을 지키기 위해서 굳이 Board 테이블에 필드를 생성해줄 필요가 없다.
    @JsonIgnoreProperties({"board"}) // 무한 참조를 막기위한 방법 : reply를 호출할 때, reply가 가지고 있는 board는 더이상 참조되지 않는다.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;


    // @OneToOne : 하나에 하나만 매칭됨
    // @OneToMany : 여러개가 하나에 매칭됨
    // EAGER 전략 : 너가 Board 요청할 때 User도 같이 보내줘 바로 필요하니깐
    // Lazy 전략 : 너가 Board 요청할 때 바로 주진 않아도돼 필요할 때 보내줘
}
