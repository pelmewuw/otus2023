package com.otus.stubserver.controller;

import com.otus.stubserver.model.Score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/score")
public class ScoreController {

  @GetMapping("/{name}")
  public Score getUserById(@PathVariable String name) {
    return new Score(name, 1 + new Random().nextInt(100));
  }
}
