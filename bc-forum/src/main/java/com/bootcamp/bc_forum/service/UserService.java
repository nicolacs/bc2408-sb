package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CompanyEntity;
import com.bootcamp.bc_forum.entity.GeoEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDbDTO;

public interface UserService {
    List<UserDTO> getUser();
    UserDTO getUserById(Long id);
    List<UserEntity> saveAll(List<UserEntity> userEntities);

    // Ex3
    List<AddressEntity> saveAddress(List<AddressEntity> addressEntities);
    // ^ Step: 1. create Entity & Repository
    //  2. create method here in service.
    //  3. autowired service class & override method in Impl.
    //  4. use in AppStartRunner.
    // 郁到5個FILES

    List<CompanyEntity> saveCompany(List<CompanyEntity> companyEntities);
    List<GeoEntity> saveGeo(List<GeoEntity> geoEntities);

//!!! ---------------EX 3A----------------
    List<UserDbDTO> getDbUser();
    UserDbDTO getDbUserById(Long id);
    UserEntity updateUser(Long id);
 
}
