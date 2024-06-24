package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class LitresManager {

    public WebDriver driver;
    public LitresManager(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@class, 'CatalogButton_wrapper__nZ5n8')]")
    private WebElement catalogBtn;

    @FindBy(xpath = "//a[contains(text(), 'Фантастика')]")
    private WebElement category;

    @FindBy(xpath = "//a[contains(text(), 'Менеджмент')]")
    private WebElement category2;

    @FindBy(xpath = "//a[contains(text(), 'Интерьеры')]")
    private WebElement category3;

    @FindBy(xpath = "//div[contains(@class, 'Dropdown_dropdown__input__yrc2U')]")
    private WebElement filter1;

    @FindBy(xpath = "//div[contains(text(), 'Новинки')]")
    private WebElement filterNew;

    @FindBy(xpath = "//div[contains(@class, 'ArtDefault_container__0yjZl')]")
    private WebElement book;

    @FindBy(xpath = "//div[contains(@class, 'PaginatedContent_content__HG6bS')]")
    private WebElement page;

    @FindBy(xpath = "//*[contains(@class, 'ArtDefault_like_button__V9gv1')]")
    private WebElement likeBtn;

    @FindBy(xpath = "//a[contains(@class, 'Tab_tab__link__uuF1u')]")
    private WebElement likedCategoryBtn;

    @FindBy(xpath = "//label[contains(text(), 'Английский')]")
    private WebElement langEn;

    @FindBy(xpath = "//label[contains(text(), 'Текст')]")
    private WebElement textBooks;

    @FindBy(xpath = "//*[contains(@class, 'BookLanguage_language__description__9Dkvu')]")
    private WebElement bookLang;

    @FindBy(xpath = "//*[contains(@class, 'BookTabsFormat_bookTabsFormat__3HdHL')]")
    private WebElement bookFormat;

    @FindBy(xpath = "//*[contains(@class, 'FiltersGrid_content__t_W3L')]")
    private WebElement likedPage;

    public void catalogClick() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        catalogBtn.click();
    }

    public void categoryClick() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        category.click();
    }

    public void category2Click() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        category2.click();
    }

    public void category3Click() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        category3.click();
    }

    public void chooseFilterNew() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        filter1.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        filterNew.click();
    }

    public String likeBtnClick() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        likeBtn.click();
        WebElement item = page.findElement(By.xpath("//*[contains(@class, 'ArtDefault_container__0yjZl')]"));
        String name = item.findElement(By.className("ArtInfo_title__h_5Ay")).getText();
        return name;
    }

    public void likedCategoryBtnClick() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        likedCategoryBtn.click();
    }

    public void getBooks(int k) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String[] authors = getAuthors(k);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String[] prices = getPrices(k);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String[] names = getNames(k);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        for (int i = 0; i < k; i++) {
            System.out.println("Автор: " + authors[i] + "\nНазвание: " + names[i] + "\nЦена: " + prices[i] + "\n--\n");
        }
    }

    public void chooseEn() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        langEn.click();
    }

    public void chooseTextBooks() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        textBooks.click();
    }

    public void firstBookClick() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement book = page.findElement(By.className("ArtInfo_title__h_5Ay"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        book.click();
    }

    public boolean checkLang() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        if (bookLang.getText().equals("Книга на английском"))
            return true;
        else
            return false;
    }

    public boolean checkFormat() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String format = bookFormat.findElement(By.xpath("//div[contains(text(), 'Читать фрагмент')]")).getText();
        if (format.equals("Читать фрагмент"))
            return true;
        else
            return false;
    }

    private String[] getAuthors(int k) {
        String[] authors = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, 'ArtDefault_container__0yjZl')]"));
        for (WebElement item : items) {
            String author = item.findElement(By.className("ArtInfo_author__0W3GJ")).getText();
            authors[j] = author;
            j++;
            if (j == k)
                break;
        }

        return authors;
    }

    private String[] getNames(int k) {
        String[] names = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, 'ArtDefault_container__0yjZl')]"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("ArtInfo_title__h_5Ay")).getText();
            names[j] = name;
            j++;
            if (j == k)
                break;
        }

        return names;
    }

    private String[] getPrices(int k) {
        String[] prices = new String[k];

        int j = 0;
        List<WebElement> items = page.findElements(By.xpath("//*[contains(@class, 'ArtDefault_container__0yjZl')]"));
        for (WebElement item : items) {
            String price = item.findElement(By.className("ArtPriceFooter_ArtPriceFooterPrices__final__7AMjB")).getText();
            prices[j] = price;
            j++;
            if (j == k)
                break;
        }

        return prices;
    }

    public String getName() {
        String name = likedPage.findElement(By.className("ArtInfo_title__h_5Ay")).getText();
        return name;
    }
}
