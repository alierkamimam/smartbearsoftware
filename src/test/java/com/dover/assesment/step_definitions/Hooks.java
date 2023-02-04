package com.dover.assesment.step_definitions;
import com.dover.assesment.utilities.ConfigReader;
import com.dover.assesment.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void setup(Scenario scenario) {
        System.out.println(":::: Starting test automation ::::");
        System.out.println("Browser type :: " + ConfigReader.getProperty("browser"));
        System.out.println("Environment :: " + ConfigReader.getProperty("url"));
        System.out.println("Test scenario :: " + scenario.getName());
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(image, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }
}
