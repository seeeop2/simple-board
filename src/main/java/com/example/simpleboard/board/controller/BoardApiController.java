package com.example.simpleboard.board.controller;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardRequest;
import com.example.simpleboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardEntity create(@Valid @RequestBody BoardRequest boardRequest){
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardEntity view(@PathVariable Long id){

        BoardEntity entity = boardService.view(id);
        log.info("result : " + entity);

        return entity;
    }
}
