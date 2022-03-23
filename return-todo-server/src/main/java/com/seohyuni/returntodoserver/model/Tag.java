package com.seohyuni.returntodoserver.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

  /**
   * 식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 이름
   */
  @Column
  private String name;

  /**
   * 색상코드
   */
  @Column
  private String colorCode;

  @OneToMany(mappedBy = "tag")
  private List<Todo> todoList;

}
