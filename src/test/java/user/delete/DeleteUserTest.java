package user.delete;

import static data.UserData.USER_USERNAME;

import dto.UserDTO;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class DeleteUserTest {

  @Test
  public void checkDeleteUserValidUsername() {
    //Проверка удаления пользователя по валидному username

    //Создаем пользователя
    UserApi userApi = new UserApi();
    String username = USER_USERNAME + "checkDeleteUserValidUsername";

    UserDTO user = UserDTO.builder()
        .username(username)
        .build();

    // Проверяем результат выполнения создания(статус код, валидация по схеме, возвращенные поля)
    userApi.checkCreateResponse(userApi.createUser(user));

    //Удаляем пользователя, проверяем что статус код 200
    userApi.checkDeleteResponse(userApi.deleteUser(username));

    //Проверяем, что пользователь не находится по данному username
    userApi.checkNotFoundGetResponse(userApi.getUser(username));


  }


  @Test
  public void checkDeleteUserNotFoundUsername() {
    //Проверка удаления пользователя по ненайденному username
    UserApi userApi = new UserApi();

    //Пытаемся удалить пользователя по несуществующему username. Проверяем что получем статус код 404 Not Found
    userApi.checkNotFoundDeleteResponse(userApi.deleteUser("!!!"));

  }
}
