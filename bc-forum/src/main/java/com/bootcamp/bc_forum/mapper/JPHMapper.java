package com.bootcamp.bc_forum.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum.entity.CommentEntity;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.Comment;
import com.bootcamp.bc_forum.model.Post;
import com.bootcamp.bc_forum.model.User;
import com.bootcamp.bc_forum.model.User.Address;
import com.bootcamp.bc_forum.model.User.Company;
import com.bootcamp.bc_forum.model.User.Address.Geo;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO.AddressDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO.AddressDTO.CompanyDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO.AddressDTO.GeoDTO;

// JPHMapper -> object (bean) -> map()
@Component
public class JPHMapper {
  public UserEntity map(UserDTO user) {
    return UserEntity.builder().id(user.getId()).name(user.getName())
        .username(user.getUsername()).email(user.getEmail())
        .phone(user.getPhone()).website(user.getWebsite())
        .dummy(String.valueOf("")).build();
  }

  public PostEntity map(PostsDTO posts) {
    return PostEntity.builder().postId(posts.getId()).title(posts.getTitle())
        .body(posts.getBody()).build();
  }

  public CommentEntity map(CommentsDTO comments) {
    return CommentEntity.builder().id(comments.getPostId()).name(comments.getName())
        .email(comments.getEmail()).body(comments.getBody()).build();
  }

  public GeoDTO mapGeo(Geo geo) {
    return GeoDTO.builder().lat(geo.getLat()).lng(geo.getLng()).build();
  }

  public GeoDTO mapGeo(UserDTO.Address.Geo geo) {
    return GeoDTO.builder().lat(geo.getLat()).lng(geo.getLng()).build();
  }

  public AddressDTO mapAddress(Address address) {
    return AddressDTO.builder().street(address.getStreet())//
        .suite(address.getSuite())//
        .city(address.getCity())//
        .zipcode(address.getZipcode())//
        .geo(this.mapGeo(address.getGeo()))//
        .build();
  }

  public AddressDTO mapAddress(UserDTO.Address address) {
    return AddressDTO.builder().street(address.getStreet())//
        .suite(address.getSuite())//
        .city(address.getCity())//
        .zipcode(address.getZipcode())//
        .geo(this.mapGeo(address.getGeo()))//
        .build();
  }

  public CompanyDTO mapCompany(UserDTO.Company company) {
    return CompanyDTO.builder().name(company.getName())
        .catchPhrase(company.getCatchPhrase()).bs(company.getBs()).build();
  }

  public CompanyDTO mapCompany(Company company) {
    return CompanyDTO.builder().name(company.getName())
        .catchPhrase(company.getCatchPhrase()).bs(company.getBs()).build();
  }

  public UserPostCommentDTO mapToDTO(User user) {
    return UserPostCommentDTO.builder()
        // .id(user.getId())
        .name(user.getName()).username(user.getUsername())
        .email(user.getEmail()).phone(user.getPhone())
        .website(user.getWebsite()).build();
  }

  public UserPostCommentDTO mapToDTO(UserDTO user) {
    return UserPostCommentDTO.builder().id(user.getId()).name(user.getName())
        .username(user.getUsername()).email(user.getEmail())
        .phone(user.getPhone()).website(user.getWebsite()).build();
  }

  public UserPostCommentDTO.Posts mapToDTO(Post post) {
    return UserPostCommentDTO.Posts.builder().postId(post.getId())
        .title(post.getTitle()).body(post.getBody()).build();
  }

  public UserPostCommentDTO.Posts mapToDTO(PostsDTO post) {
    return UserPostCommentDTO.Posts.builder().postId(post.getId())
        .title(post.getTitle()).body(post.getBody()).build();
  }

  public UserPostCommentDTO.Comments mapToDTO(Comment comment) {
    return UserPostCommentDTO.Comments.builder()//
        .commentId(comment.getId())//
        .name(comment.getName())//
        .email(comment.getEmail())//
        .body(comment.getBody())//
        .build();
  }

  public UserPostCommentDTO.Comments mapToDTO(CommentsDTO comment) {
    return UserPostCommentDTO.Comments.builder()//
        .commentId(comment.getId())//
        .name(comment.getName())//
        .email(comment.getEmail())//
        .body(comment.getBody())//
        .build();
  }

  // map :[ [1,2,3] ,[4,5,6]]->[[1,2,3,4,5,6]]
  // flatmap :[[1,2,3],[4,5,6]] ->[1,2,3,4,5,6]
  public UserCommentsDTO mapToDTO(UserPostCommentDTO c) {
    UserCommentsDTO result = UserCommentsDTO.builder().id(c.getId())
        .userName(c.getUsername()).build();
    List<UserCommentsDTO.CommentDTO2> commentsList = new ArrayList<>();

    commentsList = c.getPostDTO().stream()//
        .flatMap(post -> post.getCommentsDTO().stream()//
            .map(comment -> new UserCommentsDTO.CommentDTO2(
                comment.getCommentId(), comment.getBody())))//
        .collect(Collectors.toList());

    result.setCommentDto2(commentsList);
    return result;
  }
}
    // .map(c -> map(UserPostCommentDTO.Posts.Comments.getBody()))
    // .map(c -> map(c.getCommentsDTO().stream()//
    // commentsList= c.getPostDTO().stream()//
    // .flatMap(post -> post.getCommentsDTO().stream()//
    // .map(comment -> comment.getCommentId())
    // .collect(Collectors.toList())
    // .build())
    // .collect(Collectors.toList());