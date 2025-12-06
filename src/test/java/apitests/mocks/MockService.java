package apitests.mocks;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MockService {

    private final String baseUrl;

    public MockService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getUser(int id) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .when()
                .get("/user/" + id)
                .then()
                .extract()
                .response();
    }

    public Response get(String path) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response post(String path, String body) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public Response put(String path, String body) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(path)
                .then()
                .extract()
                .response();
    }

    public Response delete(String path) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }

    public Response deleteUser(int id) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .when()
                .delete("/user/delete/" + id)
                .then()
                .extract()
                .response();
    }
}
