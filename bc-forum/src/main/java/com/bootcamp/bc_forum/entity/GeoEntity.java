package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
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
@Table(name = "Geo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GeoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String lat;
    private String lng;

    @JsonIgnore
    @OneToOne 
    @JoinColumn (name = "Id", nullable = false) //this is FK
    private AddressEntity addressEntity;
}
