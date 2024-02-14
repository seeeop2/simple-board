package com.example.simpleboard.board.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Api<T> {
    
    private T body;

    private Pagination pagination;
}
