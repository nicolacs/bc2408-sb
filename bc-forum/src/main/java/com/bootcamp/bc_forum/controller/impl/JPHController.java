package com.bootcamp.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.JPHOperation;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDbDTO;
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
    public UserCommentsDTO getUserComment(String id){
        return allService.getUserComment(Long.parseLong(id));
    }

    @Override
    public UserDTO getUserById(String id){
        return userService.getUserById(Long.parseLong(id));
    }

//!!! ---------------EX 3A----------------  
    @Override
    public List<UserDbDTO> getDbUser(){
        return userService.getDbUser();
    }

    @Override
    public UserDbDTO getDbUserById(String id){
        return userService.getDbUserById(Long.parseLong(id));
    }

    @Override
    public UserEntity updateUser(String id, UserEntity entity){
        // return userService.updateUser(Long.parseLong(id));
        // if (id == null || entity == null || !id.equals(entity.getId())) {
        //     throw new IllegalArgumentException();
        //   }
        //   if (this.userRepository.findById(id).isPresent()) {
        //     return this.userRepository.save(entity);
        //   }
          return null; // throw new NotFoundException()
        }

    //!!! Learning JPQL
    @Override
    public PostEntity findPostEntity(String title){
        return postService.findPostEntity(title);
    }
}


