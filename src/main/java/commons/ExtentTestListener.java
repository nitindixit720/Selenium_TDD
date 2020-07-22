package main.java.commons;

import com.aventstack.extentreports.ExtentTest;
import main.java.utility.BaseTestHelper;
import main.java.utility.TestSuiteHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ExtentTestListener implements ITestListener {

    public static ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentTest = BaseTestHelper.addTestName(iTestResult.getName(), BaseTestListener.extentReport);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        TestSuiteHelper.afterMethod(extentTest, BaseTestListener.extentReport, iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        TestSuiteHelper.afterMethod(extentTest, BaseTestListener.extentReport, iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        TestSuiteHelper.afterMethod(extentTest, BaseTestListener.extentReport, iTestResult.getName());
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
