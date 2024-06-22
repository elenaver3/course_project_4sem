package task1;

import common.DriverManager;
import common.LambdaTestManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Test1 {
    public static LambdaTestManager lambdaTestManager;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        DriverManager driverManager = new DriverManager();

//        DriverManager driverManager1 = PageFactory.initElements(driver, DriverManager.class);
        driver = driverManager.getDriver("https://lambdatest.github.io/sample-todo-app/");
        lambdaTestManager = new LambdaTestManager(driver);

    }


    @Test
    public void test1Test1() {
        Assert.assertEquals("LambdaTest Sample App", lambdaTestManager.getH2Name());
    }

    @Test
    public void test1Test2() {
        Assert.assertEquals("5 of 5 remaining", lambdaTestManager.getLabelRemains());
    }

    @Test
    public void test1Test3() {
        for(int i = 1; i <= 5; i++) {
            WebElement li = lambdaTestManager.getLi(i);
            Assert.assertEquals("done-false", li.getAttribute("class"));
            lambdaTestManager.clickCheckbox(i);
            Assert.assertEquals("done-true", li.getAttribute("class"));
            Assert.assertEquals(5 - i + " of 5 remaining", lambdaTestManager.getLabelRemains());
        }
    }

    @Test
    public void test1Test4() {
        lambdaTestManager.addNewElement();
        Assert.assertEquals("1 of 6 remaining", lambdaTestManager.getLabelRemains());
        WebElement li = lambdaTestManager.getLi(6);
        Assert.assertEquals("done-false", li.getAttribute("class"));
    }

    @Test
    public void test1Test5() {
        WebElement li = lambdaTestManager.getLi(6);
        lambdaTestManager.clickCheckbox(6);
        Assert.assertEquals("done-true", li.getAttribute("class"));
        Assert.assertEquals("0 of 6 remaining", lambdaTestManager.getLabelRemains());
    }
}
