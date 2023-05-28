package services;


import dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class UserApi {

  private static final String BASE_URL = "https://petstore.swagger.io/v2/";
  private static final String USER = "/user";
  private RequestSpecification spec;

  public UserApi(){
    spec=given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON);
  }

  public ValidatableResponse createUser(UserDTO userDTO) {
    return given(spec)
            .log().all()
            .body(userDTO)
        .when()
            .post(USER)
        .then()
            .log().all();
  }

  public ValidatableResponse getUser(String username){
    return given(spec)
        .log().all()
        .when()
        .get(USER+"/"+username)
        .then()
        .log().all();
  }

  public ValidatableResponse deleteUser(String username){
    return given(spec)
        .log().all()
        .when()
        .delete(USER+"/"+username)
        .then()
        .log().all();
  }

}
