package main.java.utility;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.pombase.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup extends BasePage {

    public BrowserSetup(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);
    }


    public static WebDriver openBrowser(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome":
                openChromeDriver();
                break;
            case "firefox":
                openFirefoxDriver();
                break;

            default:
                openChromeDriver();
                break;
        }

        return driver;

    }

    public static WebDriver openChromeDriver() {
        WebDriverManager.chromedriver().clearPreferences();
        WebDriverManager.chromedriver().clearCache();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver openFirefoxDriver() {
        WebDriverManager.firefoxdriver().clearPreferences();
        WebDriverManager.chromedriver().clearCache();
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }


}
