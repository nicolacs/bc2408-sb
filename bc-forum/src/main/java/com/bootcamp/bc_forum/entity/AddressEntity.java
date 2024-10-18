package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
import com.bootcamp.bc_forum.model.dto.UserDTO.Address.Geo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddressEntity implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

}
