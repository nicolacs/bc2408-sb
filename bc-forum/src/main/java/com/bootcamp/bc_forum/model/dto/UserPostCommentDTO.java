package com.bootcamp.bc_forum.model.dto;

import java.util.ArrayList;
import java.util.List;
import com.bootcamp.bc_forum.model.dto.UserDTO.Company;
import com.bootcamp.bc_forum.model.dto.UserDTO.Address.Geo;
import com.bootcamp.bc_forum.model.dto.UserPostCommentDTO.AddressDTO.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class UserPostCommentDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressDTO address; // One-to-one
    private String phone;
    private String website;
    private CompanyDTO company;
    @Builder.Default
    private List<Posts> postDTO = new ArrayList<>();
    
    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddressDTO{
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private GeoDTO geo;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class GeoDTO {
        private String lat;
        private String lng;
        }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CompanyDTO{
        private String name;
        private String catchPhrase;
        private String bs;
    }
    }

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    public static class Posts{
        private Long postId;
        private String title;
        private String body;
        @Builder.Default
        private List<Comments> commentsDTO = new ArrayList<>();
    }

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    public static class Comments{
        private Long commentId;
        private String name;
        private String email;
        private String body;
    }
}
