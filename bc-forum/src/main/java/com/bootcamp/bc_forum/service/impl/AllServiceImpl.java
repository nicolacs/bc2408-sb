package com.bootcamp.bc_forum.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_forum.dto.CommentsDTO;
import com.bootcamp.bc_forum.dto.PostsDTO;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.bc_forum.service.AllService;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.UserService;

@Service
public class AllServiceImpl implements AllService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;


    public UserPostCommentDTO allData(){
    
    List<UserDTO> userList =  userService.getUser();
    List<PostsDTO> postList =  postService.getPost();
    List<CommentsDTO> commentList =  commentService.getComment();

    List<UserPostCommentDTO> combinedDataList = new ArrayList<>();
    
    for (int i = 0; i < userList.size(); i++) {
     // UserPostCommentDTO user = userList.getId(i);

        combinedDataList.add(userList);
    }        
    PostsDTO post = postList.get(i);
        CommentsDTO comment = commentList.get(i);

    return combinedDataList;

    }

    
}
