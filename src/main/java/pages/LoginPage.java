package main.java.pages;

import main.java.pombase.BasePage;
import main.java.utility.PagesHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;

public class LoginPage extends BasePage {

    public LoginPage() {
        super(driver, extentTest);
    }


    @FindBy(xpath = "//input[@ng-model='emailId']")
    @CacheLookup
    WebElement userName;

    @FindBy(xpath = "//input[@ng-model='password']")
    @CacheLookup
    WebElement passWord;

    @FindBy(xpath = "//*[text()='Log in']")
    @CacheLookup
    WebElement loginButton;

    @FindBy(xpath = ".//*[@id='toast-container']/div/div")
    @CacheLookup
    WebElement invalidLoginToaster;

    @FindBy(linkText = "Farmer")
    @CacheLookup
    WebElement farmer;


    public void clickToaster() {
        PagesHelper.click(invalidLoginToaster);
    }

    public void setUserName(LinkedHashMap<String, String> data) {
        PagesHelper.clearText(userName);
        PagesHelper.sendKeys(userName, data.get("username"));
    }

    public void setPassWord(LinkedHashMap<String, String> data) {
        PagesHelper.clearText(passWord);
        PagesHelper.sendKeys(passWord, data.get("password"));
    }

    public void clickLogin() {
        PagesHelper.click(loginButton);
    }

    public boolean isLoginButtonDisplayed() {
        boolean flag = PagesHelper.isDisplayed(loginButton);
        return flag;
    }

    public void login(LinkedHashMap<String, String> data) {
        setUserName(data);
        setPassWord(data);
        clickLogin();
    }

    public boolean isFarmerTabEnabled() {
        boolean flag = PagesHelper.isDisplayed(farmer);
        return flag;
    }


}
