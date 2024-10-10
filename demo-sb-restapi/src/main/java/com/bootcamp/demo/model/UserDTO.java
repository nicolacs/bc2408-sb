package com.bootcamp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
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
