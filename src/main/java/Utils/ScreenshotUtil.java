package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    // Take screenshot and save to reports/screenshots
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Create screenshots folder if it doesn't exist
        File destDir = new File(System.getProperty("user.dir") + "/reports/screenshots/");
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        // Generate timestamped screenshot filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destinationPath = destDir + "/" + screenshotName + "_" + timestamp + ".png";

        // Take screenshot and save
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(destinationPath);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationPath; // returns the screenshot path for reports
    }
}
