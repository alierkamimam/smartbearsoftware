package com.dover.assesment.pages;

import com.dover.assesment.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    public BasePage() {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

    }

    public void navigateTo(String component) {
        String locator = "//a[text() = '" + component + "']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
    }
}