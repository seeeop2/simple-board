package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import lombok.*;
import org.hibernate.annotations.Where;

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

    @OrderBy("id desc")
    @Where(clause = "status = 'REGISTERED'")
    @OneToMany(mappedBy ="board")
    private List<PostEntity> postList = List.of();

}
