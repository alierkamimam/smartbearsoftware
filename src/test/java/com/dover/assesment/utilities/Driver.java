package com.dover.assesment.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    /*
     * We use private and static modifiers so that no one can create a driver class object,
     * instead everyone should call the static getter method.
     */

    private Driver() {

    }

    /**
     * Synchronized makes method thread safe.
     * It ensures that only 1 thread can use it at the time.
     * Thread safety reduces performance but it makes everything safe.
     *
     * @return
     */

    public synchronized static WebDriver getDriver() {
        /*
         * We first check if a webdriver object exists,
         * if not, this method will create it.
         *
         */

        if (driverPool.get() == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximize");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;

                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options1 = new FirefoxOptions();
                    options1.addArguments("--start-maximize");
                    driverPool.set(new FirefoxDriver(options1));
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions options2 = new EdgeOptions();
                    options2.addArguments("--start-maximize");
                    driverPool.set(new EdgeDriver(options2));
                    break;
                default:
                    new RuntimeException("Wrong Browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool == null) {
            driverPool.get().close();
            driverPool.remove();
        }
    }

    public static void quitDriver() {
        if (driverPool == null) {
            driverPool.get().quit();
            driverPool.remove();
        }


    }
}
