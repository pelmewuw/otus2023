package com.otus.stubserver.controller;

import com.otus.stubserver.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/all")
  public List<User> getAllUser() {
    return LongStream
        .range(1, 20)
        .mapToObj(val -> new User(val, "name" + val, "course" + val, val + "@test.test", 1 + new Random().nextInt(160)))
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return new User(id, "name" + id, "course" + id, id + "@test.test", 1 + new Random().nextInt(60));
  }

}
