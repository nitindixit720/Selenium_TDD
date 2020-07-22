package main.java.utility;

import com.google.common.base.Function;
import main.java.constants.Constant;
import main.java.pombase.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;


public class PagesHelper extends BasePage {

    public PagesHelper() {
        super(driver, extentTest);
    }

    public static void clearText(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        element.clear();
    }

    //get text.
    public static String getText(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        String value = element.getText().toLowerCase();
        return value;
    }

    //click element.
    public static void click(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        element.click();
    }

    //open link in new tab.
    public static void openLinkInNewTab(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        String selectLinkOpeninNewTab;
        if (Constant.PROJECTOS.contains("Mac")) {
            selectLinkOpeninNewTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
        } else {
            selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        }
        element.sendKeys(selectLinkOpeninNewTab);
    }

    //Using java script click on element.
    public static void javaScriptClick(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //Send data.
    public static void sendKeys(WebElement element, String text) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        element.sendKeys(text);
    }

    //java script send keys
    public static void javaScriptSendKeys(WebElement element, String text) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='" + text + "'", element);
    }

    //Wait explicit for single element the element to load.
    public static void waitExplicit(WebElement element, String type, long waittime) {
        driver.manage().timeouts().implicitlyWait(0, SECONDS);
        System.out.println("Inside WaitExplicit " + element);
        WebDriverWait wait = new WebDriverWait(driver, waittime);
        String CaseType = type;
        switch (CaseType.toLowerCase()) {
            case Constant.PagesHelperConstants.VISIBILITYOF:
                wait.until(ExpectedConditions.visibilityOf(element));
                break;
            case Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE:
                wait.until(ExpectedConditions.elementToBeClickable(element));
                break;
            case Constant.PagesHelperConstants.ELEMENTTOBESELECTED:
                wait.until(ExpectedConditions.elementToBeSelected(element));
                break;
            case Constant.PagesHelperConstants.INVISIBILITYOF:
                wait.until(ExpectedConditions.invisibilityOf(element));
                break;

            default:
                System.out.println("Method Name: waitexplicit-->Wrong Method Passed");
                break;
        }
    }

    //Wait explicit for more than one element.
    public static void waitExplicitElements(List<WebElement> elements, String type, long waittime) {
        driver.manage().timeouts().implicitlyWait(0, SECONDS);
        System.out.println("Inside waitExplicitElements ");
        WebDriverWait wait = new WebDriverWait(driver, waittime);
        String CaseType = type;
        switch (CaseType.toLowerCase()) {

            case Constant.PagesHelperConstants.VISIBILITYOFALLELEMENTS:
                wait.until(ExpectedConditions.visibilityOfAllElements(elements));
                break;

            default:
                System.out.println("Method Name: waitExplicitElements-->Wrong Method Passed" + elements);
                break;
        }
    }

    //Is element displayed.
    public static boolean isDisplayed(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        boolean flag = element.isDisplayed();
        return flag;
    }

    public static boolean isEnabled(WebElement element) {
        boolean isPresent = true;
        try {
            isPresent = element.isEnabled();
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent;
    }

    //Is element selected.
    public static boolean isSelected(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        boolean flag = element.isSelected();
        return flag;
    }

    //Select element.
    public static void selectElement(WebElement element, String type) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        Select selectObj = new Select(element);
        int index = 0;
        List<WebElement> options = selectObj.getOptions();
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(type))
                break;
            index++;
        }
        selectObj.selectByIndex(index);
    }

    //select element by index
    public static String selectElementByIndex(WebElement element, int index) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        Select selectObj = new Select(element);
        List<WebElement> options = selectObj.getOptions();
        WebElement option = options.get(index);
        selectObj.selectByIndex(index);
        return option.getText();
    }


    //Switch to active element.
    public static void switchToActiveElement() {
        driver.switchTo().activeElement();
    }

    //Select multiple checkbox.
    public static void selectMultipleElements(List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
            javaScriptClick(element);
        }
    }

    //Select Value from list.
    public static void waitForElementToDisplay(List<WebElement> elements, String value) {
        int number = Integer.parseInt(value);
        WebElement element = elements.get(number);
        boolean flag = element.isDisplayed();
        if (flag == true)
            javaScriptClick(element);
        else
            waitForElementToDisplay(elements, value);
    }

    //Check for element available.
    public static boolean isElementAvailable(List<WebElement> elements) {
        boolean flag = elements.size() > 0;
        return flag;
    }

    public static void waitFluent(WebElement element) {
        driver.manage().timeouts().implicitlyWait(0, SECONDS);

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(50, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return null;
            }
        };

        wait.until(function);
    }

    //wait for elements to load.
    public static void waitIfElementsIsDisplayed(List<WebElement> elements, String value) {
        int number = Integer.parseInt(value);
        WebElement element = elements.get(number);
        if (element.isDisplayed())
            waitIfElementsIsDisplayed(elements, value);
    }

    public static void waitIfElementIsDisplayed(WebElement element, String value) {
        int number = Integer.parseInt(value);
        if (element.isDisplayed())
            waitIfElementIsDisplayed(element, value);
    }

    public static void moveToElement(WebElement element) throws InterruptedException {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void scrollDown(WebElement element) {
        waitExplicit(element, Constant.PagesHelperConstants.ELEMENTTOBECLICKABLE, Constant.PagesHelperConstants.TIME);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element);

    }

    public static void scrollToEndOfPage() {
        System.out.println("Inside scrollToEndOfPage");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollUsingValue(int value) {
        System.out.println("Inside scrollUsingValue");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, " + value + ");");
    }

    public static void scrollUsingRobot() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    public static void readTableClickOnHyperlink(LinkedHashMap<String, String> data, List<WebElement> orderIdNumbers) throws InterruptedException {
        for (int i = 0; i < Integer.parseInt(data.get("size")); i++) {
            openLinkInNewTab(orderIdNumbers.get(i));
        }
    }

    public static String removeExtraValues(String value) {
        String replaceValue = value.replace("₹", " ").replace("-", " ").
                replace("\n", " ").replace(",", " ").replace("|", "").replaceAll(" +", " ").trim();
        return replaceValue;
    }

    public static String[] splitLastIndex(String addressValue, String regex) {
        int length = addressValue.length();
        int lastSpaceIndex = addressValue.lastIndexOf(regex);
        String first = addressValue.substring(lastSpaceIndex + 1, length);
        String second = addressValue.substring(0, lastSpaceIndex);
        return new String[]{first, second};
    }
}





