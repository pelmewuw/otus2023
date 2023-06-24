package com.otus.stubserver;

import com.otus.stubserver.services.CourseApi;
import com.otus.stubserver.services.ScoreApi;
import com.otus.stubserver.services.UserApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StubServerTests {

  @Test
  @DisplayName("/user/all - получение всех пользователей")
  public void getAllUser() {
    UserApi userApi = new UserApi();
    userApi.checkGetResponse(userApi.getAllUser());
  }

  @Test
  @DisplayName("/user/{id} - получение пользователя по id")
  public void getUserById() {
    UserApi user = new UserApi();
    user.checkGetResponse(user.getUserById(23l));
  }

  @Test
  @DisplayName("/course/all - получение всех курсов")
  public void getAllCourse() {
    CourseApi courseApi = new CourseApi();
    courseApi.checkGetResponse(courseApi.getAllCourse());
  }

  @Test
  @DisplayName("/score/{name} - получение оценки по имени пользователя")
  public void getScoreByName() {
    ScoreApi scoreApi = new ScoreApi();
    scoreApi.checkGetResponse(scoreApi.getScoreByName("David"));
  }
}
