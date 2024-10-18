package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
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
@Builder
@Getter
@Setter
public class GeoEntity implements Serializable {
    private String lat;
    private String lng;
}
