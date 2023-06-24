package com.otus.stubserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private Long id;
  private String name;
  private String course;
  private String email;
  private Integer age;

}
