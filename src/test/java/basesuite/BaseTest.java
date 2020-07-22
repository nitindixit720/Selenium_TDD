package test.java.basesuite;

import com.aventstack.extentreports.Status;
import main.java.commons.Common;
import main.java.constants.Constant;
import main.java.pombase.BasePage;
import main.java.utility.BaseTestHelper;
import main.java.utility.BrowserSetup;
import main.java.utility.DownloadExcelHelper;
import main.java.utility.TestSuiteHelper;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.List;

import static main.java.commons.BaseTestListener.baseExtentTest;


public class BaseTest {

    public WebDriver driver;
    List<XmlSuite> suites;


    @Test(priority = 1)
    public void createFolder() {
        try {
            String reportPath = Constant.BaseTestConstant.REPORTSFOLDER;
            TestSuiteHelper.createFolder(reportPath, baseExtentTest);
            String screenShotPath = Constant.BaseTestConstant.SCREENSHOTFOLDER;
            TestSuiteHelper.createFolder(screenShotPath, baseExtentTest);
        } catch (Exception e) {
            baseExtentTest.fail("Not able to create folder" + e);
            Assert.fail("Not able to create folder");
        }
    }


    @Test(priority = 2)
    public void downloadFile() {
        try {
            // download file from google drive.
            if (!TextUtils.isEmpty(Constant.DownloadExcelFileHelperConstants.EXCELFILEPATH))
                try {
                    DownloadExcelHelper.initialMethod();
                    baseExtentTest.log(Status.PASS, "File downloaded successfully.");
                } catch (IOException e) {
                    baseExtentTest.fail("Not able to download file form drive" + e);
                }

            // Get Runnable sheets from excel
            BaseTestHelper.getTestSuiteData();
        } catch (Exception e) {
            baseExtentTest.fail("Not able to download file form drive" + e);
            Assert.fail("Not able to download file form drive");
        }

    }

    @Test(priority = 3)
    public void setBrowser() {
        String filePath = null;
        try {
            driver = BrowserSetup.openBrowser(Common.BaseTestHelperClass.BROWSERNAME);
            driver.get(Common.BaseTestHelperClass.ENVIRONMENT);
            if (System.getProperty(Constant.BaseTestConstant.ENVIRONMENT, Constant.BaseTestConstant.ENVIRONMENTLOCAL).equalsIgnoreCase(Constant.BaseTestConstant.ENVIRONMENTLOCAL)) {
                driver.manage().window().maximize();
            }
            BasePage basePageObj = new BasePage(driver, baseExtentTest);
            baseExtentTest.log(Status.PASS, "Browser open successfully");
            baseExtentTest.log(Status.INFO, "URL Open in browser:->" + Common.BaseTestHelperClass.ENVIRONMENT);
            TestSuiteHelper.takeScreenShotHelper(baseExtentTest);
            baseExtentTest.log(Status.PASS, "Set Browser successfully.");
        } catch (Exception e) {
            baseExtentTest.fail("Not able to set browser-->" + e);
            Assert.fail("Not able to set browser");
        }
    }

    @Test(priority = 4)
    public void createXmlfile() throws IOException {
        try {
            suites = BaseTestHelper.genarateXmlFile();
            baseExtentTest.log(Status.PASS, "Generate XML file successfully");
        } catch (Exception e) {
            baseExtentTest.fail("Not able to create XML File for running flows-->" + e);
            Assert.fail("Not able to create XML File for running flows");
        }

    }

    @Test(priority = 5)
    public void runTestNgXmlFile() {
        try {
            BaseTestHelper.runXmlSuite(suites);
            baseExtentTest.log(Status.PASS, "Run XML file successfully");
        } catch (Exception e) {
            baseExtentTest.fail("Not able to Run XML File-->" + e);
        }
    }

}
