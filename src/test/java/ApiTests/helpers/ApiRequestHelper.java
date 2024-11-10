package ApiTests.helpers;

import io.restassured.response.Response;
import io.restassured.RestAssured;

public class ApiRequestHelper {

    public Response sendGetRequest(String endpoint) {
        return RestAssured.given().get(endpoint);
    }

    public Response sendPostRequest(String endpoint, String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(endpoint);
    }

    public Response sendPutRequest(String endpoint, String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put(endpoint);
    }

    public Response sendDeleteRequest(String endpoint) {
        return RestAssured.given().delete(endpoint);
    }

    public Response sendGetRequestWithAuth(String endpoint, String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(endpoint);
    }

    public Response sendPostRequestWithAuth(String endpoint, String token, String requestBody) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(endpoint);
    }

}
