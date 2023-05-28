package user.createUser;

import dto.UserDTO;
import dto.UserResponseDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

import static data.UserData.*;
import static org.hamcrest.Matchers.equalTo;

public class createUserTest {

  @Test
  public void checkCreateUser(){
    UserApi userApi = new UserApi();

    // Создаем юзера с заполненными всеми полями
    UserDTO user = UserDTO.builder()
        .id(USER_ID)
        .email(USER_EMAIL)
        .firstName(USER_FIRST_NAME)
        .lastName(USER_LAST_NAME)
        .password(USER_PASSWORD)
        .userStatus(USER_USER_STATUS)
        .phone(USER_PHONE)
        .username(USER_USERNAME)
        .build();

    // Проверяем результат выполнения создания(статус код, валидация по схеме, возвращенные поля)
    userApi.createUser(user)
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"))
        .body("message", equalTo(USER_ID.toString()));

    // Получаем созданного юзера по username
    ValidatableResponse response = userApi.getUser(USER_USERNAME)
        .statusCode(HttpStatus.SC_OK);

    UserResponseDTO userResponseDTO = response.extract().body().as(UserResponseDTO.class);

    // Проверяем возращенные поля на то, что заполнены введенными значениями
    Assertions.assertAll("Check create user",
        () -> Assertions.assertEquals(USER_ID, userResponseDTO.getId(), "Incorrect ID"),
        () -> Assertions.assertEquals(USER_FIRST_NAME, userResponseDTO.getFirstName(), "Incorrect FIRST_NAME"),
        () -> Assertions.assertEquals(USER_LAST_NAME, userResponseDTO.getLastName(), "Incorrect LAST_NAME"),
        () -> Assertions.assertEquals(USER_PASSWORD, userResponseDTO.getPassword(), "Incorrect PASSWORD"),
        () -> Assertions.assertEquals(USER_USER_STATUS, userResponseDTO.getUserStatus(), "Incorrect USER_STATUS"),
        () -> Assertions.assertEquals(USER_PHONE, userResponseDTO.getPhone(), "Incorrect PHONE"),
        () -> Assertions.assertEquals(USER_EMAIL, userResponseDTO.getEmail(), "Incorrect EMAIL"),
        () -> Assertions.assertEquals(USER_USERNAME, userResponseDTO.getUsername(), "Incorrect USERNAME")
    );
  }

  @Test
  public void checkCreateUserOnlyUserName(){
    UserApi userApi = new UserApi();
    String username = USER_USERNAME+"test2";
    // Создаем юзера с заполнгением только поля username
    UserDTO user = UserDTO.builder()
        .username(username)
        .build();

    // Проверяем результат выполнения создания(статус код, валидация по схеме, возвращенные поля)
    userApi.createUser(user)
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"));

  // Получаем созданного юзера по username
  ValidatableResponse response = userApi.getUser(username)
      .statusCode(HttpStatus.SC_OK);

    UserResponseDTO userResponseDTO = response.extract().body().as(UserResponseDTO.class);

  // Проверяем возращенные поля на то, что заполнено только username и id, в остальных полях дефолтные значения
    Assertions.assertAll("Check create user",
      () -> Assertions.assertNotNull( userResponseDTO.getId(), "Incorrect ID"),
      () -> Assertions.assertNull(userResponseDTO.getFirstName(), "Incorrect FIRST_NAME"),
      () -> Assertions.assertNull(userResponseDTO.getLastName(), "Incorrect LAST_NAME"),
      () -> Assertions.assertNull(userResponseDTO.getPassword(), "Incorrect PASSWORD"),
      () -> Assertions.assertEquals(0,userResponseDTO.getUserStatus(), "Incorrect USER_STATUS"),
      () -> Assertions.assertNull(userResponseDTO.getPhone(), "Incorrect PHONE"),
      () -> Assertions.assertNull(userResponseDTO.getEmail(), "Incorrect EMAIL"),
      () -> Assertions.assertEquals(username, userResponseDTO.getUsername(), "Incorrect USERNAME")
      );

  }
}
