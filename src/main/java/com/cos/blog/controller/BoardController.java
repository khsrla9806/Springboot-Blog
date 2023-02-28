package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping({"","/"}) // 아무것도 안 붙였을 때랑 /를 붙였을 때 작동
    public String index(Model model, @PageableDefault(size=3, sort="id", direction= Sort.Direction.DESC)Pageable pageable) {
        // jsp에서 사용할 수 있는 데이터를 들고 갈 때는 Model을 사용한다.
        // model의 addAttribute로 모든 글 목록을 넣어서 index.jsp로 가져간다.
        model.addAttribute("boards", boardService.findAllBoard(pageable)); // Page타입을 index.jsp로 넘김
        // /WEB-INF/views/index.jsp 찾아감
        return "index"; // viewResolver가 작동됨
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        return "board/detail";
    }

    // User 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        return "board/updateForm";
    }
}

      //@AuthenticationPrincipal 을 이용해서 세션에 접근할 수 있다.
//    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
//        // /WEB-INF/views/index.jsp 찾아감
//        System.out.println("로그인 사용자 아이디: " + principal.getUsername());
//        return "index";
//    }