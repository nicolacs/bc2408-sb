package com.bootcamp.demo_jsonplaceholder.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity;
import com.bootcamp.demo_jsonplaceholder.exception.JPHRestClientException;
import com.bootcamp.demo_jsonplaceholder.mapper.JPHMapper;
import com.bootcamp.demo_jsonplaceholder.model.CommentDTO;
import com.bootcamp.demo_jsonplaceholder.model.PostDTO;
import com.bootcamp.demo_jsonplaceholder.model.UserDTO;
import com.bootcamp.demo_jsonplaceholder.repository.UserRepository;
import com.bootcamp.demo_jsonplaceholder.service.JPHService;
import com.bootcamp.demo_jsonplaceholder.util.Scheme;
import com.bootcamp.demo_jsonplaceholder.util.Url;

@Service  // 如果冇@Service,  Controller個度就@Autowised 唔到
          // Component annotation -> bean
public class JPHServiceImpl implements JPHService {

  @Autowired
  @Qualifier (value = "JPHRestTemplate") // inject bean by specific bean name
  private RestTemplate restTemplate;

  // @Autowired
  //private Cat cat; // Vincent

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JPHMapper jphMapper;

  //!!! @Value (inject from yml) is similar to @Autowired (inject from Spring
  // Context)
  // Both of them has to be executed before server start
  @Value("${api.jph.domain}")
  private String jphDomain;

  @Value("${api.jph.endpoints.users}")
  private String usersEndpoint;

  @Value("${api.jph.endpoints.posts}")
  private String postsEndpoint;

  @Value("${api.jph.endpoints.comments}")
  private String commentsEndpoint;

    @Override
    public List<UserDTO> getUsers() {
      //!!! 舊方法
      //UserDTO[] users = new RestTemplate().getForObject(
      //  "https://jsonplaceholder.typicode.com/users", UserDTO[].class);
      
    // ! You can use UriComponentBuilder directly
    String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.usersEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);

    UserDTO[] users;
    try{
      users = this.restTemplate.getForObject(url, UserDTO[].class); // 10 ms
    } catch (RestClientException e){
      throw new JPHRestClientException("Json  Placeholder Exception.");
    }
    return List.of(users);
    }

    @Override
    public List<UserEntity> saveUsers() {
      // Call External JPH service
      List<UserDTO> userDTOs = this.getUsers();
      return this.saveUsers(userDTOs);
    }

  private List<UserEntity> saveUsers(List<UserDTO> userDTOs) {
    // Mapper: from List<UserDTO> to List<UserEntity>
    List<UserEntity> userEntities = userDTOs.stream() //
        .map(e -> this.jphMapper.map(e)) //
        .collect(Collectors.toList());
    return userRepository.saveAll(userEntities);
  }
  // saveUser(int id)
  // -> stream filter -> save()

  @Override
  public Boolean deleteUser(Long id){
    if (this.userRepository.findById(id).isPresent()){
    this.userRepository.deleteById(id);
    return true;
    }
  return false;
  }

  // save(): create or replace

  //!!!第一個updateUser寫法:
  // @Override
  // public UserEntity updateUser(Long id, UserEntity entity){
  //   if (id != null 
  //       && entity != null 
  //       &&id.equals(entity.getId()) 
  //       && this.userRepository.findById(id).isPresent()){
  //     return this.userRepository.save(entity); // update 訪度? -> this.userRepository
  //   }
  // return null; // can throw new NotFoundException() rather than null
  // }

  // !!! NOTE updateUser另一BETTER寫法:
  @Override
  public UserEntity updateUser(Long id, UserEntity entity) {
    if (id == null || entity == null || !id.equals(entity.getId())) {
      throw new IllegalArgumentException();
    }
    if (this.userRepository.findById(id).isPresent()) {
      return this.userRepository.save(entity);
    }
    return null; // throw new NotFoundException()
  }

  @Override
  public  UserEntity patchUserWebsite(Long id, String website){
    if (id == null || website == null ) {
      throw new IllegalArgumentException();
    }
    Optional<UserEntity> userEntity = this.userRepository.findById(id);
    if (userEntity.isPresent()) {
      UserEntity entity = userEntity.get();
      entity.setWebsite(website);
      return this.userRepository.save(entity);
    }
    return null; // throw new NotFoundException()
  }

  @Override
  public UserEntity createUser(UserEntity userEntity){
    return this.userRepository.save(userEntity);
  }

  @Override
  public Optional<UserEntity> findByWebsite(String website){
    return this.userRepository.findByWebsite(website);
  }

  //!!!! -----------------2024-10-17-----------------------
  @Override
  public List<PostDTO> getPosts() {
    String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.postsEndpoint) //
        .build() //
        .toUriString();
    PostDTO[] posts;
    try {
      posts = this.restTemplate.getForObject(url, PostDTO[].class);
    } catch (RestClientException e) {
      throw new JPHRestClientException("Json Placeholder Exception.");
    }
    return List.of(posts);
  }

  @Override
  public List<CommentDTO> getComments() {
    String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.commentsEndpoint) //
        .build() //
        .toUriString();
    CommentDTO[] comments;
    try {
      comments = this.restTemplate.getForObject(url, CommentDTO[].class);
    } catch (RestClientException e) {
      throw new JPHRestClientException("Json Placeholder Exception.");
    }
    return List.of(comments);
  }
}
