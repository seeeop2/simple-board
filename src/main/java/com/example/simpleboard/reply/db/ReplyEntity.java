package com.example.simpleboard.reply.db;

import com.example.simpleboard.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "reply")
@Entity
public class ReplyEntity {

    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private PostEntity post;

    private String userName;

    private String password;

    private String status;

    private String title;

    @Column(length = 2000)
    private String content;

    private LocalDateTime repliedAt;

}
