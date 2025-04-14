package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.GamePage;
import pages.MainPage;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    MainPage mainPage;
    GamePage gamePage;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        mainPage = new MainPage(driver);
        gamePage = new GamePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}