package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "post")
@Entity
public class PostEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private BoardEntity board;  //연관관계 일 때, board+_id --> board_id로 관계 맺어준다.

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(length = 2000)
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(mappedBy = "post")
    private List<ReplyEntity> replyList = List.of();

}
