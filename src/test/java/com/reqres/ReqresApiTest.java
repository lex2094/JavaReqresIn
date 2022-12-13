package com.reqres;

import com.reqres.pojo.domain.User;
import com.reqres.pojo.domain.UserMapper;
import com.reqres.pojo.remote.UserDataRemote;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ReqresApiTest extends TestBase {

    @Test
    @DisplayName("Get user with id = 2")
    void getUserTest(){

        UserDataRemote response = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .get("api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .extract().as(UserDataRemote.class);

        User user = UserMapper.map(response.data);

        Assertions.assertEquals(user.contact.email, response.data.email);

    }
}
