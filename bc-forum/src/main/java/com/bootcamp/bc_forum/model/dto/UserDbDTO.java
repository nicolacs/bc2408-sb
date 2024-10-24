package com.bootcamp.bc_forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDbDTO {
    private Long id;
    private String name;
    private String username;
    private String email;

    private String phone;
    private String website;
    private String dummy;

}