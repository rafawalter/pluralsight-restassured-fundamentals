package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class _5ValidatableResponse {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void bodyMatcher() {

        RestAssured.get(BASE_URL)
                .then()
                .body("current_user_url", Matchers.equalTo(BASE_URL + "/user"))
                .body(containsString("feeds_url"))
                .body(containsString("feeds_url"), containsString("current_user_url"));
    }

    @Test
    public void complexBodyMatcher() {
        RestAssured.get(BASE_URL + "/users/andrejs-ps")
                .then()
                .body("url", Matchers.containsString("andrejs-ps"))
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }

    @DataProvider
    public Object[][] names() {
        return new Object[][]{
                {"andrejs-ps"},
                {"rest-assured"}
        };
    }

    @Test(dataProvider = "names")
    public void complexBodyMatcherWithDataProvider(String name) {
        RestAssured.get(BASE_URL + "/users/" + name)
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }
}
