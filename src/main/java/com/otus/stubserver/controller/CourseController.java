package com.otus.stubserver.controller;

import com.otus.stubserver.model.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/course")
public class CourseController {

  @GetMapping("/all")
  private List<Course> getAllCourse() {
    return LongStream
        .range(0, 20)
        .mapToObj(val -> new Course("name" + val, 1000 + new Random().nextInt(10000)))
        .collect(Collectors.toList());
  }

}
