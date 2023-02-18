package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll(); // 전체 사용자가 모두 리턴된다.
    }

    // 한 페이지당 2건의 사용자를 리턴받는 메서드
    @GetMapping("/dummy/user")
    // http://localhost:8000/blog/dummy/user?page=0 (0은 첫 번째 페이지, 1은 그 다음 페이지)
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // 데이터 사이즈(size)는 2건씩 정렬은 id로 하는데, 최신순이기 때문에 DESC 내림차순 정렬
        return userRepository.findAll(pageable).getContent(); // Pageable.getContent()를 하면 List 반환
        
        // Page<User>로 반환하면 밑에 여러 다른 데이터들을 볼 수 있다.
        // Page<User> pagingUser = userRepository.findAll(pageable);
        // Pageable 다양한 메서드를 활용하고 싶다면 getContent()로 바로 리턴하는 것이 아니라 한번 거치는 것이 좋다.
        // 첫 번째 데이터인지, 마지막 데이터인지 이런 기능들을 쓸 수 있다.
    }

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
