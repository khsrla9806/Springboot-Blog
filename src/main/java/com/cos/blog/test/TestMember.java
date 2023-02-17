package com.cos.blog.test;

import lombok.*;

import java.lang.reflect.Member;

//@Getter
//@Setter
//@RequiredArgsConstructor // final 붙은 필드에 대한 생성자를 만들어준다.
//@AllArgsConstructor // 전체 필드에 대한 생성자가 생성된다.
@Data // Getter + Setter
@NoArgsConstructor // 비어있는 기본 생성자
public class TestMember {
    private int id;
    private String username;
    private String password;
    private String email;

    // Java 에서는 필드를 모두 private 으로 만든다.
    // 변수에 Direct 로 접근해서 값을 접근하는 것은 객체지향과 맞지 않기 때문에
    // 그 변수에 알맞는 메서드나 행동이 있어야 한다. 다이렉트 접근은 마법을 부리는 것과 마찬가지


    // AllArgsConstructor 와 동일한 기능을 한다. 하지만 @Builder 를 사용하면?
    // Builder 패턴의 장점
    // 데이터를 넣을 때 순서를 지키지 않아도 된다.
    // 생성자를 통해서 넣을 때는 반드시 id, username, password, email 순서를 지켜서 넣어야 한다.
    @Builder
    public TestMember(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
