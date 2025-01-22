package automationexercise.com.apitests.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiRequestHelper {

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the GET request to.
     * @return Response object containing the server's response.
     */
    public Response sendGetRequest(String endpoint) {
        return RestAssured.given().get(endpoint);
    }

    /**
     * Sends a POST request to the specified endpoint with a JSON-formatted request body.
     *
     * @param endpoint    The endpoint to send the POST request to.
     * @param requestBody The JSON-formatted request body.
     * @return Response object containing the server's response.
     */
    public Response sendPostRequest(String endpoint, String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(endpoint);
    }

    /**
     * Sends a GET request with query parameters.
     *
     * @param endpoint  The endpoint to send the GET request to.
     * @param queryParamKey   The query parameter key.
     * @param queryParamValue The query parameter value.
     * @return Response object containing the server's response.
     */
    public Response sendGetRequestWithQueryParam(String endpoint, String queryParamKey, String queryParamValue) {
        return RestAssured.given()
                .queryParam(queryParamKey, queryParamValue)
                .get(endpoint);
    }

    /**
     * Sends a POST request with form data.
     *
     * @param endpoint The endpoint to send the POST request to.
     * @param formData The form data as key-value pairs.
     * @return Response object containing the server's response.
     */
    public Response sendPostRequestWithFormData(String endpoint, Map<String, String> formData) {
        return RestAssured.given()
                .formParams(formData)
                .post(endpoint);
    }
}
