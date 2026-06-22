package automationexercise.com.infra.testdata;

public class RegistrationData {

    private final String genderType;
    private final String day;
    private final String month;
    private final String year;
    private final String state;
    private final String countryValue;

    public RegistrationData() {
        genderType = "#id_gender1";
        day = "17";
        month = "June";
        year = "1993";
        state = "South";
        countryValue = "Israel";
    }

    public String getGenderType() {
        return genderType;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getState() {
        return state;
    }

    public String getCountryValue() {
        return countryValue;
    }
}