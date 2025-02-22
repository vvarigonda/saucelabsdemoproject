package org.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public abstract class BaseLib {
    public static ThreadLocal<EventFiringWebDriver> driver = new ThreadLocal<>();
    public static String globalEnvironment;
    public static WebDriver getDriver(){
        return driver.get();
    }
}