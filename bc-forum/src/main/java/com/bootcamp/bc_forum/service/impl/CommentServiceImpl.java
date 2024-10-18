package com.bootcamp.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.exception.ErrorCode;
import com.bootcamp.bc_forum.exception.ResTemplateErrorException;
import com.bootcamp.bc_forum.model.dto.CommentsDTO;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.util.Scheme;
import com.bootcamp.bc_forum.util.Url;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  @Qualifier (value = "JPHRestTemplate") 
  private RestTemplate restTemplate;

	@Value("${api.jph.domain}")
  	private String jphDomain;

  @Value("${api.jph.endpoints.comments}")
  private String commentsEndpoint;

	@Override
	public List<CommentsDTO> getComment() {
		String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.commentsEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);

    CommentsDTO[] comments;
    try{
        comments = this.restTemplate.getForObject(url, CommentsDTO[].class);
      } catch (ResTemplateErrorException e){
      throw new ResTemplateErrorException(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getMsg());
      }
    return List.of(comments);  
  }  
}
