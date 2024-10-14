package com.bootcamp.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.exception.JPHRestClientException;
import com.bootcamp.bc_forum.service.UserService;
import com.bootcamp.bc_forum.util.Scheme;
import com.bootcamp.bc_forum.util.Url;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
  	@Qualifier (value = "JPHRestTemplate")
  	private RestTemplate restTemplate;

	@Value("${api.jph.domain}")
  	private String jphDomain;

  	@Value("${api.jph.endpoints.users}")
  	private String usersEndpoint;

	@Override
	public List<UserDTO> getUser() {
		String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.usersEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);

    UserDTO[] users;
    try{
      users = this.restTemplate.getForObject(url, UserDTO[].class);
    } catch (RestClientException e){
      throw new JPHRestClientException("Json  Placeholder Exception.");
    }
    return List.of(users);
    
    }  
}
