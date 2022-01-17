package hw6.com.github;

import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;

public class TestBase {

    protected final String REPOSITORY = "/tbogatyrev/qa-guru";

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://github.com";
    }
}
