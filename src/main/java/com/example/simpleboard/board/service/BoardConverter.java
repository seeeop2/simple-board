package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardConverter {

    private final PostConverter postConverter;

    public BoardDto toDto(BoardEntity boardEntity){
        List<PostDto> postList = boardEntity.getPostList().stream().map(postEntity -> {
            return postConverter.toDto(postEntity);
        }).collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }
}
