package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Objects;

public class BrowseFactory {
    public WebDriver wDriver;
    private void configChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        /* headless option*/
//        options.addArguments("--headless");
        options.addArguments("window-size=1200x600");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        wDriver =  new ChromeDriver(options);
    }


    private void configFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        wDriver = new FirefoxDriver();
    }

    private void configIEDriver() {
        WebDriverManager.iedriver().setup();
        wDriver = new InternetExplorerDriver();
    }

    private void configEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        wDriver = new EdgeDriver();
    }

    private void configSafariDriver(){
        WebDriverManager.safaridriver().setup();
        wDriver = new SafariDriver();
    }

   public WebDriver getDriver(String browser){
        if (browser == null){
            configChromeDriver();
            return wDriver;
        }
        switch (Objects.requireNonNull(browser).toLowerCase()){
            case "chrome":
                configChromeDriver();
            case "firefox":
                configFirefoxDriver();
            case "ie":
                configIEDriver();
            case "safari":
                configSafariDriver();
            case "edge":
                configEdgeDriver();
            default:
                configChromeDriver();
        }
        return wDriver;
   }

    public EventFiringWebDriver getDriver(){
        return new EventFiringWebDriver(getDriver(null));
    }
}
