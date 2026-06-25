package apitests.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiMockConfig {

    private final String mockUrl;

    public ApiMockConfig() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        this.mockUrl = dotenv.get("WIREMOCK_URL");

        if (this.mockUrl == null || this.mockUrl.isBlank()) {
            throw new IllegalStateException("WIREMOCK_URL is not configured");
        }
    }

    public String getMockUrl() {
        return mockUrl;
    }
}