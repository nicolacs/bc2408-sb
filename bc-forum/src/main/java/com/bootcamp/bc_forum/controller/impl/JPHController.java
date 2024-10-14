package com.bootcamp.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.JPHOperation;
import com.bootcamp.bc_forum.dto.CommentsDTO;
import com.bootcamp.bc_forum.dto.PostsDTO;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.bc_forum.service.AllService;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.UserService;

@RestController
public class JPHController implements JPHOperation{
    
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private AllService allService;

    @Override
    public List<UserDTO> getUser() {
    return userService.getUser();
    }

    @Override
    public List<CommentsDTO> getComment() {
    return commentService.getComment();
    }

    @Override
    public List<PostsDTO> getPost(){
        return postService.getPost();
    }

    @Override
    public List<UserPostCommentDTO> getAll(){
        return allService.getAll();
    }

}
