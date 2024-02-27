package com.example.simpleboard.reply.service;

import com.example.simpleboard.crud.Converter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReplyConverter implements Converter<ReplyDto, ReplyEntity> {

    @Autowired(required = false)
    private final PostRepository postRepository;

    @Override
    public ReplyDto toDTO(ReplyEntity replyEntity) {

        return ReplyDto.builder()
                .id(replyEntity.getId())
                .postId(replyEntity.getPost().getId())
                .status(replyEntity.getStatus())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .userName(replyEntity.getUserName())
                .password(replyEntity.getPassword())
                .repliedAt(replyEntity.getRepliedAt())
                .build();
    }

    @Override
    public ReplyEntity toENTITY(ReplyDto replyDto) {

        Optional<PostEntity> postEntity = postRepository.findById(replyDto.getPostId());

        return ReplyEntity.builder()
                .id(replyDto.getId())   // << null save | not null update
                .post(postEntity.orElseGet(() -> null))
                .userName(replyDto.getUserName())
                .password(replyDto.getPassword())
                .status((replyDto.getStatus() != null) ? replyDto.getStatus() : "REGISTERED" )
                .title(replyDto.getTitle())
                .content(replyDto.getContent())
                .repliedAt( (replyDto.getRepliedAt() != null) ? replyDto.getRepliedAt() : LocalDateTime.now() )
                .build();
    }
}
