package com.bootcamp.demo_jsonplaceholder.service;

// https://jsonplaceholder.typicode.com/users
import java.util.List;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity;
import com.bootcamp.demo_jsonplaceholder.model.UserDTO;

public interface JPHService {
  List<UserDTO> getUsers();

  // calling api
  List<UserEntity> saveUsers();

  Boolean deleteUser(Long id);

  UserEntity updateUser(Long id, UserEntity entity);

  UserEntity patchUserWebsite(Long id, String website);

  UserEntity createUser(UserEntity userEntity);
}