package com.dover.assesment.step_definitions;

import com.dover.assesment.utilities.BrowserUtils;
import com.dover.assesment.utilities.ConfigReader;
import com.dover.assesment.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginProcessStepDefs extends BaseStep {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        String url = ConfigReader.getProperty("url");
        Driver.getDriver().get(url);
        System.out.println("Open :: " + url);
        BrowserUtils.wait(1);
    }
    @When("the user enters username {string}")
    public void theUserEntersUsername(String userName) {
        pages.loginPage().enterUserNameValue(userName);
    }

    @When("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        pages.loginPage().enterPasswordValue(password);
    }

    @When("the user clicks on the login")
    public void theUserClicksOnTheLogin() {
        pages.loginPage().clickLoginButton();
    }

    @Then("the user should be logged in")
    public void theUserShouldBeLoggedIn() {
        BrowserUtils.wait(1);
        Assert.assertEquals(pages.webOrdersPage().getCurrentUrl(),
                "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
    }

    @Then("the user arrives at the home page and welcome text containing the username {string}")
    public void theUserArrivesAtTheHomePageAndWelcomeTextContainingTheUsername(String username) {
        String displayedUserNameMessage = pages.webOrdersPage().getDisplayedUserName();
        Assert.assertTrue(displayedUserNameMessage.contains(username));
    }

    @Then("the user should not be able to login and {string} error must be displayed")
    public void theUserShouldNotBeAbleToLoginAndErrorMustBeDisplayed(String errorMessage) {
        String actualErrorMessage=pages.loginPage().getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains(errorMessage));
    }
}
