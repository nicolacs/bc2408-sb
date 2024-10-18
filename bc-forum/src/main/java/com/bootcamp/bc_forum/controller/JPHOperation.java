package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO;

public interface JPHOperation {
    @GetMapping("/jph/users")
    List<UserDTO> getUser();

    @GetMapping("/jph/comments")
    List<CommentsDTO> getComment();

    @GetMapping("/jph/posts")
    List<PostsDTO> getPost();

    @GetMapping("/jph/all")
    List<UserPostCommentDTO> getAll();

    @GetMapping("jph/usercomment")
    UserCommentsDTO getUserComment(@RequestParam Long userId);

    @GetMapping("jph/user")
    UserDTO getUserById(@RequestParam Long userId);

    
}
