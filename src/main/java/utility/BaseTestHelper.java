package main.java.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.java.commons.Common;
import main.java.commons.ExtentTestListener;
import main.java.constants.Constant;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class BaseTestHelper {

    static List<Class<? extends ITestNGListener>> listenerClasses = new ArrayList<>();

    public static void getTestSuiteData() {
        ExcelReader excelread = new ExcelReader();
        String sheetName = Constant.BaseTestConstant.SHEETNAME;
        int rows = excelread.getLastRowNumber(sheetName);
        Common.BaseTestHelperClass.ENVIRONMENT = excelread.getCellData(sheetName, 1, 0);
        Common.BaseTestHelperClass.BROWSERNAME = excelread.getCellData(sheetName, 3, 0);
        Common.BaseTestHelperClass.SLEEPTIME = Integer.parseInt(excelread.getCellData(sheetName, 5, 0));
        for (int i = 0; i < rows; i++) {
            if (excelread.getCellData(sheetName, 1, i).equalsIgnoreCase(Constant.BaseTestConstant.RUNMODE)) {
                Common.BaseTestHelperClass.sheetsName.add(excelread.getCellData(sheetName, 0, i));
            }
        }
        System.out.println("Test--" + Common.BaseTestHelperClass.sheetsName);
    }

    public static List<XmlSuite> genarateXmlFile() {
        List<XmlTest> tests = new ArrayList<XmlTest>();
        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        XmlSuite suite = new XmlSuite();
        suite.setName(Constant.BaseTestHelperConstant.SUITENAME);
        suite.setPreserveOrder(true);

        for (int i = 0; i < Common.BaseTestHelperClass.sheetsName.size(); i++) {
            XmlTest test = new XmlTest(suite);
            List<XmlClass> classes = new ArrayList<XmlClass>();
            test.setName(Common.BaseTestHelperClass.sheetsName.get(i));
            classes.add(new XmlClass(Constant.BaseTestHelperConstant.TESTSUITEPATH + Common.BaseTestHelperClass.sheetsName.get(i)));
            test.setXmlClasses(classes);
            tests.add(test);
        }

        listenerClasses.add(DataProviderAdder.class);

        suites.add(suite);
        System.out.println("Printing TestNG Suite Xml");
        System.out.println(suite.toXml());
        return suites;

    }

    public static void runXmlSuite(List<XmlSuite> suites) {
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        listenerClasses.add(ExtentTestListener.class);
        tng.setListenerClasses(listenerClasses);
        tng.run();
    }

    public static ExtentTest addTestName(String name, ExtentReports extentReport) {
        ExtentTest extentTest = extentReport.createTest(name);
        return extentTest;
    }

    public static void afterMethod(ExtentTest extentTest, ExtentReports extentReport, String methodName) {
        extentTest.log(Status.INFO, "After Method :---");
        extentTest.log(Status.INFO, "Test Name:----" + methodName + "-----Status:----" + extentTest.getStatus().toString());
        TestSuiteHelper.extentTestHelper(extentTest.getStatus().toString());
        extentReport.flush();
    }

}
