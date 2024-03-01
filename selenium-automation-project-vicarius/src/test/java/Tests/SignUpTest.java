package Tests;

import basicOperation.BasicHandler;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporter.TestNGReportListener;
import vicariusTests.TestBase;
import vicariusTests.WebDriverManager;

@Listeners(TestNGReportListener.class)
public class SignUpTest extends TestBase {

    @Test
    public void testSignUpPageElements() {     //test 1 - sing up successfully e2e
        try {
            driver.get("https://www.vicarius.io/sign/up");
            driver.manage().window().maximize();
            // take screenShot
            BasicHandler.fillUserDetails("Niv","Sapir","nivsap@radware.com","Nvidia");
            TestNGReportListener.captureScreenshot(driver, "after_action");
            TestNGReportListener.test.pass("step 1 - fill login page");

            // Verify buttons and click continue
            WebDriverManager.waitForElement(By.xpath("//button[contains(., 'Continue')]")).click();
            BasicHandler.fillPasswordDetails("Nivniv123!");
            TestNGReportListener.test.pass("step 2 - fill passwords");
            WebDriverManager.waitForElement(By.xpath("//button[contains(., 'Start Free 14-days Trial')]")).click();

            BasicHandler.verifyThirdPageOfSingUpDetails();
            TestNGReportListener.test.pass("step 3 - verify sign in success");
            TestNGReportListener.captureScreenshot(driver, "before_action");
            Thread.sleep(1000);
            TestNGReportListener.test.pass("test passed");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }

    }
    @Test
    public void testSignUpPageVerifyErrors() {
        try {
            driver.get("https://www.vicarius.io/sign/up");   // test 2 - sing up with wrong data
            driver.manage().window().maximize();
            BasicHandler.fillUserDetails("tamara","okook","will-played","123pijsdf");
            TestNGReportListener.test.pass("step 1 - fill data with wrong email");
            BasicHandler.verifyErrors();
            TestNGReportListener.test.pass("step 2 - verify errors");
            WebDriverManager.waitForElement(By.xpath("//button[contains(., 'Continue')]")).click();
            if(!WebDriverManager.waitForElement(By.xpath("//div[contains(@class, 'notification-inner')]")).isDisplayed()){
                TestNGReportListener.test.fail("there is no pop up massage");
            }
            TestNGReportListener.test.pass("step 3 - verify error pop ups after press continue");
            Thread.sleep(1000);
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testSignUpPageVerifyPasswordErrors() {     // test 3 - verify sing up passwords page.
        try {
            driver.get("https://www.vicarius.io/sign/up");
            driver.manage().window().maximize();
            BasicHandler.fillUserDetails("Niv","Sapir","nivsap@radware.com","Nvidia");
            TestNGReportListener.test.pass("step 1 - fill data with properly mail");
            WebDriverManager.waitForElement(By.xpath("//button[contains(., 'Continue')]")).click();
            String[] arr = {"niv123!","sdfd","nivniv","  "};
            for(String s : arr){
                BasicHandler.fillPasswordDetails(s);
                BasicHandler.verifyErrors();
                Thread.sleep(100);
            }
            TestNGReportListener.test.pass("step 2 - verify password errors");
            WebDriverManager.waitForElement(By.xpath("//button[contains(., 'Start Free 14-days Trial')]")).click();
            if(!WebDriverManager.waitForElement(By.xpath("//div[contains(@class, 'notification-inner')]")).isDisplayed()){
                TestNGReportListener.test.fail("there is no pop up massage");
            }
            TestNGReportListener.test.pass("step 3 - try continue without fit password and got pop up");
            Thread.sleep(1000);
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }

    }


}
