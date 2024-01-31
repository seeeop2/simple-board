package com.example.simpleboard.board.db;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
