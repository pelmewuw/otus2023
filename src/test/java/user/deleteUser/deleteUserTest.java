package user.deleteUser;

import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import services.UserApi;

import static data.UserData.USER_USERNAME;
import static org.hamcrest.Matchers.equalTo;

public class deleteUserTest {

  @Test
  public void checkDeleteUserValidUsername(){
    //Проверка удаления пользователя по валидному username

    //Создаем пользователя
    UserApi userApi = new UserApi();
    String username = USER_USERNAME+"checkDeleteUserValidUsername";

    UserDTO user = UserDTO.builder()
        .username(username)
        .build();

    // Проверяем результат выполнения создания(статус код, валидация по схеме, возвращенные поля)
    userApi.createUser(user)
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"));

    //Удаляем пользователя, проверяем что статус код 200
    userApi.deleteUser(username)
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"))
        .body("message", equalTo(username));

    //Проверяем, что пользователь не находится по данному username
    userApi.getUser(username)
        .statusCode(HttpStatus.SC_NOT_FOUND)
        .body("code", equalTo(1))
        .body("type", equalTo("error"))
        .body("message", equalTo("User not found"));


  }


  @Test
  public void checkDeleteUserNotFoundUsername(){
    //Проверка удаления пользователя по ненайденному username
    UserApi userApi = new UserApi();

    //Пытаемся удалить пользователя по несуществующему username. Проверяем что получем статус код 404 Not Found
    userApi.deleteUser("!!!")
        .statusCode(HttpStatus.SC_NOT_FOUND);

  }
}
