package basicOperation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reporter.TestNGReportListener;
import vicariusTests.WebDriverManager;

import java.net.URL;

public class BasicHandler {

    public static void fillUserDetails(String firstName,String lastName,String mail,String work){
        WebElement element = WebDriverManager.waitForElement(By.id("input27"));
        element.sendKeys(firstName);

        element = WebDriverManager.waitForElement(By.id("input29"));
        element.sendKeys(lastName);

        element = WebDriverManager.waitForElement(By.id("input31"));
        element.sendKeys(mail);

        element = WebDriverManager.waitForElement(By.id("input33"));
        element.sendKeys(work);
    }

    public static void fillPasswordDetails(String password){
        WebElement element;
        element = WebDriverManager.waitForElement(By.id("input64"));
        element.clear();
        element.sendKeys(password);

        element = WebDriverManager.waitForElement(By.id("input66"));
        element.clear();
        element.sendKeys(password);
    }

    public static void verifyThirdPageOfSingUpDetails(){
        WebElement element;
        element = WebDriverManager.waitForElement(By.xpath("//p[contains(., 'Please check your email for verification link')]"));
        if(!element.isDisplayed()){
            TestNGReportListener.test.fail("Test failed: there is no third page");
        }
    }

    public static void verifyErrors(){
        WebElement element;
        element = WebDriverManager.waitForElementByCssSelector("[class*='error']");
        if(!element.isDisplayed()){
            TestNGReportListener.test.fail("Test failed: there is no error massage as expected");
        }
    }

    public static void verifyIfButtonIsEnabledAndDisplayed(String locator){
        WebElement button = WebDriverManager.waitForElement(By.xpath(locator));
        // Check if the button is enabled
        boolean isEnabled = button.isEnabled();

        // Check if the button is displayed
        boolean isDisplayed = button.isDisplayed();

        if (!(isEnabled && isDisplayed)) {
            TestNGReportListener.test.fail("Sign up button is disabled or not appears");
        }
    }

    public static void sendWorgMail(String mail,String ele, String loginButton, String popUp){
        WebElement element = WebDriverManager.waitForElement(By.id(ele));
        element.sendKeys(mail);

        element = WebDriverManager.waitForElement(By.xpath(loginButton));
        element.click();

        element = WebDriverManager.waitForElement(By.xpath(popUp));
        if(!element.isDisplayed()){
            TestNGReportListener.test.fail("the pop up are not appears as expected ");
        }

    }

    public static void testTimeResponeProduct(WebDriver driver) {
        long startTime = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeScript(
                "return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart);");
        long loadTime = System.currentTimeMillis() - startTime;
        System.out.println("Page load time: " + loadTime + " milliseconds");
        TestNGReportListener.test.pass("Page load time: " + loadTime + " milliseconds");
    }
    public static void testTimeResponeProductWithGrid() {  // my docker desktops dont work properly on my local pc, so i couldn't Pull Selenium Hub Image into docker
        String gridHubUrl = "http://localhost:4444/wd/hub";

        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

        RemoteWebDriver[] drivers = new RemoteWebDriver[10];
            try {
            for (int i = 0; i < 10; i++) {
                drivers[i] = new RemoteWebDriver(new URL(gridHubUrl), chromeCapabilities);
            }
            for (int i = 0; i < 10; i++) {
                RemoteWebDriver driver = drivers[i];
                driver.get("https://www.vicarius.io/sign/in");
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
                long startTime = System.currentTimeMillis();
                long loadTime = (long) ((JavascriptExecutor) driver).executeScript(
                        "return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart);");
                System.out.println("Page load time on Node " + (i + 1) + ": " + loadTime + " milliseconds");
                TestNGReportListener.test.pass("Page load time on Node " + (i + 1) + ": " + loadTime + " milliseconds");
            }
        } catch (Exception e) {
            TestNGReportListener.test.fail(e);
        } finally {
            for (RemoteWebDriver driver : drivers) {
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }

    public static void checkIfTheVideoIsPlaying(String className,WebDriver driver){
        WebElement videoElement = driver.findElement(By.className(className));
        boolean isVideoPlaying = Boolean.parseBoolean(videoElement.getAttribute("paused"));
        if (!isVideoPlaying) {
            TestNGReportListener.test.fail("the video are not playing");
        }
    }

    public static void verifyIfButtonAndText(By locator){
        WebElement button = WebDriverManager.waitForElement(locator);
        // Check if the button is enabled
        boolean isEnabled = button.isEnabled();

        // Check if the button is displayed
        boolean isDisplayed = button.isDisplayed();

        if (isEnabled && isDisplayed) {
            TestNGReportListener.test.pass("Sign up button is ok");
        } else {
            TestNGReportListener.test.fail("Sign up button is disabled or not appears");
        }
    }


}
