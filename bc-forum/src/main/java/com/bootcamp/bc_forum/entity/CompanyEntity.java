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
@Table(name = "Company")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CompanyEntity implements Serializable {
    private String name;
    private String catchPhrase;
    private String bs;

}
