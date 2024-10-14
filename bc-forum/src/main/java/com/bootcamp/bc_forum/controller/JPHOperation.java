package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_forum.dto.CommentsDTO;
import com.bootcamp.bc_forum.dto.PostsDTO;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.dto.UserPostCommentDTO;

public interface JPHOperation {
    @GetMapping("/jph/users")
    List<UserDTO> getUser();

    @GetMapping("/jph/comments")
    List<CommentsDTO> getComment();

    @GetMapping("/jph/posts")
    List<PostsDTO> getPost();

    @GetMapping("/jph/all")
    List<UserPostCommentDTO> getAll();
    
}
