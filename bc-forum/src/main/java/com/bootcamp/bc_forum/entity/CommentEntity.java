package com.bootcamp.bc_forum.entity;

import java.io.Serializable;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter 
public class CommentEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  @Column(length = 1000) // default 255
  private String body;

  //FK setting method
  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private PostEntity post; // getId() -> table     
}
