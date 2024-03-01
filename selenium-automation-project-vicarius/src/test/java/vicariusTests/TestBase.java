package vicariusTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = WebDriverManager.getDriver(); // singleton
    }

    @AfterSuite
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
