package com.bootcamp.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.exception.ErrorCode;
import com.bootcamp.bc_forum.exception.ResTemplateErrorException;
import com.bootcamp.bc_forum.model.dto.PostsDTO;
import com.bootcamp.bc_forum.repository.PostRepository;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.util.Scheme;
import com.bootcamp.bc_forum.util.Url;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  @Qualifier(value = "JPHRestTemplate")
  private RestTemplate restTemplate;

  @Autowired
  private PostRepository postRepository;

  @Value("${api.jph.domain}")
  private String jphDomain;

  @Value("${api.jph.endpoints.posts}")
  private String postsEndpoint;

  @Override
  public List<PostsDTO> getPost() {
    String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.postsEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);

    PostsDTO[] posts;
    try {
      posts = this.restTemplate.getForObject(url, PostsDTO[].class);
    } catch (ResTemplateErrorException e) {
      throw new ResTemplateErrorException(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getMsg());
    }
    return List.of(posts);

  }

  @Override
  public List<PostEntity> saveAll(List<PostEntity> postEntities) {
    return postRepository.saveAll(postEntities);
  }

  //!!! Learning JPQL
  @Override
  public PostEntity findPostEntity(String title){
    return postRepository.findPostEntity(title);
  }
}
