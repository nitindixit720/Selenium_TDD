package test.java.testsuite;

import com.aventstack.extentreports.Status;
import main.java.pages.LoginPage;
import main.java.utility.CheckoutFarmerHelper;
import main.java.utility.TestSuiteHelper;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static main.java.commons.ExtentTestListener.extentTest;

public class LoginTest {

    LoginPage loginObj = new LoginPage();

    @Test(priority = 1)
    public void loginFail(LinkedHashMap<String, String> data) {
        TestSuiteHelper.isTestExecutable(data.get("runmode"), extentTest);
        loginObj.login(data);
        loginObj.clickToaster();
        boolean flag = loginObj.isLoginButtonDisplayed();
        if (flag)
            extentTest.log(Status.PASS, "Login button locate successfully.");
        else
            extentTest.log(Status.FAIL, "Not able to locate Login button.");
        extentTest.log(Status.PASS, "Test Passed");

    }

    @Test(priority = 2)
    public void loginSuccess(LinkedHashMap<String, String> data) {
        TestSuiteHelper.isTestExecutable(data.get("runmode"), extentTest);
        CheckoutFarmerHelper.loginSuccess(loginObj, data, extentTest);
        extentTest.log(Status.PASS, "Test Passed");

    }

    @Test(priority = 3)
    public void logoutUser(LinkedHashMap<String, String> data) {
        TestSuiteHelper.isTestExecutable(data.get("runmode"), extentTest);
        extentTest.log(Status.PASS, "Test Passed");

    }

}
