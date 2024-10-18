package com.bootcamp.demo_jsonplaceholder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address; // One-to-one
    private String phone;
    private String website;
    private Company company; // One-to-one

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address{
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Geo {
        private String lat;
        private String lng;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Company{
        private String name;
        private String catchPhrase;
        private String bs;
    }
}
