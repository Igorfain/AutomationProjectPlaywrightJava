package automationexercise.com.infra.testdata;

public final class ContactUsData {

    private static final String name = "Sheldon Cooper";
    private static final String email = "sheldonCooper@mail.com";
    private static final String subject = "Automation feedback";
    private static final String message = "Great site for test practice!";
    private static final String filePath = "src/test/resources/test.txt";

    private ContactUsData() {}

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getSubject() {
        return subject;
    }

    public static String getMessage() {
        return message;
    }

    public static String getFilePath() {
        return filePath;
    }
}