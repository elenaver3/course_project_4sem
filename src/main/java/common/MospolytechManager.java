package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

public class MospolytechManager {
    public WebDriver driver;
    public MospolytechManager(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(@title, 'Расписание')]")
    private WebElement raspBtn;

    @FindBy(xpath = "//a[contains(@href, 'https://rasp.dmami.ru/session')]")
    private WebElement watchOnSite;

    @FindBy(xpath = "//input[contains(@class, 'groups')]")
    private WebElement inputGroup;

    @FindBy(xpath = "//*[contains(@id, '111-111')]")
    private WebElement groupBtn;

    @FindBy(xpath = "//*[contains(@class, 'schedule-day_today')]/div[1]")
    private WebElement chosenDate;

    public void clickRaspBtn() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        raspBtn.click();
    }

    public void clickWatchOnSiteBtn() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        watchOnSite.click();
        newTab();
    }

    public void findGroup(String groupNumber) {
        inputGroup.click();
        inputGroup.sendKeys(groupNumber);
    }

    public void chooseGroup(String groupNumber) {
        groupBtn = driver.findElement(By.xpath("//*[@id =\"" + groupNumber + "\"]"));
        groupBtn.click();
    }

    public boolean getChosenDate() {
        return compareDates(chosenDate);
    }

    private void newTab(){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }
    }

    private boolean compareDates(WebElement selectedDate) {
        String selectedDateStr = selectedDate.getText().toLowerCase();
        Locale ru = new Locale("ru", "RU");
        String dow = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, ru);
        String month = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, ru).substring(0, 3);
        String[] parts = String.valueOf(LocalDate.now()).split("-");
        String day = parts[2];

        parts = selectedDateStr.split(" ");
        if (parts[0].equals(dow) && parts[2].equals(day) && parts[3].equals(month))
            return true;
        else
            return false;
    }

}
