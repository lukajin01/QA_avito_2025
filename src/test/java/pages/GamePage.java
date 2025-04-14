package pages;

import org.openqa.selenium.WebDriver;

public class GamePage {

    WebDriver driver;

    public GamePage(WebDriver driver) {
        this.driver = driver;
    }

    public void end(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }

}