package main.java.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.java.commons.Common;
import main.java.constants.Constant;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.SkipException;

import java.io.File;
import java.io.IOException;

import static main.java.pombase.BasePage.driver;

public class TestSuiteHelper {

    public static void takeScreenShotHelper(ExtentTest extentTest) {
        try {
            String filePath = TakeScreenShot.takeScreenShot();
            extentTest.addScreenCaptureFromPath(filePath);
        } catch (IOException ex) {
            extentTest.fail("Exception:->" + ex);
        }

    }

    public static void isTestExecutable(String mode, ExtentTest extentTest) {
        if (mode.equalsIgnoreCase(Constant.TestSuiteHelperConstants.RUNMODE))
            extentTest.log(Status.INFO, "Running the test as Runmode is Yes");
        else {
            extentTest.log(Status.SKIP, "Skipping the test as Runmode is No");
            throw new SkipException("Skipping the test as Runmode is No");
        }
    }

    public static void extentTestHelper(String status) {
        if (status.equalsIgnoreCase(Constant.ExtentTestResult.PASSTEST)) {
            Common.ExtentTestCount.PASSCOUNT++;
        } else if (status.equalsIgnoreCase(Constant.ExtentTestResult.FAILTEST)) {
            Common.ExtentTestCount.FAILCOUNT++;
        } else if (status.equalsIgnoreCase(Constant.ExtentTestResult.SKIPTEST)) {
            Common.ExtentTestCount.SKIPCOUNT++;
        }
    }

    public static void extractLogs(LogEntries logEntries, ExtentTest extentTest) {
        for (LogEntry entry : logEntries) {
            extentTest.log(Status.INFO, "Console Logs:  " + "<p> Level:->" + entry.getLevel() + "<p> Messages:->" + entry.getMessage());
        }
    }

    public static void afterMethod(ExtentTest extentTest, ExtentReports extentReport, String methodName) {
        extentTest.log(Status.INFO, "After Method :---");
        extentTest.log(Status.INFO, "Test Name:----" + methodName + "-----Status:----" + extentTest.getStatus().toString());
        takeScreenShotHelper(extentTest);
        TestSuiteHelper.extentTestHelper(extentTest.getStatus().toString());
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        TestSuiteHelper.extractLogs(logEntries, extentTest);
        extentReport.flush();
    }

    public static void createFolder(String filePath, ExtentTest baseExtentTest) {
        File file = new File(filePath);
        if (!file.exists()) {
            baseExtentTest.log(Status.INFO, "Create New Folder->" + filePath);
            file.mkdir();
            baseExtentTest.log(Status.INFO, "Folder Created Successfully->" + filePath);
        } else {
            baseExtentTest.log(Status.INFO, "Folder Present->" + filePath);
        }


}

}

