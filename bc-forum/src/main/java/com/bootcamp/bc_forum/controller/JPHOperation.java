package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDbDTO;
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
    UserCommentsDTO getUserComment(@RequestParam String userId);

    @GetMapping("jph/user")   
    UserDTO getUserById(@RequestParam String userId);

    // Ex3A 
    @GetMapping("jph/dbuser")
    List<UserDbDTO> getDbUser();

    @GetMapping("jph/dbuserbyid")
    UserDbDTO getDbUserById(@RequestParam String id);
    
     @PutMapping("/jph/userupdate") 
    UserEntity updateUser(@RequestParam String id, @RequestBody UserEntity entity);


//!!! Learning JPQL
    @GetMapping("jph/findpost")
    PostEntity findPostEntity(@RequestParam String title);

}
