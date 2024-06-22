package common;

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
    }
}
