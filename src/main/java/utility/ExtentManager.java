package main.java.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import main.java.constants.Constant;

import java.io.File;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static String fileName;

	/*
     * Create Extent report instance and report path.
	 *
	 */

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            String testName = Constant.BaseTestConstant.TESTCASENAME;
            Date date = new Date();
            fileName = date.toString().replace(":", "_").replace(" ", "_") + "_" + testName + "_" + ".html";
            String reportPath = Constant.ExtentManagerConstant.REPORTSPATH + fileName;
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
            htmlReporter.loadXMLConfig(new File(Constant.PROJECTPATH + File.separatorChar + "ReportsConfig.xml"));
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
            htmlReporter.setAppendExisting(true);
        }
        return extentReports;
    }

    public static String getFileName() {
        return fileName;
    }
}
