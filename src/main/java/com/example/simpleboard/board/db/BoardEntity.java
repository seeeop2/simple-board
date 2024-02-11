package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "board")
@Entity
public class BoardEntity {

    @Id @GeneratedValue
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(mappedBy ="board")
    private List<PostEntity> postList = List.of();

}
