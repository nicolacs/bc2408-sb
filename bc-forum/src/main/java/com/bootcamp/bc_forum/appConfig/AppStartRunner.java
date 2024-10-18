package com.bootcamp.bc_forum.appConfig;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CommentEntity;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.mapper.JPHMapper;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.UserService;

// @Autowired(required = false)
// private CommandLineRunner runner;
// runner.run();

@Component
public class AppStartRunner implements CommandLineRunner {
  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private JPHMapper jphMapper;

  @Override
  public void run(String... args) throws Exception {
    // call jph service
    // insert into database (design tables by entity)
    List<UserDTO> users = this.userService.getUser();
    List<UserEntity> userEntities = users.stream()//
    .map(uDto -> {
     return UserEntity.builder()
        .id(uDto.getId())
        .name(uDto.getName())
        .username(uDto.getUsername())
        .email(uDto.getEmail())
        .phone(uDto.getPhone())
        .website(uDto.getWebsite())
        .dummy("")
        .build();
    } )
    .collect(Collectors.toList());
    userService.saveAll(userEntities);

    // List<UserDTO> users = this.userService.getUser();
    List<AddressEntity> addressEntities = users.stream()
      .map(uDto -> {
        return AddressEntity.builder()
          .street(uDto.getAddress().getStreet())
          .suite(uDto.getAddress().getSuite())
          .city(uDto.getAddress().getCity())
          .zipcode(uDto.getAddress().getZipcode())
          .build();
      })
      .collect(Collectors.toList());
      userService.saveAddress(addressEntities);
    

    List<PostsDTO> posts = this.postService.getPost();
    List<CommentsDTO> comments = this.commentService.getComment();

    List<PostEntity> postEntities = posts.stream().map(pDto -> {
      PostEntity postEntity = jphMapper.map(pDto);

      List<CommentEntity> commentEntities = comments.stream() //
          .filter(cDto -> cDto.getPostId().equals(pDto.getId())) //
          .map(cDto -> {
            CommentEntity commentEntity = CommentEntity.builder() //
                .body(cDto.getBody()) //
                .email(cDto.getEmail()) //
                .name(cDto.getName()) //
                .build();
            commentEntity.setPost(postEntity);
            return commentEntity;
          }) //
          .collect(Collectors.toList());
      postEntity.setComments(commentEntities);
      return postEntity;
    }).collect(Collectors.toList());

    // Insert into Posts, Comments
    postService.saveAll(postEntities);
  }
}