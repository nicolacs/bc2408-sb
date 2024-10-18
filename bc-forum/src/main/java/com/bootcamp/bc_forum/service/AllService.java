package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.UserCommentsDTO;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO;

public interface AllService {
    List<UserPostCommentDTO> getAll();
    UserCommentsDTO getUserComment(Long id);

    

    
}
