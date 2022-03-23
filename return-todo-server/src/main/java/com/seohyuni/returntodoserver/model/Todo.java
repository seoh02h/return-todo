package com.seohyuni.returntodoserver.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

  /**
   * 식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 내용
   */
  @Column
  private String content;

  /**
   * 완료여부
   */
  @Column
  private Boolean complete;

  /**
   * 생성일시
   */
  @Column
  private LocalDateTime createdAt;

  @ManyToOne(targetEntity = Tag.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_id", nullable = true)
  private Tag tag;

  @PrePersist
  public void onPrePersist() {
    this.complete = false;
    this.createdAt = LocalDateTime.now();
  }

  public void toggleComplete() {
    this.complete = !this.complete;
  }

  public Long getTagId() {
    return this.tag != null ? this.tag.getId() : null;
  }

  public String getTagName() {
    return this.tag != null ? this.tag.getName() : null;
  }

  public String getTagColorCode() {
    return this.tag != null ? this.tag.getColorCode() : null;
  }

}
