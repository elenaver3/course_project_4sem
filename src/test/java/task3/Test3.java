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
    public void test3Test1() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yandexMarketManager.catalogClick();
    }

    @Test
    public void test3Test2() {
        yandexMarketManager.electronicsClick();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yandexMarketManager.laptopsClick();
    }

    @Test
    public void test3Test3() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int k = 5;
        String[] names = yandexMarketManager.getNames(k);
        String[] prices = yandexMarketManager.getPrices(k);
        for (int i = 0; i < k; i++) {
            System.out.println("Название: " + names[i] + "\nЦена: " + prices[i]);
        }
        System.out.println();
    }

    @Test
    public void test3Test4() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yandexMarketManager.setPrices("60000", "110000");
        int k = 5;
        String[] names = yandexMarketManager.getNewNames(k);
        String[] prices = yandexMarketManager.getNewPrices(k);
        for (int i = 0; i < k; i++) {
            System.out.println("Название: " + names[i] + "\nЦена: " + prices[i]);
            String a = prices[i].replaceAll(" ","");
//            int selectedPrice = ;
//            Assert.assertTrue(yandexMarketManager.isInRange(60000, 110000, selectedPrice));
        }
    }

}
