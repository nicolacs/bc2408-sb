package com.bootcamp.demo_jsonplaceholder.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  @Id // Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private Long id;
  private String name;
  private String username;
  private String phone;
  private String website;
  @Column(name = "address_street")
  private String addrStreet;
  @Column(name = "address_suite")
  private String addrSuite;
  @Column(name = "address_city")
  private String addrCity;
  @Column(name = "address_zip_code")
  private String addrZipcode;
  @Column(name = "address_lat")
  private String addrLat;
  @Column(name = "address_long")
  private String addrLng;
  @Column(name = "company_name")
  private String comName;
  @Column(name = "company_catch_phrase")
  private String comCatchPhrase;
  @Column(name = "company_bs")
  private String comBs;



}
