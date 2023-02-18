package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/user/{id}") // 주소로 파라미터를 전달받을 수 있다.
    public User detail(@PathVariable long id) {
        // user의 4번을 찾았을 때 DB에서 못찾으면 Null이 되기 때문에 return이 null이 된다.
        // 그래서 JPA에서는 Optional 객체로 리턴을 해주고, 그걸 null인지 아닌지 판단해서 return 하면 된다.
        // Optional에는 그냥 객체를 가져오는 get()이 있고, 만약 null 이라면 Supplier를 통해서 get을 정의할 수 있는 orElseGet()이 있다.
        // JPA에서 권장하는 방법은 orElseThrow()를 써서 IllegalArgumentException을 던지는 것
        // 람다식을 사용해도 됨
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("찾는 아이디에 해당하는 사용자는 없습니다 id : " + id);
        });

        // 웹브라우저에 응답으로 user 객체를 리턴하고 있다.
        // json 변환 후 응답해야 웹브라우저가 이해할 수 있다 (MessageConverter 역할)
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
