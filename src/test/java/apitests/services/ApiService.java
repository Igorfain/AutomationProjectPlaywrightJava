package apitests.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class ApiService {

    public Response sendGetRequest(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.get(endpoint);
    }

    public Response sendPostRequest(String endpoint, String body) {
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json");
        return httpRequest.body(body).post(endpoint);
    }
}
