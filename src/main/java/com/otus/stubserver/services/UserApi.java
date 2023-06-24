package com.otus.stubserver.services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
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

  public ValidatableResponse getAllUser() {
    return given(spec)
        .baseUri(BASE_URL)
        .log().all()
        .when()
        .get(USER + "/all")
        .then()
        .log().all();
  }

  public ValidatableResponse getUserById(Long id) {
    return given(spec)
        .baseUri(BASE_URL)
        .log().all()
        .when()
        .get(USER + "/" + id)
        .then()
        .log().all();
  }

  public ValidatableResponse checkGetResponse(ValidatableResponse response) {
    return response
        .statusCode(HttpStatus.SC_OK);
  }
}
