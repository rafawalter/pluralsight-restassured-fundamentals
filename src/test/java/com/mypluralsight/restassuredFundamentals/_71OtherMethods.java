package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _71OtherMethods {

    public static final String BASE_URL = "https://api.github.com/user/repos";
    public static final String TOKEN = "";

    @Test(description = "Create a repo")
    public void optionsTest() {

        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201);
    }
}
