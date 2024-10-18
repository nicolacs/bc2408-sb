package com.bootcamp.demo_jsonplaceholder.config;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo_jsonplaceholder.entity.CommentEntity;
import com.bootcamp.demo_jsonplaceholder.entity.PostEntity;
import com.bootcamp.demo_jsonplaceholder.model.CommentDTO;
import com.bootcamp.demo_jsonplaceholder.model.PostDTO;
import com.bootcamp.demo_jsonplaceholder.service.JPHService;
import com.bootcamp.demo_jsonplaceholder.service.PostService;

// CommandLineRunner runner = new AppStartRunner();
// implements CommandLineRunner即係背後等於: 
// @Autowised(required = false) >> 用CommandLineRunner runner 黎粒BEAN

@Component
public class AppStartRunner implements CommandLineRunner{
    @Autowired
    private PostService postService;

    @Autowired
    private JPHService jphService;


    @Override
    public void run(String ... arg) throws Exception{
        //System.out.println("hello world");
        // Ex 3 要做 call jph service
        // insert into database
    List<PostDTO> posts = this.jphService.getPosts();
    List<CommentDTO> comments = this.jphService.getComments();

    List<PostEntity> postEntities = posts.stream().map(pDto -> {
      PostEntity postEntity = PostEntity.builder() //
          .title(pDto.getTitle()) //
          .body(pDto.getBody()) //
          .build();
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
