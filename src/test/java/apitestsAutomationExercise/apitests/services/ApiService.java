package apitestsAutomationExercise.apitests.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ApiService {

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the GET request to.
     * @return Response object containing the server's response.
     */
    public Response sendGetRequest(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.get(endpoint);
    }

    /**
     * Sends a GET request with query parameters to the specified endpoint.
     *
     * @param endpoint  The endpoint to send the GET request to.
     * @param queryParams A map of query parameters to include in the request.
     * @return Response object containing the server's response.
     */
    public Response sendGetRequestWithQueryParams(String endpoint, Map<String, String> queryParams) {
        RequestSpecification httpRequest = RestAssured.given().queryParams(queryParams);
        return httpRequest.get(endpoint);
    }

    /**
     * Sends a POST request with a JSON body to the specified endpoint.
     *
     * @param endpoint The endpoint to send the POST request to.
     * @param body     The JSON-formatted request body.
     * @return Response object containing the server's response.
     */
    public Response sendPostRequest(String endpoint, String body) {
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json");
        return httpRequest.body(body).post(endpoint);
    }

    /**
     * Sends a POST request with form data to the specified endpoint.
     *
     * @param endpoint  The endpoint to send the POST request to.
     * @param formData  A map of form data to include in the request.
     * @return Response object containing the server's response.
     */
    public Response sendPostRequestWithFormData(String endpoint, Map<String, String> formData) {
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.formParams(formData).post(endpoint);
    }

    /**
     * Sends a POST request with headers to the specified endpoint.
     *
     * @param endpoint  The endpoint to send the POST request to.
     * @param headers   A map of headers to include in the request.
     * @param body      The JSON-formatted request body.
     * @return Response object containing the server's response.
     */
    public Response sendPostRequestWithHeaders(String endpoint, Map<String, String> headers, String body) {
        RequestSpecification httpRequest = RestAssured.given().headers(headers);
        return httpRequest.body(body).post(endpoint);
    }

    /**
     * Sends a DELETE request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the DELETE request to.
     * @return Response object containing the server's response.
     */
    public Response sendDeleteRequest(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.delete(endpoint);
    }
}
