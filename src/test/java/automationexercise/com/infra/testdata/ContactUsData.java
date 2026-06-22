package automationexercise.com.infra.testdata;

public class ContactUsData {

    private final String name;
    private final String email;
    private final String subject;
    private final String message;
    private final String filePath;

    public ContactUsData() {
        name = "Sheldon Cooper";
        email = "sheldonCooper@mail.com";
        subject = "Automation feedback";
        message = "Great site for test practice!";
        filePath = "src/test/resources/test.txt";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getFilePath() {
        return filePath;
    }
}