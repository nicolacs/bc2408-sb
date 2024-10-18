package com.bootcamp.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.exception.ErrorCode;
import com.bootcamp.bc_forum.exception.UserNotFoundException;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.service.UserService;
import com.bootcamp.bc_forum.util.Scheme;
import com.bootcamp.bc_forum.util.Url;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  @Qualifier(value = "JPHRestTemplate")
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
    try {
      users = this.restTemplate.getForObject(url, UserDTO[].class);
    } catch (RestClientException e) {
      throw new RestClientException("Json  Placeholder Exception.");
    }
    return List.of(users);

  }

  @Override
  public UserDTO getUserById(Integer id) {
    String url = Url.builder()
      .scheme(Scheme.HTTPS).domain(this.jphDomain)
      .endpoint(this.usersEndpoint).build() //
      .toUriString();
    System.out.println("url=" + url);

    UserDTO[] users = this.restTemplate.getForObject(url, UserDTO[].class);

    List<UserDTO> userList = Arrays.asList(users);
    return userList.stream()//
        .filter(user -> id.equals(user.getId()))//
        .findFirst()//
        .orElseThrow(()->
            new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getMsg()));
  }
}
