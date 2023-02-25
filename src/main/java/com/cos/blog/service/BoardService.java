package com.cos.blog.service;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepositroy;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepositroy replyRepositroy;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void write(Board board, User user) { // title, content
        board.setCount(0);
        board.setUser(user); // controller로 부터 가져온 User를 넣어준다. (글쓰기를 요청한 유저)
        boardRepository.save(board);
    }

    public Page<Board> findAllBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true) // SELECT만 하기 때문에 readOnly 설정
    public Board detail(int id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("찾는 게시글은 없습니다. : " + id);
        });
    }

    @Transactional
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Board requestBoard) {
        // 영속성 컨텍스트에 DB 데이터를 먼저 영속화 시켜야 한다.
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("해당 아이디 값을 가지는 게시글은 존재하지 않습니다. : " + id);
        });

        // 수정된 내용을 적용
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());

        // 서비스가 종료될 때, 더티체킹이 일어나고 DB에 수정내용을 flush 진행하여 DB에 적용 (Transactional 어노테이션 필수)
    }

    @Transactional
    public void makeReply(ReplySaveRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(() -> {
            throw new NotFoundException("댓글 작성을 위한 게시글 정보를 찾을 수 없습니다.");
        });

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> {
            throw new NotFoundException("작성자 정보를 찾을 수 없습니다.");
        });

        Reply reply = Reply.builder()
                .user(user)
                .content(requestDto.getContent())
                .board(board)
                .build();

        replyRepositroy.save(reply);
    }

}
