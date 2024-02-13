package com.example.simpleboard.reply.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create(ReplyRequest replyRequest){

        //repository에서 엔티티 찾은 뒤, 바로 get()하지 않고 아래의 과정을 거치는 것을 권장.
        Optional<PostEntity> optionPostEntity = postRepository.findById(replyRequest.getPostId());
        if (optionPostEntity.isEmpty()){
            throw new RuntimeException("게시물이 존재 하지 않습니다. : " + replyRequest.getPostId());
        }

        ReplyEntity entity =  ReplyEntity.builder()
                .post(optionPostEntity.get())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }

}
