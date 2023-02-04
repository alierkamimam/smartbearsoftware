package com.dover.assesment.step_definitions;

import com.dover.assesment.utilities.BrowserUtils;
import com.dover.assesment.utilities.CommonExcelReader;
import com.dover.assesment.utilities.ConfigReader;
import com.dover.assesment.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OrderPageStepDefs extends BaseStep {
    Map<String, String> excelData;

    @Given("the user navigates to login page")
    public void the_user_navigates_to_login_page() {
        String URL = ConfigReader.getProperty("url");
        Driver.getDriver().get(URL);
        System.out.println("Open ::" + URL);
        BrowserUtils.wait(1);
    }


    @Given("the user is on the web orders page")
    public void the_user_is_on_the_web_orders_page() {
        BrowserUtils.wait(1);
        Assert.assertEquals(pages.webOrdersPage().getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
    }


    @Given("the user logged in with valid credentials, {string} as username and {string} as password")
    public void the_user_logged_in_with_valid_credentials_as_username_and_as_password(String usernameValue, String passwordValue) {
        pages.loginPage().enterUserNameValue(usernameValue);
        pages.loginPage().enterPasswordValue(passwordValue);
        pages.loginPage().clickLoginButton();
    }

    @Given("the user wants to test test case : {string} by retrieving the test data from Excel Workbook: {string} Sheet: {string}")
    public void theUserWantsToTestTestCaseByRetrievingTheTestDataFromExcelWorkbookSheet(String testCase, String excelWorkbook, String sheet) {
        try {
            CommonExcelReader readExcel = new CommonExcelReader();
            excelData = readExcel.getDataFromExcel(testCase, excelWorkbook, sheet);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @And("the user navigates to {string} page")
    public void theUserNavigatesToPage(String string) {
        pages.webOrdersPage().navigateTo(string);

    }

    @When("the user enters product information")
    public void theUserEntersProductInformation() {
        String productValue = excelData.get("Product");
        String quantityValue = excelData.get("Quantity");
        String pricePerUnit = excelData.get("Price_per_unit");
        String discount = excelData.get("Discount");

        pages.orderPage().setProductType(productValue);
        pages.orderPage().enterQuantity(quantityValue);
        pages.orderPage().enterPricePerUnit(pricePerUnit);
        pages.orderPage().enterDiscount(discount);

    }

    @And("the user click on calculate button")
    public void theUserClickOnCalculateButton() {
        pages.orderPage().clickToCalculate();
    }

    @Then("the user should verify that displayed total amount")
    public void theUserShouldVerifyThatDisplayedTotalAmount() {
        Assert.assertEquals(String.valueOf(excelData.get("Total")), pages.orderPage().getTotal());

    }

    // | Card | Card Nr:  | Expire date (mm/yy) |
    @When("the user enters address and payment information")
    public void theUserEntersAddressAndPaymentInformation() {
        pages.orderPage().enterAddressInformation(
                excelData.get("Customer_name"),
                excelData.get("Street"),
                excelData.get("City"),
                excelData.get("State"),
                excelData.get("Zip"));

        pages.orderPage().enterPaymentInformation(excelData.get("Card"),
                excelData.get("Card_Nr"),
                excelData.get("Expire_date"));

    }

    @And("the user click on process button")
    public void theUserClickOnProcessButton() {
        pages.orderPage().clickOnProcessButton();

    }


}


