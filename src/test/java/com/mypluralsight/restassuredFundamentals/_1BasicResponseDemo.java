package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class _1BasicResponseDemo {
    private static final String BASE_URL = "https://api.github.com";

    @Test
    void convinienceMethods() {
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    void genericHeader() {
        Response response = RestAssured.get(BASE_URL);

        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");

        assertEquals(Integer.parseInt(response.getHeader("x-ratelimit-limit")), 60);
    }

    @Test
    void getHeaders() {
        Response response = RestAssured.get(BASE_URL);
        Headers headers = response.getHeaders();
        String val = headers.getValue("header1");
        int size = headers.size();
        List<Header> list = headers.asList();
        boolean isPresent = headers.hasHeaderWithName("x-ratelimit-limit");
        assertTrue(isPresent);
    }

    @Test
    void timeExample() {
        Response response = RestAssured.get(BASE_URL);

        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));

        System.out.println(response.getTimeIn(TimeUnit.SECONDS)); // Trucated by Java TimeUnit :-(
        final long duration = response.getTime();
        System.out.println(DurationFormatUtils.formatDurationWords(duration, true, true));
        System.out.println(DurationFormatUtils.formatDuration(duration, "S"));
        System.out.println(DurationFormatUtils.formatDuration(duration, "s"));
    }
}
