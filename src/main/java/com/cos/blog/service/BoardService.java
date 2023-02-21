package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user) { // title, content
        board.setCount(0);
        board.setUser(user); // controller로 부터 가져온 User를 넣어준다. (글쓰기를 요청한 유저)
        boardRepository.save(board);
    }
}
