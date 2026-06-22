package automationexercise.com.infra.testdata;

public final class RegistrationData {

    private static final String day = "17";
    private static final String month = "June";
    private static final String year = "1993";
    private static final String state = "South";
    private static final String countryValue = "Israel";

    private RegistrationData() {}

    public static String getDay() {
        return day;
    }

    public static String getMonth() {
        return month;
    }

    public static String getYear() {
        return year;
    }

    public static String getState() {
        return state;
    }

    public static String getCountryValue() {
        return countryValue;
    }
}