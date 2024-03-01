package Tests;

import basicOperation.BasicHandler;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporter.TestNGReportListener;
import vicariusTests.TestBase;
import vicariusTests.WebDriverManager;

@Listeners(TestNGReportListener.class)
public class SignInTest extends TestBase {
    @Test
    public void testSignInPageElements1() {  // test 1 - sign in with none@none mail and wait for error msg
        try {
            driver.get("https://www.vicarius.io/sign/in");
            driver.manage().window().maximize();
            //none mail
            TestNGReportListener.test.pass("step 1 - start test with none@none mail");
            BasicHandler.sendWorgMail("none@none","input24","//button[contains(., 'Login')]","//div[contains(text(), 'Please, verify if all fields are correctly filled.')]");
            TestNGReportListener.captureScreenshot(driver, "after_action");
            Thread.sleep(1000);
            TestNGReportListener.test.pass("step 2 - got error when tried to sign in with non exist user");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testSignInPageElements2() {   //  test 2 - sign in with empty mail and wait for error msg
        try {
            //without mail
            driver.get("https://www.vicarius.io/sign/in");
            driver.manage().window().maximize();
            TestNGReportListener.test.pass("step 1 - start test without mail data");
            BasicHandler.sendWorgMail("  ","input24","//button[contains(., 'Login')]","//div[contains(text(), 'Please, verify if all fields are correctly filled.')]");
            TestNGReportListener.captureScreenshot(driver, "after_action");
            Thread.sleep(1000);
            TestNGReportListener.test.pass("step 2 - got error when tried to sign in with empty email address");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testSignInPageElements3() {   //test 3 - sign in with mail that wait to verification and wait for proper error.
        try {
            driver.get("https://www.vicarius.io/sign/in");
            driver.manage().window().maximize();
            TestNGReportListener.test.pass("step 1 - start test with mail that waits for verification");
            BasicHandler.sendWorgMail("nivsap@radware.com","input24","//button[contains(., 'Login')]","//div[contains(text(), 'If the email address exists and ')]");
            TestNGReportListener.captureScreenshot(driver, "after_action");
            Thread.sleep(1000);
            TestNGReportListener.test.pass("step 2 - got message that the user waits to be approved as expected");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testSignInPageVerifyElements() {    //test 4 - verify all element on sign in page.
        try {
            driver.get("https://www.vicarius.io/sign/in");
            driver.manage().window().maximize();
            TestNGReportListener.test.pass("step 1 - verify 'sign up' header button");

            BasicHandler.verifyIfButtonIsEnabledAndDisplayed("//a[@href='/sign/up']");

            TestNGReportListener.test.pass("step 2 - verify 'forgot mail' button");
            BasicHandler.verifyIfButtonIsEnabledAndDisplayed("//a[@class='forgot' and contains(text(), 'Forgot my e-mail')]");
            //Verify logo button
            WebElement svgElement = WebDriverManager.waitForElementByCssSelector("[class*='logo']");
            boolean isDisplayed = svgElement.isDisplayed();
            if (isDisplayed) {
                TestNGReportListener.test.pass("step 3 - verify 'logo' header button");
            } else {
                TestNGReportListener.test.fail("Logo main button not working properly");
            }
            Thread.sleep(1000);
            TestNGReportListener.test.pass("all elements as excepted");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }


}
