package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.bootcamp.bc_forum.model.dto.UserDTO.Address.Geo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long userId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id", nullable = false)  //FK setting method
    private UserEntity userEntity;
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    // PK setting method
    @OneToOne(mappedBy = "addressEntity", cascade = CascadeType.ALL,
       fetch = FetchType.LAZY)
    private GeoEntity geo;

}
