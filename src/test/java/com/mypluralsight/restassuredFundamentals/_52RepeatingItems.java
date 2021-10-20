package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class _52RepeatingItems {

    public static final String BASE_URL = "https://reqres.in/api/users??page=1";

    @Test
    public void repeatingItems() {

        RestAssured.get(BASE_URL)
                .then()
//                .body("data.id", Matchers.equalTo(1)); // doesnt match
                .body("data.id[0]", Matchers.equalTo(1))
                .body("data.id[1]", Matchers.equalTo(2))
                .body("data.first_name[2]", Matchers.equalTo("Emma"))
                .body("data.first_name[3]", Matchers.equalTo("Eve"))
//                .body("data.first_name", Matchers.containsInAnyOrder("Eve", "Emma")) // matches full array
                .body("data.first_name", Matchers.hasItem("Eve"))
                .body("data.first_name", Matchers.hasItems("Eve", "Emma"))
                .body("data.first_name", Matchers.hasItem(Matchers.endsWith("ma")));

    }
}
