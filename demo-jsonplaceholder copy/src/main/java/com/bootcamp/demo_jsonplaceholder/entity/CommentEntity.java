package com.bootcamp.demo_jsonplaceholder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
@Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long id;
  private String name;
  private String email;
  @Column(length = 1000) // default 255
  private String body;

  @ManyToOne
  @JoinColumn(name ="post_id", nullable = false)
  private PostEntity post;  // 成個POST OBJ放入去就有.getId, 就認到
}
