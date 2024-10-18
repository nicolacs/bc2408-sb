package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@EntityScan
@Table(name = "Users")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String username;

  @Column(name = "useremail")
  private String email;
  private String phone;
  private String website;
  private String dummy;
}
