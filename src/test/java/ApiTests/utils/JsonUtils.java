package ApiTests.utils;

public class JsonUtils {

    public static String createPostRequestBody(String title, String body, int userId) {
        return "{" +
                "\"title\": \"" + title + "\"," +
                "\"body\": \"" + body + "\"," +
                "\"userId\": " + userId +
                "}";
    }

    public static String createPostRequestBodyAuthors(int id, String idBook, String url) {
        return "{" +
                "\"id\": \"" + id + "\"," +
                "\"idBook\": \"" + idBook + "\"," +
                "\"url\": " + url +
                "}";
    }
}
