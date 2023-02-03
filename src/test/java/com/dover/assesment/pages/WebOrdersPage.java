package com.dover.assesment.pages;

import com.dover.assesment.utilities.ConfigReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebOrdersPage extends BasePage {

    @FindBy(id = "ctl00_MainContent_username")
    private WebElement userNameInputBoxElement;

    @FindBy(id = "ctl00_MainContent_password")
    private WebElement passwordInputBoxElement;

    @FindBy(id = "ctl00_MainContent_login_button")
    private WebElement loginButton;

    @FindBy(id = "ctl00_MainContent_status")
    private WebElement errorMessage;


    public void login() {
        String usernameValue = ConfigReader.getProperty("username");
        String passwordValue = ConfigReader.getProperty("password");
        userNameInputBoxElement.sendKeys(usernameValue);
        passwordInputBoxElement.sendKeys(passwordValue, Keys.ENTER);
    }


    public void enterUserNameValue(String usernameValue) {
        userNameInputBoxElement.sendKeys(usernameValue);
    }

    public void enterPasswordValue(String passwordValue) {
        passwordInputBoxElement.sendKeys(passwordValue);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
