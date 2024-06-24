package task4;

import common.DriverManager;
import common.LitresManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Test4 {
    public static LitresManager litresManager;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        DriverManager driverManager = new DriverManager();
        driver = driverManager.getDriver("https://www.litres.ru/");
        litresManager = new LitresManager(driver);
    }

    @Test
    public void test4Test1() {
        litresManager.catalogClick();
        litresManager.categoryClick();
        litresManager.chooseFilterNew();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int k = 5;
        litresManager.getBooks(k);
    }

    @Test
    public void test4Test2() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        litresManager.catalogClick();
        litresManager.category2Click();
        litresManager.chooseEn();
        litresManager.chooseTextBooks();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        litresManager.firstBookClick();
        Assert.assertTrue(litresManager.checkFormat());
        Assert.assertTrue(litresManager.checkLang());
    }

    @Test
    public void test4Test3() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        litresManager.catalogClick();
        litresManager.category3Click();
        String name = litresManager.likeBtnClick();
        litresManager.likedCategoryBtnClick();
        Assert.assertEquals(name, litresManager.getName());
    }

}
