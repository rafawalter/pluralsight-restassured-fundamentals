package com.mypluralsight.restassuredFundamentals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static org.testng.Assert.assertEquals;

public class _61MappingWithObjectMappers {

    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMappingRelyingOnMapperType() {

        User user = RestAssured.get(BASE_URL)
                .as(User.class, ObjectMapperType.JACKSON_2);

        assertEquals(user.getLogin(), "rest-assured");
    }

    @Test
    public void objectMappingUsingSpecifiedMapper() {

        io.restassured.mapper.ObjectMapper mapper = new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });

        UserWithoutMapping user = RestAssured.get(BASE_URL)
                .as(UserWithoutMapping.class, mapper);

        assertEquals(user.login, "rest-assured");
    }

    @Test
    public void usingGetMapper() {
        io.restassured.mapper.ObjectMapper mapper = this.getMapper();

        UserWithoutMapping user = RestAssured.get(BASE_URL)
                .as(UserWithoutMapping.class, mapper);

        assertEquals(user.login, "rest-assured");
    }

    io.restassured.mapper.ObjectMapper getMapper() {
        return new Jackson2Mapper((type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }

}
