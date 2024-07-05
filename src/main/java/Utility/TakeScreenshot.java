package Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TakeScreenshot {
    WebDriver driver;
    public TakeScreenshot(WebDriver driver){
        this.driver =driver;
    }


    public void Screenshot(String fileName) {
        // Convert WebDriver object to TakeScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot as file
        File screenshot = ts.getScreenshotAs(OutputType.FILE);

        // Define the path to save the screenshot
        String destination = "C:\\Users\\ashwinraj.s\\Desktop\\viren\\target\\Spark\\" + fileName + ".png";

        // Copy file to the destination
        try {
            Files.copy(screenshot.toPath(), new File(destination).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
