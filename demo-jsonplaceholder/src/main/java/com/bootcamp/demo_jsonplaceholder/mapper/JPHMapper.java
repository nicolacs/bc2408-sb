package com.bootcamp.demo_jsonplaceholder.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity;
import com.bootcamp.demo_jsonplaceholder.model.dto.UserDTO;

// JPHMapper -> object (bean) -> map()
@Component
public class JPHMapper {
  // reivse from static method to instance

  // UserDTO.class (Controller Layer)
  // User.class (Service Layer)
  // UserEntity.class (Repository Layer)
  
  public UserEntity map(UserDTO userDTO) {
    return UserEntity.builder() //
        .addrLat(userDTO.getAddress().getGeo().getLat()) //
        .addrLng(userDTO.getAddress().getGeo().getLng()) //
        .addrCity(userDTO.getAddress().getCity()) //
        .addrStreet(userDTO.getAddress().getStreet()) //
        .addrSuite(userDTO.getAddress().getSuite()) //
        .addrZipcode(userDTO.getAddress().getZipcode()) //
        .comBs(userDTO.getCompany().getBs()) //
        .comCatchPhrase(userDTO.getCompany().getCatchPhrase()) //
        .comName(userDTO.getCompany().getName()) //
        .name(userDTO.getCompany().getName()) //
        .username(userDTO.getUsername()) //
        .phone(userDTO.getPhone()) //
        .website(userDTO.getWebsite()) //
        .build();
    }
}
