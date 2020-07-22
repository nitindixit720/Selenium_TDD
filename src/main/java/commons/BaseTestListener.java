package main.java.commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import main.java.constants.Constant;
import main.java.utility.BaseTestHelper;
import main.java.utility.ExtentManager;
import main.java.utility.SlackMessageHelper;
import org.testng.*;

import java.io.IOException;

import static main.java.pombase.BasePage.driver;


public class BaseTestListener implements ISuiteListener, ITestListener {


    public static ExtentReports extentReport;
    public static ExtentTest baseExtentTest;


    @Override
    public void onStart(ISuite iSuite) {
        extentReport = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        SlackMessageHelper slackMessage = new SlackMessageHelper();
        try {
            String[] values = Common.BaseTestHelperClass.ENVIRONMENT.split("[^a-z]");
            String environment = values[3];
            int totalTestsCount = Common.ExtentTestCount.PASSCOUNT + Common.ExtentTestCount.FAILCOUNT + Common.ExtentTestCount.SKIPCOUNT;
            String reportLink = Constant.SlackMessageConstants.ENDPOINT + ExtentManager.getFileName();
            String message = "[" + environment + "] " + iSuite.getName().toLowerCase() + ": " + Common.ExtentTestCount.PASSCOUNT + "/" + totalTestsCount + " tests passed | " + reportLink;
            slackMessage.sendMessage(Constant.SlackMessageConstants.CHANNEL, message, Constant.SlackMessageConstants.TOKEN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        baseExtentTest = BaseTestHelper.addTestName(iTestResult.getName(), extentReport);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        BaseTestHelper.afterMethod(baseExtentTest, extentReport, iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        BaseTestHelper.afterMethod(baseExtentTest, extentReport, iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        BaseTestHelper.afterMethod(baseExtentTest, extentReport, iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}