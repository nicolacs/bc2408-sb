package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.dto.PostsDTO;

public interface PostService {
    List<PostsDTO> getPost();
}
