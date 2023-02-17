package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 파일을 리턴하기 위해서 Controller 를 사용
@Controller
public class TempControllerTest {
    // http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        // 스프링 파일 기본 경로 : src/main/resources/static
        System.out.println("Temp Home");

        // 리턴명에 /를 붙여서 보내줘야 풀 네임으로 요청이 가능
        // src/main/resources/static/home.html 이렇게
        return "/home.html";

        // 하지만 스프링에서는 JSP 파일에 대한 지원을 해주지 않는다. 그래서 의존성에서 JSP 템플릿에 대한 의존성을 추가해준 것
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        // JSP 는 자바 파일로서 컴파일이 필요한 동적파일이기 떄문에 인식하지 못한다.
        // 새로운 경로의 설정이 필요하다.
        // blog/src/main 아래에 다음과 같은 디렉토리를 생성 webapp/WEN-INF/views
        // 그리고 나서 application.yml 설정을 진행
        // prefix : /WEB-INF/views
        // suffix : .jsp
        // 풀 경로는 이렇게 됨 -> /WEB-INF/views/{메서드에서 리턴되는 String 값}.jsp
        return "test";
    }
    // 이제 test.jsp 가 들어오면 아파치에서 처리를 하지 못하기 때문에 톰켓이 나서서 jsp 파일을 웹 브라우저가 이해할 수 있는 html 파일로 바꿔서 준다.
}
