package com.cos.blog.test;

public class TestMember {
    private int id;
    private String username;
    private String password;
    private String email;

    // Java 에서는 필드를 모두 private 으로 만든다.
    // 변수에 Direct 로 접근해서 값을 접근하는 것은 객체지향과 맞지 않기 때문에
    // 그 변수에 알맞는 메서드나 행동이 있어야 한다. 다이렉트 접근은 마법을 부리는 것과 마찬가지


    public TestMember(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
