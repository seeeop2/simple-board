package com.example.simpleboard.board.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Pagination {

    private Integer page;               //현재 페이지
    private Integer size;               //총 사이즈
    private Integer currentElements;    //현재 가지고 있는 엘리먼트
    private Integer totalPage;          //전체 페이지
    private Long totalElements;         //전체 엘리먼트
    
}
