package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class UserApi {

  private static final String BASE_URL = System.getProperty("base.url");
  private static final String USER = "/user";
  private RequestSpecification spec;

  public UserApi() {
    spec = given()
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

  public ValidatableResponse getUser(String username) {
    return given(spec)
        .log().all()
        .when()
        .get(USER + "/" + username)
        .then()
        .log().all();
  }

  public ValidatableResponse deleteUser(String username) {
    return given(spec)
        .log().all()
        .when()
        .delete(USER + "/" + username)
        .then()
        .log().all();
  }

  public ValidatableResponse checkCreateResponse(ValidatableResponse response){
    return response
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"));
  }

  public ValidatableResponse checkGetResponse(ValidatableResponse response){
    return response
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GetUser.json"));
  }
  public ValidatableResponse checkNotFoundGetResponse(ValidatableResponse response){
    return response
        .statusCode(HttpStatus.SC_NOT_FOUND)
        .body("code", equalTo(1))
        .body("type", equalTo("error"))
        .body("message", equalTo("User not found"));
  }

  public ValidatableResponse checkDeleteResponse(ValidatableResponse response){
    return response
        .statusCode(HttpStatus.SC_OK)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"));
  }

  public ValidatableResponse checkNotFoundDeleteResponse(ValidatableResponse response){
    return response
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

}
