package apitests.config;

public class ApiMockConfig {

    private final String mockUrl;

    public ApiMockConfig() {
        this.mockUrl = "http://10.0.0.15:8080"; // WireMock address
    }

    public String getMockUrl() {
        return mockUrl;
    }
}
