package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@data-test='username']")
    private WebElement usernameInputField;

    @FindBy(how = How.XPATH, using = "//input[@data-test='password']")
    private WebElement passwordInputField;

    @FindBy(how = How.XPATH, using = "//input[@data-test='login-button']")
    private WebElement loginSubmitButton;

    @FindBy(how = How.XPATH, using = "//div[@class=\"app_logo\"]")
    private WebElement homePageTitle;


    public void enterUsername(String userName){
        usernameInputField.clear();
        usernameInputField.sendKeys(userName);
    }


    public void enterPassword(String passWord){
        passwordInputField.clear();
        passwordInputField.sendKeys(passWord);
    }


    public void clickLoginButton(){
        loginSubmitButton.click();
    }

    public boolean successfulLogin(){
        return homePageTitle.getText().equals("Swag Labs");
    }

    public boolean isLoginPage(){
        return driver.getTitle().equals("Swag Labs");
    }
}
