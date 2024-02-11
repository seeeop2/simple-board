package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final ReplyService replyService;

    public PostEntity create(PostRequest postRequest){
        BoardEntity boardEntity = boardRepository.findById(postRequest.getBoardId()).get();

        PostEntity entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }


    public PostEntity view(PostViewRequest postViewRequest) {
        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map( it -> {
                    //entity존재
                    if (!it.getPassword().equals(postViewRequest.getPassword())){

                        String format = "패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword() , postViewRequest.getPassword()));
                    }

                    // 답변글도 같이 적용
                    List<ReplyEntity> replyList = replyService.findAllByPostId(it.getId());
                    it.setReplyList(replyList);

                    return it;

                }).orElseThrow(() -> {
                    return new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId());
                });
    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map( it -> {
                    //entity존재
                    if (!it.getPassword().equals(postViewRequest.getPassword())){

                        String format = "패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword() , postViewRequest.getPassword()));
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;

                }).orElseThrow(() -> {
                    return new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId());
                });

    }
}
