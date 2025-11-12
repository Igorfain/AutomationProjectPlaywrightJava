package apitests.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiRequestHelper {

    public Response sendGetRequest(String baseUri, String endpoint) {
        return RestAssured
                .given()
                .baseUri(baseUri)
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendPostRequest(String baseUri, String endpoint, String body) {
        return RestAssured
                .given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendPutRequest(String baseUri, String endpoint, String body) {
        return RestAssured
                .given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendDeleteRequest(String baseUri, String endpoint) {
        return RestAssured
                .given()
                .baseUri(baseUri)
                .log().all()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
