package com.otus.stubserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
  private String name;
  private Integer score;
}
