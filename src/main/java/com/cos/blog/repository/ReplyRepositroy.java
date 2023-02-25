package com.cos.blog.repository;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReplyRepositroy extends JpaRepository<Reply, Integer> {

    @Modifying // Native Query에서 Insert를 할 때, 붙여줘야 한다.
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int customSave(long userId, int boardId, String content); // ?1, ?2, ?3에 대한 값을 매핑시켜준다.
}
