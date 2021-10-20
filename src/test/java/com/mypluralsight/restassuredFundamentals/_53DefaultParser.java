package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class _53DefaultParser {

    public static final String BASE_URL = "https://api.github.com/";

    @Test(expectedExceptions = {AssertionError.class})
    public void parserAndSyntaticSugar() {
        RestAssured.get(BASE_URL)
                .then()
                .using()
                .defaultParser(Parser.HTML)
                .body("current_user_url", Matchers.equalTo(BASE_URL + "user"));
    }
}
