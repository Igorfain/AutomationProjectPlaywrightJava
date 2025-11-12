package apitests.tests.fakerestapi;

import apitests.BaseTest;
import apitests.helpers.ApiRequestHelper;
import apitests.helpers.ResponseValidator;
import apitests.services.Endpoints;
import org.testng.annotations.Test;

   public class FakeRestApiTests extends BaseTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private final ResponseValidator responseValidator = new ResponseValidator();

    @Test
    public void testGetNonExistentBookDetails() { //Negative
        var response = apiRequestHelper.sendGetRequest(FAKE_REST_API_URI,Endpoints.GET_BOOK_BY_ID("1111111"));
        responseValidator.validateBookNotFoundResponse(response);
    }

    @Test
    public void testGetAuthorById() {
        var response = apiRequestHelper.sendGetRequest(FAKE_REST_API_URI,Endpoints.GET_AUTHOR_BY_ID("1"));
        responseValidator.validateAuthorResponse(response);
    }

    @Test
    public void testGetCoverPhotoByBookId() {
        var response = apiRequestHelper.sendGetRequest(FAKE_REST_API_URI,Endpoints.GET_COVER_PHOTOS_BY_ID_BOOK("1"));
        responseValidator.validateCoverPhotosResponse(response);
    }



}
