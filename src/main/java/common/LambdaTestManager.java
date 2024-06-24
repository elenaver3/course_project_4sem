package common;

import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LambdaTestManager {
    public WebDriver driver;
    public LambdaTestManager(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@class, 'ng-binding')]")
    private WebElement labelRemains;

    @FindBy(xpath = "//h2[contains(text(), 'LambdaTest Sample App')]")
    private WebElement h2Name;

    @FindBy(xpath = "//*[contains(@name, 'li1')]")
    private WebElement li;

    @FindBy(xpath = "//*[contains(@name, 'li1')]")
    private WebElement liCheckbox;

    @FindBy(xpath = "//*[contains(@id, 'sampletodotext')]")
    private WebElement inputNewElement;

    @FindBy(xpath = "//*[contains(@id, 'addbutton')]")
    private WebElement btnNewElement;

    @Step
    public void checkH2() {
        Assert.assertEquals("LambdaTest Sample App", getH2Name());
        if (getH2Name().equals("LambdaTest Sample App"))
            System.out.println("Заголовок страницы 'LambdaTest Sample App' совпадает");
        else
            System.out.println("Заголовок страницы 'LambdaTest Sample App' не совпадает");
    }

    @Step
    public void checkLabels() {

        for(int i = 1; i <= 5; i++) {
            WebElement li = getLi(i);
            Assert.assertEquals("done-false", li.getAttribute("class"));
            if (li.getAttribute("class").equals("done-false"))
                System.out.println("Класс done-false для пункта " + i + " найден");
            else
                System.out.println("Класс done-false для пункта " + i + " не найден");
            clickCheckbox(i);
            Assert.assertEquals("done-true", li.getAttribute("class"));
            if (li.getAttribute("class").equals("done-true"))
                System.out.println("Пункт " + i + " отмечен");
            else
                System.out.println("Пункт " + i + " не отмечен");
            Assert.assertEquals(5 - i + " of 5 remaining", getLabelRemains());
            if (getLabelRemains().equals(5 - i + " of 5 remaining"))
                System.out.println("Заголовок верный");
            else
                System.out.println("Заголовок не верный");
        }
    }

    @Step
    public void checkLabel() {
        Assert.assertEquals("5 of 5 remaining", getLabelRemains());
        if (getLabelRemains().equals("5 of 5 remaining"))
            System.out.println("Надпись '5 of 5 remaining' присутствует");
        else
            System.out.println("Надпись '5 of 5 remaining' не присутствует");
    }

    @Step
    public void addElement() {

        addNewElement();
        Assert.assertEquals("1 of 6 remaining", getLabelRemains());
        if (getLabelRemains().equals("1 of 6 remaining"))
            System.out.println("Надпись '1 of 6 remaining' присутствует");
        else
            System.out.println("Надпись '1 of 6 remaining' не присутствует");
        WebElement li = getLi(6);
        Assert.assertEquals("done-false", li.getAttribute("class"));
    }

    @Step
    public void checkNewElement() {
        clickCheckbox(6);
        WebElement li = getLi(6);
        Assert.assertEquals("done-true", li.getAttribute("class"));
        if (li.getAttribute("class").equals("done-true"))
            System.out.println("Пункт " + 6 + " отмечен");
        else
            System.out.println("Пункт " + 6 + " не отмечен");
        Assert.assertEquals("0 of 6 remaining", getLabelRemains());
        if (getLabelRemains().equals("0 of 6 remaining"))
            System.out.println("Надпись '0 of 6 remaining' присутствует");
        else
            System.out.println("Надпись '0 of 6 remaining' не присутствует");
    }


    public String getLabelRemains() {
        return labelRemains.getText();
    }


    public String getH2Name() {
        return h2Name.getText();
    }

    public WebElement getLi(int i) {
        li = driver.findElement(By.xpath("//input[@name =\"li" + i + "\"]/../span"));
        return li;
    }

    public void clickCheckbox(int i) {
        liCheckbox = driver.findElement(By.xpath("//input[@name =\"li" + i + "\"]"));
        liCheckbox.click();
    }

    public void addNewElement() {
        inputNewElement.click();
        inputNewElement.sendKeys("New element");
        btnNewElement.click();
        System.out.println("Новый элемент добавлен");
    }



}
