package com.bootcamp.bc_forum.model.dto;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentsDTO {
    private Integer id;
    private String userName;
    private List<CommentDTO2> commentDto2 = new ArrayList<>();


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentDTO2 {
        private Integer id;
        private String body;
    }

}
