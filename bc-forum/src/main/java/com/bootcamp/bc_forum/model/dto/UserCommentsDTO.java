package com.bootcamp.bc_forum.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentsDTO {
    private Long id;
    private String userName;
    private List<CommentDTO2> commentDto2 = new ArrayList<>();


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentDTO2 {
        private Long id;
        private String body;
    }

}
