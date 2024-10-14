package com.bootcamp.bc_forum.dto;

import com.bootcamp.bc_forum.dto.UserDTO.Address.Geo;
import java.util.ArrayList;
import java.util.List;
import com.bootcamp.bc_forum.dto.UserDTO.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserPostCommentDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address; // One-to-one
    private String phone;
    private String website;
    private Company company;
    private List<Posts> posts = new ArrayList<>();
    //private List<Comments> comments = new ArrayList<>();  -> 係下面出

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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Company{
        private String name;
        private String catchPhrase;
        private String bs;
    }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Posts{
        private int id;
        private String title;
        private String body;
        List<Comments> comments = new ArrayList<>();

        public static class Comments{
            private int id;
            private String name;
            private String email;
            private String body;
        }
    }
}
