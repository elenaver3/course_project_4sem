package task1;

import common.DriverManager;
import common.LambdaTestManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class Test1 {
    public static LambdaTestManager lambdaTestManager;

    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        DriverManager driverManager = new DriverManager();
        driver = driverManager.getDriver("https://lambdatest.github.io/sample-todo-app/");
        lambdaTestManager = new LambdaTestManager(driver);
    }


    @Test
    public void test1() {
        lambdaTestManager.checkH2();

        lambdaTestManager.checkLabel();

        lambdaTestManager.checkLabels();

        lambdaTestManager.addElement();

        lambdaTestManager.checkNewElement();

    }

}
