package com.bootcamp.demo_jsonplaceholder.service;

// https://jsonplaceholder.typicode.com/users
import java.util.List;
import java.util.Optional;
import com.bootcamp.demo_jsonplaceholder.entity.PostEntity;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity2;
import com.bootcamp.demo_jsonplaceholder.model.dto.CommentDTO;
import com.bootcamp.demo_jsonplaceholder.model.dto.PostDTO;
import com.bootcamp.demo_jsonplaceholder.model.dto.UserDTO;

public interface JPHService {
  List<UserDTO> getUsers();

  // calling api
  List<UserEntity> saveUsers();

  Boolean deleteUser(Long id);

  UserEntity updateUser(Long id, UserEntity entity);

  UserEntity patchUserWebsite(Long id, String website);

  UserEntity createUser(UserEntity userEntity);

  Optional<UserEntity> findByWebsite(String website);

  List<PostDTO> getPosts();
  List<CommentDTO> getComments();

  List<UserEntity2> saveAll(List<UserEntity2> userEntities);
}
