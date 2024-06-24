package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {

    public static WebDriver driver;

    public DriverManager() {
        System.setProperty("webdriver.firefox.driver", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver();
    }

    public WebDriver getDriver(String site) {
        driver.manage().window().maximize();
        driver.get(site);
        return driver;
    }


}
