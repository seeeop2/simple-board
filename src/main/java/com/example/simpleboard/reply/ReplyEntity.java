package com.example.simpleboard.reply;

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

    private Long postId;

    private String userName;

    private String password;

    private String status;

    private String title;

    @Column(length = 2000)
    private String content;

    private LocalDateTime repliedAt;

}
