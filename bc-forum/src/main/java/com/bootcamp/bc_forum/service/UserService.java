package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.dto.UserDTO;

public interface UserService {
    List<UserDTO> getUser();
    UserDTO getUserById(Long id);
    List<UserEntity> saveAll(List<UserEntity> userEntities);

    // Ex3
    List<AddressEntity> saveAddress(List<AddressEntity> addressEntities);
}
