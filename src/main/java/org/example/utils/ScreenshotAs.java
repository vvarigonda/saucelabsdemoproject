package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class ScreenshotAs {
    protected Logger log;
    protected WebDriver driver;
    public ScreenshotAs(WebDriver driver)
    {
        log=Logger.getLogger(this.getClass().getName());
        this.driver = driver;
    }
    public String getScreenshot()
    {
        DateFormat dateFormat = new SimpleDateFormat("YY_MM_dd_hh_mm_ss");
        Date date = new Date();
        // Taking Screenshots
        TakesScreenshot screenshots = (TakesScreenshot) driver;
        File srcFile = screenshots.getScreenshotAs(OutputType.FILE);

        String screenshotPath = System.getProperty("user.dir") + "/Screenshots_FailedTests/" +
                dateFormat.format(date) + ".png";
        File destFile = new File(screenshotPath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info("Not able to take the screenshot.");
            e.printStackTrace();
            log.info("Error: " + e);
        }
        return screenshotPath;
    }
}
