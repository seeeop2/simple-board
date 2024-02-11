package com.example.simpleboard.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {

    private Long boardId = 1L;  //일시 고정

    @NotBlank
    private String userName;

    @Size(min = 4 , max = 4)
    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
