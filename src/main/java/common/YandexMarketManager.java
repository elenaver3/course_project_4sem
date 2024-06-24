package common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class YandexMarketManager {

    public WebDriver driver;
    public YandexMarketManager(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Каталог')]")
    private WebElement catalogBtn;

//    @FindBy(xpath = "//*[contains(@href, 'https://market.yandex.ru/special/electronics_dep')]")
    @FindBy(xpath = "//*[contains(text(), 'Электроника')]")
    private WebElement electronics;

    @FindBy(xpath = "//*[contains(@class, '_3RyfO')]")
    private WebElement page;

    @FindBy(xpath = "//*[contains(@class, '_3i0uk')]")
    private WebElement page2;


    @FindBy(xpath = "//*[contains(@href, 'https://market.yandex.ru/catalog--noutbuki/26895412/list?hid=91013')]")
    private WebElement laptops;

    @FindBy(xpath = "//input[contains(@class, 'yXKAc')]")
    private WebElement inputFrom;

    @FindBy(xpath = "//input[contains(@class, 'yXKAc')]")
    private WebElement inputTo;

    @FindBy(xpath = "//*[contains(@class, '_2rw4E') and not contains(@class, 'ySif_')]")
    private WebElement element;

    @FindBy(xpath = "//*[contains(text(), 'Все фильтры')]")
    private WebElement allFilters;

    @FindBy(xpath = "//*[contains(@class, '_1Rc6L')]")
    private WebElement confirmFilters;

    @FindBy(xpath = "//*[contains(@class, '_1WMsA')]")
    private WebElement btn;

    public void catalogClick() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        catalogBtn.click();
    }

    public WebElement electronicsMove() {
        return electronics;
    }

    public WebElement laptopsMove() {
        return laptops;
    }

    public void laptopsClick() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        laptops.click();
    }

    public void electronicsClick() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        electronics.click();
    }

    public void getItems(int k) {
        String[] names = getNames(k);
        String[] prices = getPrices(k);
        for (int i = 0; i < k; i++) {
            System.out.println("Название: " + names[i] + "\nЦена: " + prices[i]);
        }
        System.out.println();
    }

    public void getNewItems(int k) {
        String[] names = getNewNames(k);
        String[] prices = getNewPrices(k);
        for (int i = 0; i < k; i++) {
            System.out.println("Название: " + names[i] + "\nЦена: " + prices[i]);
            Assert.assertTrue(isInRange(" 60 000", "110 000", prices[i]));
        }
        System.out.println();
    }

    public String[] getNames(int k) {
        String[] names = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, '_2rw4E') and not (contains(@class, 'ySif_'))]"));
        for (WebElement item : items) {
            WebElement name = item.findElement(By.className("G_TNq"));
            names[j] = String.valueOf(name.getText());
            j++;
            if (j == k)
                break;
        }

        return names;
    }

    public String[] getPrices(int k) {
        String[] prices = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, '_2rw4E') and not (contains(@class, 'ySif_'))]"));
        for (WebElement item : items) {
            WebElement price = item.findElement(By.className("_1ArMm"));
            prices[j] = String.valueOf(price.getText());
            j++;
            if (j == k)
                break;
        }

        return prices;
    }



    public String[] getNewNames(int k) {
        String[] names = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, '_2rw4E') and not (contains(@class, 'ySif_'))]"));
        for (WebElement item : items) {
            WebElement name = item.findElement(By.className("G_TNq"));
            names[j] = String.valueOf(name.getText());
            j++;
            if (j == k)
                break;
        }

        return names;
    }

    public String[] getNewPrices(int k) {
        String[] prices = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, '_2rw4E') and not (contains(@class, 'ySif_'))]"));
        for (WebElement item : items) {
            String price = item.findElement(By.className("_1ArMm")).getText();
            prices[j] = price;
            j++;
            if (j == k)
                break;
        }

        return prices;
    }

    public void setPrices(String priceBefore, String priceAfter) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        allFilters.click();
        List<WebElement> items = page2.findElements(By.xpath("//*[contains(@class, '_2xtC2')]"));
        inputFrom = items.get(0);
        inputTo = items.get(1);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        inputTo.click();
        inputTo.sendKeys(priceAfter);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        btn.click();
        btn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        inputFrom.click();
        inputFrom.sendKeys(priceBefore);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        btn.click();
        btn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        confirmFilters.click();
    }

    public boolean isInRange(String before, String after, String selected) {
        if (selected.length() == 6)
            selected = " " + selected;
        int res1 = selected.compareTo(before);
        int res2 = selected.compareTo(after);
        if (res1 >= 0 && res2 <= 0)
            return true;
        else
            return false;
    }
}
