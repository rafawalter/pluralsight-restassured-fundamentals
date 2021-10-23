package com.mypluralsight.restassuredFundamentals;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class _6MappingPayloadDataToObjects {

    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void mavenDependencyOk() {
        ObjectMapper mapper = new ObjectMapper();
    }

    @Test
    public void objectMappingTestOne() {

        User user = RestAssured.get(BASE_URL)
                .as(User.class);

        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getPublicRepos(), 2);
    }
}
