package com.bootcamp.bc_forum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_forum.mapper.JPHMapper;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO;
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

    @Autowired
    private JPHMapper mapper;

    @Override
    public List<UserPostCommentDTO> getAll(){
    List<UserDTO> userLists =  userService.getUser();
    List<PostsDTO> postLists =  postService.getPost();
    List<CommentsDTO> commentLists =  commentService.getComment();
    // List<User> userLists = List.of(this.userService.getUser());
    // List<Post> postLists = this.postService.getPost();
    // List<Comment> commentLists = this.commentService.getComment();

    List<UserPostCommentDTO> combinedDataList = new ArrayList<>();
    
    // Approach 2
    for (int i = 0; i < userLists.size(); i++) {
      List<UserPostCommentDTO.Posts> userLayer = new ArrayList<>();
       
      for (int j = 0; j < postLists.size(); j++) {
        if (postLists.get(j).getUserId().equals(userLists.get(i).getId())) {
        List<UserPostCommentDTO.Comments> postsLayer = new ArrayList<>();
        for (int k = 0; k < commentLists.size(); k++) {
          if (commentLists.get(k).getPostId().equals(postLists.get(j).getUserId())) {
            UserPostCommentDTO.Comments commentLayer =
            mapper.mapToDTO(commentLists.get(k));
            postsLayer.add(commentLayer);   
           }
        } ;
        UserPostCommentDTO.Posts postDto =
            mapper.mapToDTO(postLists.get(j));
            postDto.setCommentsDTO(postsLayer);
            userLayer.add(postDto);
      }
    } ;
    UserPostCommentDTO userDto = mapper.mapToDTO(userLists.get(i));
    userDto.setAddress(mapper.mapAddress(userLists.get(i).getAddress()));
    userDto.setCompany(mapper.mapCompany(userLists.get(i).getCompany()));
    userDto.setPostDTO(userLayer);
    combinedDataList.add(userDto);
    } ;
    return combinedDataList;
};

  @Override
  //public List<UserCommentsDTO> getUserComment() {
    //List<UserDTO> userLists =  userService.getUser();
    //List<CommentsDTO> commentLists =  commentService.getComment();
    // List<UserCommentsDTO> userCommentLists = new ArrayList<>();
    // return userCommentLists;
  public UserCommentsDTO getUserComment(Integer id) {
    UserPostCommentDTO target =    this.getAll().stream()//
    .filter(user->id.equals(user.getId()))//
    .findFirst()//
    .get();

    UserCommentsDTO result = mapper.mapToDTO(target);
    return result;

}
    
}
