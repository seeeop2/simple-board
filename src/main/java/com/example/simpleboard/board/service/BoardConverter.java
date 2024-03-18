package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.crud.Converter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardConverter implements Converter<BoardDto,BoardEntity> {

/*
    private final PostConverter postConverter;

    public BoardDto toDto(BoardEntity boardEntity){
        List<PostDto> postList = boardEntity.getPostList().stream().map(postEntity -> {
            return postConverter.toDTO(postEntity);
        }).collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }
*/

    @Autowired(required = false)
    private final PostRepository postRepository;

    @Override
    public BoardDto toDTO(BoardEntity boardEntity) {

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .build();
    }

    @Override
    public BoardEntity toENTITY(BoardDto boardDto) {

        List<PostEntity> postList = postRepository.findAllByBoard_Id(boardDto.getId());

        return BoardEntity.builder()
                .id(boardDto.getId())
                .boardName(boardDto.getBoardName())
                .status(boardDto.getStatus())
                .postList(postList)
                .build();
    }
}
