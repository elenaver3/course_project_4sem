package task2;

import common.DriverManager;
import common.MospolytechManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Test2 {
    public static MospolytechManager mospolytechManager;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        DriverManager driverManager = new DriverManager();
        driver = driverManager.getDriver("https://mospolytech.ru/");
        mospolytechManager = new MospolytechManager(driver);
    }

    @Test
    public void test2() {
        mospolytechManager.clickRaspBtn();

        mospolytechManager.clickWatchOnSiteBtn();

        String groupNumber = "221-361";
        mospolytechManager.findGroup(groupNumber);
        mospolytechManager.chooseGroup(groupNumber);

        mospolytechManager.checkDate();

    }


}
