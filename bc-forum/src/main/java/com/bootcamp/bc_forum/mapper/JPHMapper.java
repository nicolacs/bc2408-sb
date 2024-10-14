package com.bootcamp.bc_forum.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.entity.UserEntity;

// JPHMapper -> object (bean) -> map()
@Component
public class JPHMapper {
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
