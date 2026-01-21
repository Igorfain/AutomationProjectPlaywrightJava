package apitests.tests.fakerestapi;

import apitests.BaseApiTest;
import apitests.helpers.ApiRequestHelper;
import apitests.helpers.ResponseValidator;
import apitests.services.Endpoints;
import org.testng.annotations.Test;

public class FakeRestApiTests extends BaseApiTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private final ResponseValidator responseValidator = new ResponseValidator();

    @Test(description = "Verify that requesting a non-existent book returns a 'Not Found' response")
    public void testGetNonExistentBookDetails() {
        logTestStep();
        var response = apiRequestHelper.sendGetRequest(FAKE_REST_API_URI,Endpoints.GET_BOOK_BY_ID("1111111"));
        responseValidator.validateBookNotFoundResponse(response);
    }

    @Test(description = "Verify that requesting author details by ID returns the correct author data")
    public void testGetAuthorById() {
        logTestStep();
        var response = apiRequestHelper.sendGetRequest(FAKE_REST_API_URI,Endpoints.GET_AUTHOR_BY_ID("1"));
        responseValidator.validateAuthorResponse(response);
    }

    @Test(description = "Verify that requesting cover photos by book ID returns valid cover photo data")
    public void testGetCoverPhotoByBookId() {
        logTestStep();
        var response = apiRequestHelper.sendGetRequest(FAKE_REST_API_URI,Endpoints.GET_COVER_PHOTOS_BY_ID_BOOK("1"));
        responseValidator.validateCoverPhotosResponse(response);
    }



}
