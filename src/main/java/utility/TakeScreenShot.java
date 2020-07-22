package main.java.utility;

import com.aventstack.extentreports.Status;
import main.java.constants.Constant;
import main.java.pombase.BasePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TakeScreenShot extends BasePage {

    public TakeScreenShot() {
        super(driver, extentTest);
    }

    public static String takeScreenShot() {

        Date dateObj = new Date();
        String fileName = dateObj.toString().replace(":", "_").replace(" ", "_") + Thread.currentThread().getStackTrace()[2].getMethodName() + ".png";
        String filePath = Constant.TakeScreenShotConstant.SCREENSHOTPATH + fileName;
        System.out.println("--------" + filePath);

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);

        //File scrFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            //FileUtils.copyFile(scrFile, new File(filePath));
            ImageIO.write(screenshot.getImage(), "PNG",
                    new File(filePath));
        } catch (IOException e) {
            extentTest.log(Status.FAIL, "Exception :" + e);
        }

        return filePath;
    }

}
