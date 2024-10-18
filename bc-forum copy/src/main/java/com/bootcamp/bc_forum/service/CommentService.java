package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;

public interface CommentService {
    List<CommentsDTO> getComment();
}
