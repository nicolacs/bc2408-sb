package com.bootcamp.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.JPHOperation;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO;
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

    @Override
    public UserCommentsDTO getUserComment(Integer id){
        return allService.getUserComment(id);
    }

    @Override
    public UserDTO getUserById(Integer id){
        return userService.getUserById(id);
    }
}
