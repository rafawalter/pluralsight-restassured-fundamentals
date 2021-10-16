package com.mypluralsight.restassuredFundamentals;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _0PeekAndPrintDemo {
    private static final String ROOT_URL = "https://api.github.com";

    //
    // Show request headers and response
    //
    @Test
    public void peek() {
        RestAssured.get(ROOT_URL)
                .peek();
    }

    @Test
    public void prettyPeek() {
        RestAssured.get(ROOT_URL)
                .prettyPeek();
    }

    //
    // Show request headers
    //
    @Test
    public void print() {
        RestAssured.get(ROOT_URL)
                .print();
    }

    @Test
    public void prettyPrint() {
        RestAssured.get(ROOT_URL)
                .prettyPrint();
    }
}
