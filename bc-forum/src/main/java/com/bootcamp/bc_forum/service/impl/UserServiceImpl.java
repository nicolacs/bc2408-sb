package com.bootcamp.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CompanyEntity;
import com.bootcamp.bc_forum.entity.GeoEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.exception.ErrorCode;
import com.bootcamp.bc_forum.exception.ResTemplateErrorException;
import com.bootcamp.bc_forum.exception.UserNotFoundException;
import com.bootcamp.bc_forum.mapper.JPHMapper;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDbDTO;
import com.bootcamp.bc_forum.repository.AddressRepository;
import com.bootcamp.bc_forum.repository.CompanyRepository;
import com.bootcamp.bc_forum.repository.GeoRepository;
import com.bootcamp.bc_forum.repository.UserRepository;
import com.bootcamp.bc_forum.service.UserService;
import com.bootcamp.bc_forum.util.Scheme;
import com.bootcamp.bc_forum.util.Url;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  @Qualifier(value = "JPHRestTemplate")
  private RestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private GeoRepository geoRepository;

  @Autowired
  private JPHMapper jphMapper;

  @Value("${api.jph.domain}")
  private String jphDomain;

  @Value("${api.jph.endpoints.users}")
  private String usersEndpoint;

  @Value("${api.jph.endpoints.posts}")
  private String postsEndpoint;

  @Value("${api.jph.endpoints.comments}")
  private String commentsEndpoint;
  
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
    } catch (ResTemplateErrorException e) {
      throw new ResTemplateErrorException(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getMsg());
    }
    return List.of(users);
  }

  @Override
  public UserDTO getUserById(Long id) {
 //    if (id == null || id instanceof Number ) { // !!!instanceof 代表check type 是否xx, Java有Number type
  //     throw new InvalidInputException(ErrorCode.INVAILD_INPUT.getMsg());
  // } //!!! 已改method輸入是String 再 Long.parseLong(id) ->parseLong入面自帶左NumberFormatException
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

  //!!!! -----------------2024-10-17-----------------------
  @Override
  public List<UserEntity> saveAll(List<UserEntity> userEntities){
    return userRepository.saveAll(userEntities);
  }

  @Override
  public List<AddressEntity> saveAddress(List<AddressEntity> addressEntities){
    return addressRepository.saveAll(addressEntities);
  }

  @Override
  public List<CompanyEntity> saveCompany(List<CompanyEntity> companyEntities){
    return companyRepository.saveAll(companyEntities);
  }

  @Override
  public List<GeoEntity> saveGeo(List<GeoEntity> geoEntities){
    return geoRepository.saveAll(geoEntities);
  }

  //!!! ---------------EX 3A----------------
  @Override
  public List<UserDbDTO> getDbUser(){
    List<UserEntity> userEntities = userRepository.findAll();
    return userEntities.stream()
            .map(jphMapper::mapUserDbDTO)
            .collect(Collectors.toList());
  }

  @Override
  public UserDbDTO getDbUserById(Long id){
    Optional<UserEntity> userEntity=  userRepository.findById(id);
      return userEntity.map(jphMapper::mapUserDbDTO)
            .orElse(null);
  }

  @Override
  public  UserEntity updateUser(Long id){
    return null;
  }
}
