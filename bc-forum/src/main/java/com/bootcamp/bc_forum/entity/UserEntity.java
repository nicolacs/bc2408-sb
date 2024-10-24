package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "Users")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "useremail")
  private String username;
  private String email;
  private String phone;
  private String website;
  private String dummy;

  @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL,
      fetch = FetchType.LAZY) // PK setting method
  private AddressEntity addressEntity;


}
