package reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestNGReportListener implements ITestListener {
    private static ExtentReports extent;
    public static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
//optimization : insert the screenshot to the html report page.
    public static void captureScreenshot(WebDriver driver, String filename) {
        if (driver instanceof TakesScreenshot) {
            // Convert web driver object to TakeScreenshot
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

            // Capture the screenshot as a file
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            // Generate a timestamp for the screenshot file name
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String timestamp = now.format(formatter);

            // Define the screenshot destination path
            String screenshotPath = "test-output/screenshots/" + filename + "_" + timestamp + ".png";

            // Copy the screenshot to the desired location
            try {
                FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Embed the screenshot in the TestNG report
            String screenshotRelativePath = "./screenshots/" + filename + "_" + timestamp + ".png";
            Reporter.log("<br><a href='" + screenshotRelativePath + "'><img src='" + screenshotRelativePath + "' height='200' width='350'/></a><br>");
        }
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }
}