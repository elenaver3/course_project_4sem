package task3;

import common.DriverManager;
import common.YandexMarketManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Test3 {
    public static YandexMarketManager yandexMarketManager;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        DriverManager driverManager = new DriverManager();
        driver = driverManager.getDriver("https://market.yandex.ru");
        yandexMarketManager = new YandexMarketManager(driver);
    }

    @Test
    public void test3() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yandexMarketManager.catalogClick();

        yandexMarketManager.electronicsClick();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yandexMarketManager.laptopsClick();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int k = 5;
        yandexMarketManager.getItems(5);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yandexMarketManager.setPrices("60000", "110000");
        yandexMarketManager.getNewItems(5);
    }


}
