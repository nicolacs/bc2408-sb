package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.UserDTO;

public interface UserService {
    List<UserDTO> getUser();
    UserDTO getUserById(Integer id);
    
}
