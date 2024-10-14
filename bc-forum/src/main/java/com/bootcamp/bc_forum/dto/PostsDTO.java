package com.bootcamp.bc_forum.dto;

import java.util.ArrayList;
import java.util.List;
import com.bootcamp.bc_forum.dto.UserPostCommentDTO.Posts.Comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PostsDTO {
    private int userId;
    private int id;
    private String title;
    private String body;
    //List<Comments> comments = new ArrayList<>();
}
