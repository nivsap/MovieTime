package Tests;

import basicOperation.BasicHandler;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporter.TestNGReportListener;
import vicariusTests.TestBase;

//verify time response to each page
// We could take this scenario move step forward and validate the response time when we are under attack!!!

@Listeners(TestNGReportListener.class)
public class PageLoadTimeRespone extends TestBase {
    @Test
    public void testTimeResponeSignUp() {
        try {
            TestNGReportListener.test.pass("loading page");
            driver.get("https://www.vicarius.io/sign/up");
            BasicHandler.testTimeResponeProduct(driver);
            TestNGReportListener.test.pass("test done");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }

    }
    @Test
    public void testTimeResponeProduct()  {
        try {
            TestNGReportListener.test.pass("loading page");
            driver.get("https://www.vicarius.io/product");
            BasicHandler.testTimeResponeProduct(driver);
            TestNGReportListener.test.pass("test done");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }
    }
    @Test
    public void testTimeResponeSignIn() {
        try{
            TestNGReportListener.test.pass("loading page");
            driver.get("https://www.vicarius.io/sign/in");
            BasicHandler.testTimeResponeProduct(driver);
            TestNGReportListener.test.pass("test done");
        }catch (Exception e){
            TestNGReportListener.test.fail("Test failed: " + e.getMessage());
        }
    }
}