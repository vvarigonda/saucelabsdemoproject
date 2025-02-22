package com.example.features.steps;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.BaseLib;
import org.example.base.BrowseFactory;
import org.example.pages.LoginPage;
import org.example.utils.GetPropertyValues;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class Login extends BaseLib {

    protected static String BaseUrl;
    protected static String username;
    protected static String password;
    private static LoginPage login;
    private Scenario scenario;

    private static WebDriver webdriver;

    @BeforeAll
    public static void before_or_after_all() throws InterruptedException {
        new GetPropertyValues(false);
        BaseUrl = GetPropertyValues.getGenericProperty("BaseURL");
        username = GetPropertyValues.getGenericProperty("username");
        password = GetPropertyValues.getGenericProperty("password");
//        String browser = "chrome";
        // This can be used for multi browsers
        driver.set(new BrowseFactory().getDriver());
        driver.get().manage().window().maximize();
        driver.get().navigate().to(BaseUrl);
        login = new LoginPage(driver.get());
        Thread.sleep(3000); // This is just to show how the browser is loading
        webdriver = driver.get().getWrappedDriver();
    }

    @AfterAll
    public static void after_all() {
        driver.get().quit();
    }

    @Before
    public void before_scenario(Scenario scenario){
        this.scenario = scenario;
    }

    @AfterStep
    public void take_screenshot_after_each_step(){
        byte[] screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "screenshot");
    }
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Assert.assertTrue(login.isLoginPage());
    }
    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        login.enterUsername(username);
        login.enterPassword(password);
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        login.clickLoginButton();
        Assert.assertTrue(login.successfulLogin());
    }
}
