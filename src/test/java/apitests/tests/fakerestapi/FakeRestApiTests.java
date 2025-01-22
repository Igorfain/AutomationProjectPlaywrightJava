package apitests.tests.fakerestapi;

import apitests.helpers.ApiRequestHelper;
import apitests.helpers.ResponseValidator;
import apitests.services.Endpoints;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FakeRestApiTests {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private final ResponseValidator responseValidator = new ResponseValidator();

    @BeforeClass
    public void setupBookTests() {
        // Устанавливаем базовый URI для API
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @Test
    public void testGetNonExistentBookDetails() { //Negative
        var response = apiRequestHelper.sendGetRequest(Endpoints.GET_BOOK_BY_ID("1111111"));
        responseValidator.validateBookNotFoundResponse(response);
    }

    @Test
    public void testGetAuthorById() {
        var response = apiRequestHelper.sendGetRequest(Endpoints.GET_AUTHOR_BY_ID("1"));
        responseValidator.validateAuthorResponse(response);
    }

    @Test
    public void testGetCoverPhotoByBookId() {
        var response = apiRequestHelper.sendGetRequest(Endpoints.GET_COVER_PHOTOS_BY_ID_BOOK("1"));
        responseValidator.validateCoverPhotosResponse(response);
    }



}
