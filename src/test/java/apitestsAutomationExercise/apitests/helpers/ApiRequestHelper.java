package apitestsAutomationExercise.apitests.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class ApiRequestHelper {

    /**
     * Sends a GET request to the specified endpoint.
     */
    public Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a POST request with JSON body.
     */
    public Response sendPostRequest(String endpoint, String requestBody) {
        return RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a GET request with query parameters.
     */
    public Response sendGetRequestWithQueryParam(String endpoint, String queryParamKey, String queryParamValue) {
        return RestAssured.given()
                .log().all()
                .queryParam(queryParamKey, queryParamValue)
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a POST request with form data (key-value pairs).
     */
    public Response sendPostRequestWithFormData(String endpoint, Map<String, String> formData) {
        return RestAssured.given()
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formData)
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a POST request with a single form parameter.
     */
    public Response sendPostFormRequest(String endpoint, String paramKey, String paramValue) {
        return RestAssured.given()
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam(paramKey, paramValue)
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a PUT request with form data (key-value pairs).
     */
    public Response sendPutRequestWithFormData(String endpoint, Map<String, String> formData) {
        return RestAssured.given()
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formData)
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
