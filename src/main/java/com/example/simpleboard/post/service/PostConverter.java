package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.crud.Converter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostConverter implements Converter<PostDto, PostEntity> {

    @Autowired(required = false)
    private final BoardRepository boardRepository;

    @Autowired(required = false)
    private final ReplyRepository replyRepository;

    @Override
    public PostDto toDTO(PostEntity postEntity) {
        return PostDto.builder()
                .id(postEntity.getId())
                .boardId(postEntity.getBoard().getId())
                .userName(postEntity.getUserName())
                .password(postEntity.getPassword())
                .email(postEntity.getEmail())
                .status(postEntity.getStatus())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .build();
    }

    @Override
    public PostEntity toENTITY(PostDto postDto) {

        Optional<BoardEntity> boardEntity = boardRepository.findById(postDto.getBoardId());

        List<ReplyEntity> replies = replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postDto.getId(), "REGISTERED");

        return PostEntity.builder()
                .id(postDto.getId())
                .board(boardEntity.orElseGet(() -> null))
                .userName(postDto.getUserName())
                .password(postDto.getPassword())
                .email(postDto.getEmail())
                .status((postDto.getStatus() != null) ? postDto.getStatus() : "REGISTERED")
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postedAt((postDto.getPostedAt() != null) ? postDto.getPostedAt() : LocalDateTime.now())
                .replyList(replies)
                .build();
    }


/*
    public PostDto toDto(PostEntity postEntity){
        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .build();
    }
*/
}
