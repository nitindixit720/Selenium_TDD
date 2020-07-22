package main.java.pombase;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public static WebDriver driver;
    public static ExtentTest extentTest;

    public BasePage(WebDriver driver, ExtentTest extentTest) {
        BasePage.driver = driver;
        BasePage.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

}
