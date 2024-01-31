package com.example.simpleboard.post.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Long boardId;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(length = 2000)
    private String content;

    private LocalDateTime postedAt;

}
