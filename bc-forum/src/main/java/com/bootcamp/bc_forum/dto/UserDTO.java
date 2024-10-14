package com.bootcamp.bc_forum.dto;

import org.springframework.stereotype.Indexed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private Address address; // One-to-one
    private String phone;
    private String website;
    private Company company; // One-to-one

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Address{
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class Geo {
        private String lat;
        private String lng;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Company{
        private String name;
        private String catchPhrase;
        private String bs;
    }
}
