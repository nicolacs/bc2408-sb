package com.bootcamp.demo_jsonplaceholder.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity;
import com.bootcamp.demo_jsonplaceholder.model.dto.UserDTO;

// Restful -> read/ write resource
public interface JPHOperation {
    @GetMapping("/jph/users")
    List<UserDTO> getUsers();

    /**
     * Service Layer call external JPH service directly to refresh the user list.
     * @return
     */
    @PostMapping("/jph/users")
    List<UserEntity> createUsers();

    @PostMapping("/jph/saveusers") //係原本冇, 新增
    List<UserEntity> saveUsers();

    @DeleteMapping("/jph/user")
    Boolean deleteUser(@RequestParam Long id);

    @PutMapping("/jph/user") // BY PK , 成個資源更新
    UserEntity updateUser(@RequestParam Long id, @RequestBody UserEntity entity);
  
    @PatchMapping("/jph/user/{id}") // 希望只係改一個filed
    UserEntity patchUserWebsite(@PathVariable Long id, @RequestParam String website);

    @PostMapping("/jph/user")
    UserEntity createUser(@RequestBody UserEntity userEntity);

    @GetMapping("/jph/user/website/{website}")
    UserEntity getUserByWebsite(@PathVariable String website);

  }
