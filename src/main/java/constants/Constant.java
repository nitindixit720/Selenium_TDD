package main.java.constants;

public class Constant {

    public static final String PROJECTPATH = System.getProperty("user.dir");
    public static final String PROJECTOS = System.getProperty("os.name");

    public static final String EXCEL_PATH = PROJECTPATH + java.io.File.separatorChar + "src"
            + java.io.File.separatorChar + "test" + java.io.File.separatorChar + "resources"
            + java.io.File.separatorChar + "Excel" + java.io.File.separatorChar + "excel.xlsx";


    public static class BaseTestConstant {
        public static final String REPORTSFOLDER = Constant.PROJECTPATH + java.io.File.separatorChar + "Reports";
        public static final String SCREENSHOTFOLDER = PROJECTPATH + java.io.File.separatorChar + "src" + java.io.File.separatorChar + "test" + java.io.File.separatorChar + "resources" + java.io.File.separatorChar + "ScreenShots";
        public static final String SHEETNAME = "testsuite";
        public static final String RUNMODE = "Yes";
        public static final String TESTCASENAME = "enter your test name";
        public static final String ENVIRONMENT = "env";
        public static final String ENVIRONMENTLOCAL = "local";
    }

    public static class BaseTestHelperConstant {
        public static final String SUITENAME = "enter your suite name";
        public static final String TESTSUITEPATH = "test.java.testsuite.";
    }

    public static class TakeScreenShotConstant {
        public static final String SCREENSHOTPATH = PROJECTPATH + java.io.File.separatorChar + "src" + java.io.File.separatorChar + "test" + java.io.File.separatorChar + "resources" + java.io.File.separatorChar + "ScreenShots" + java.io.File.separatorChar;
    }

    public static class ExtentManagerConstant {
        public static final String REPORTSPATH = PROJECTPATH + java.io.File.separatorChar + "Reports"
                + java.io.File.separatorChar;
    }

    public static class DownloadExcelFileHelperConstants {
        public static final String EXCELFILEPATH = "Provide 16 digit code from google sheet URL";
        public static final String CLIENTSECRETJSON = "/client_secret.json";
        public static final String FILETYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public static final String APPLICATIONNAME = "Drive API Quickstart";
        public static final String LOCATIONOFCREDENTIAL = ".credentials";
    }

    public static class PagesHelperConstants {
        public static final String VISIBILITYOF = "visibilityof";
        public static final String VISIBILITYOFALLELEMENTS = "visibilityofallelements";
        public static final String ELEMENTTOBECLICKABLE = "elementtobeclickable";
        public static final String ELEMENTTOBESELECTED = "elementtobeselected";
        public static final String INVISIBILITYOF = "invisibilityof";
        public static final int TIME = 50;
    }

    public static class TestSuiteHelperConstants {
        public static final String RUNMODE = "Yes";
    }


    /* In your organization if your using the slack chanel, Then you can also send your test result report on
     * slack channel along with pass and fail count. here you just have to add your slack token and chanel name with
     * upload server path to the report folder  */
    public static class SlackMessageConstants {
        public static final String TOKEN = " Provide Slack auth token";
        public static final String CHANNEL = "Slack Chanel Name";
        public static final String ENDPOINT = "provide upload server path";
    }

    /* Here your getting the no of passed and failed test cases count .. Which you can send into your slack channel
     * So that non tech user will also get the status of report without opening it*/
    public static class ExtentTestResult {
        public static final String PASSTEST = "pass";
        public static final String FAILTEST = "fail";
        public static final String SKIPTEST = "skip";
    }


}
