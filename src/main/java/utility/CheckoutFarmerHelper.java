package main.java.utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.java.pages.LoginPage;

import java.util.LinkedHashMap;


public class CheckoutFarmerHelper {

    public static void loginSuccess(LoginPage loginObj, LinkedHashMap<String, String> data, ExtentTest extentTest) {
        loginObj.login(data);
        boolean flag = loginObj.isFarmerTabEnabled();
        if (flag == true)
            extentTest.log(Status.PASS, "Element Farmer locate successfully.");
        else
            extentTest.log(Status.FAIL, "Not able to locate Element Farmer.");
    }

}
