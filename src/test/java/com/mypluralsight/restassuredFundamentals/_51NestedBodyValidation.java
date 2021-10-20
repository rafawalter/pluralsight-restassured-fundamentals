package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class _51NestedBodyValidation {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void nestedBodyValidation() {

        RestAssured.get(BASE_URL + "rate_limit")
                .then()

                .rootPath("resources.core")
                .body("limit", Matchers.equalTo(60))
                .body("remaining", Matchers.lessThanOrEqualTo(60))
                .body("reset", Matchers.notNullValue())

                .rootPath("resources.search")
                .body("limit", Matchers.equalTo(10))
                .body("remaining", Matchers.lessThanOrEqualTo(10))

                .noRootPath()
                .body("resources.graphql.limit", Matchers.is(0));
    }
}
