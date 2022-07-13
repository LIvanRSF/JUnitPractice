import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestClass {

    @BeforeAll
    static void setUpAll() {
        Configuration.baseUrl = "https://ru.investing.com/";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    public void setUp() {
        Selenide.open("");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
