package Tests;

import basicOperation.BasicHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporter.TestNGReportListener;
import vicariusTests.TestBase;
import vicariusTests.WebDriverManager;

@Listeners(TestNGReportListener.class)
public class ProductPageTest extends TestBase {
    @Test
    public void testProductPage() {   // test 1 - verify video work as expected.
        try{
            driver.get("https://www.vicarius.io/product");
            driver.manage().window().maximize();
            WebElement element = WebDriverManager.waitForElement(By.xpath("//span[contains(text(), 'Watch Demo')]"));
            if(!element.isDisplayed()){
                TestNGReportListener.test.fail("button watch demo is broke");
            }
            element.click();
            Thread.sleep(1000);
            BasicHandler.checkIfTheVideoIsPlaying("video-stream",driver);
            Thread.sleep(1000);
            Thread.sleep(1000);
            WebDriverManager.waitForElement(By.className("btn-close")).click();
            Thread.sleep(1000);
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testProductPageVerifyTextAndButtons() {  // test 2 - verify element on product page.
        try{
            driver.get("https://www.vicarius.io/product");
            driver.manage().window().maximize();
            BasicHandler.verifyIfButtonAndText(By.className("mock-wrapper"));
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }
    }
}
