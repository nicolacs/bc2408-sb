package com.bootcamp.bc_forum.appConfig;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CommentEntity;
import com.bootcamp.bc_forum.entity.CompanyEntity;
import com.bootcamp.bc_forum.entity.GeoEntity;
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
          return UserEntity.builder().id(uDto.getId()).name(uDto.getName())
              .username(uDto.getUsername()).email(uDto.getEmail())
              .phone(uDto.getPhone()).website(uDto.getWebsite()).dummy("")
              .build();
        }).collect(Collectors.toList());
    userService.saveAll(userEntities);

// ~~~~ Ex3 將AddressEntity 同PK連結     
    List<AddressEntity> addressEntities = users.stream().map(uDto -> {
      return AddressEntity.builder().userId(uDto.getId())
          .street(uDto.getAddress().getStreet())
          .suite(uDto.getAddress().getSuite()).city(uDto.getAddress().getCity())
          .zipcode(uDto.getAddress().getZipcode()).build();
    }).collect(Collectors.toList());

    for (int i = 0; i < addressEntities.size(); i++) {
      for (int j = 0; j < userEntities.size(); j++) {
        if (addressEntities.get(i).getUserId()
            .equals(addressEntities.get(j).getId())) {
          addressEntities.get(i).setUserId(userEntities.get(j).getId());
        }
      }
    }
    userService.saveAddress(addressEntities);

    List<GeoEntity> geoEntities = users.stream().map(uDto -> {
      return GeoEntity.builder().Id(uDto.getId())
        .lat(uDto.getAddress().getGeo().getLat())
        .lng(uDto.getAddress().getGeo().getLng())
        .build();
    }).collect(Collectors.toList());

    for (int i = 0; i < geoEntities.size(); i++) {
      for (int j = 0; j < addressEntities.size(); j++) {
        if (geoEntities.get(i).getId()
            .equals(geoEntities.get(j).getId())) {
              geoEntities.get(i).setId(addressEntities.get(j).getUserId());
        }
      }
    }
    userService.saveGeo(geoEntities);


//!!!!  Company同User 係one to one
// ~~~~ Ex3 將CompanyEntity 同PK連結 
    List<CompanyEntity> companyEntities = users.stream().map(uDto -> {
      return CompanyEntity.builder().userId(uDto.getId())
          .name(uDto.getCompany().getName())
          .catchPhrase(uDto.getCompany().getCatchPhrase())
          .bs(uDto.getCompany().getBs())
          .build();
    }).collect(Collectors.toList());

    for (int i = 0; i < companyEntities.size() ; i++){
      for (int j =0; j<userEntities.size(); j++){
        if (companyEntities.get(i).getUserId().equals(userEntities.get(j).getId())){
          companyEntities.get(i).setUserId(userEntities.get(j).getId());
        }
      }
    }
    userService.saveCompany(companyEntities);
     
//!!!!  Post同Comment 係one to many
    List<PostsDTO> posts = this.postService.getPost();
    List<CommentsDTO> comments = this.commentService.getComment();
//!!!!!! Here 試左唔.builder, 因為mapping已經寫左, 所以autowired返mapping粒bean. 
//!!!!!  係度用mapping 個OBJ去.map
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
