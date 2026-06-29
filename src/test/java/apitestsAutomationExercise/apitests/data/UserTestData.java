package apitestsAutomationExercise.apitests.data;

import java.util.HashMap;
import java.util.Map;

public final class UserTestData {

    private UserTestData() {}

    public static Map<String, String> buildLoginFormData(String email, String password) {
        Map<String, String> loginFormData = new HashMap<>();
        loginFormData.put("email", email);
        loginFormData.put("password", password);
        return loginFormData;
    }

    public static Map<String, String> buildUpdateUserFormData(String email, String password) {
        Map<String, String> updateUserFormData = new HashMap<>();
        updateUserFormData.put("email", email);
        updateUserFormData.put("password", password);
        updateUserFormData.put("name", "TestUser");
        updateUserFormData.put("title", "Mr");
        updateUserFormData.put("birth_date", "10");
        updateUserFormData.put("birth_month", "January");
        updateUserFormData.put("birth_year", "1990");
        updateUserFormData.put("firstname", "Igor");
        updateUserFormData.put("lastname", "Test");
        updateUserFormData.put("company", "TestCorp");
        updateUserFormData.put("address1", "123 Test Lane");
        updateUserFormData.put("address2", "Apt 4B");
        updateUserFormData.put("country", "United States");
        updateUserFormData.put("zipcode", "10001");
        updateUserFormData.put("state", "New York");
        updateUserFormData.put("city", "New York");
        updateUserFormData.put("mobile_number", "1234567890");
        return updateUserFormData;
    }
}
