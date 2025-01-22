package apitests.services;

public class Endpoints {

    // Для JSONPlaceholder
    public static String GET_POST_BY_ID(String id) {
        return "/posts/" + id;
    }

    public static final String CREATE_POST = "/posts";



    // Для FakeRestApi
    public static String GET_BOOK_BY_ID(String id) {
        return "/api/v1/Books/" + id;
    }

    public static final String CREATE_BOOK = "/api/v1/Books";
    public static final String DELETE_BOOK = "/api/v1/Books/{id}";

    public static String UPDATE_BOOK(String id) {
        return "/api/v1/Books/" + id;
    }

    public static String GET_USER_BY_ID(String id) {
        return "/api/v1/Users/" + id;
    }

    public static final String CREATE_USER = "/api/v1/Users";
    public static final String DELETE_USER = "/api/v1/Users/{id}";

    public static String UPDATE_USER(String id) {
        return "/api/v1/Users/" + id;
    }

    public static String GET_AUTHOR_BY_ID(String authorId) {
        return "/api/v1/Authors/" + authorId;
    }

    public static String GET_COVER_PHOTOS_BY_ID_BOOK(String idBook){
        return "/api/v1/CoverPhotos/" + idBook;
    }

    public static String POST_COVER_PHOTOS_BY_ID_BOOK(String idBook){
        return "/api/v1/CoverPhotos/" + idBook;
    }

    // Добавьте другие эндпоинты при необходимости
}
