package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    private static final By PLATFORM_SELECTOR = By.xpath("//div[text()='Filter by platform']/following-sibling::div//div[contains(@class, 'ant-select-selector')]");
    private static final By CATEGORY_SELECTOR = By.xpath("//div[text()='Filter by category']/following-sibling::div//div[contains(@class, 'ant-select-selector')]");
    private static final By SORT_SELECTOR = By.xpath("//div[text()='Sort by']/following-sibling::div//div[contains(@class, 'ant-select-selector')]");
    private static final By PAGE_SIZE_SELECTOR = By.cssSelector("li.ant-pagination-options .ant-select-selector");

    private static final String CHOOSE_FILTER = "//div[@class='ant-select-item-option-content' and text()='%s']";
    private static final String CHOOSE_GAME = "//div[contains(@class, 'ant-card')]//h1[text()='%s']/ancestor::div[contains(@class, 'ant-card')]";

    @Step("Открытие страницы MainPage")
    public MainPage open(){
        driver.get("https://makarovartem.github.io/frontend-avito-tech-test-assignment");
        return this;
    }

    @Step("Выбор платформы игр - {platform}")
    public MainPage choosePlatform(String platform){
        driver.findElement(PLATFORM_SELECTOR).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(CHOOSE_FILTER, platform)))).click();
        return this;
    }

    @Step("Выбор категории игр - {category}")
    public MainPage chooseCategory(String category){
        driver.findElement(CATEGORY_SELECTOR).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(CHOOSE_FILTER, category)))).click();
        return this;
    }

    @Step("Выбор сортировки игр - {sort}")
    public MainPage chooseSort(String sort){
        driver.findElement(SORT_SELECTOR).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(CHOOSE_FILTER, sort)))).click();
        return this;
    }

    @Step("Выбор количества игр на странице - {pageSize}")
    public MainPage choosePageSize(String pageSize){
        driver.findElement(PAGE_SIZE_SELECTOR).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(CHOOSE_FILTER, pageSize)))).click();
        return this;
    }

    @Step("Переход на страницу игры - {game}")
    public GamePage clickGamePage(String game){
        while(true){
            List<WebElement> elements = driver.findElements(By.xpath(String.format(CHOOSE_GAME, game)));
            if(!elements.isEmpty()){
                elements.get(0).click();
                return new GamePage(driver);
            }
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Next Page' and @aria-disabled='false']//button")));
            if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                nextButton.click();
            } else if (nextButton.isDisplayed() && !nextButton.isEnabled()) {
                throw new NoSuchElementException("Игра \"" + game + "\" не найдена на всех страницах.");
            }
        }
    }
}