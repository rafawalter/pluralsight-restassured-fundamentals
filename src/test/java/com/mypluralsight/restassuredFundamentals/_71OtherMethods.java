package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _71OtherMethods {

    public static final String BASE_URL = "https://api.github.com/user/repos";
    public static final String TOKEN = TokenHelper.get();

    @Test(description = "Create a repo")
    public void postTest() {

        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201);
    }

    @Test(
            description = "Update a repo",
            dependsOnMethods = "postTest"
    )
    public void patchTest() {

        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched\"}")
                .when()
                .patch("https://api.github.com/repos/rafawalter/deleteme")
                .then()
                .statusCode(200);
    }

    @Test(
            description = "Delete a repo",
            dependsOnMethods = "patchTest"
    )
    public void deleteTest() {

        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .when()
                .delete("https://api.github.com/repos/rafawalter/deleteme-patched")
                .then()
                .statusCode(204);
    }

}
