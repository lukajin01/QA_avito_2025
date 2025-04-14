package tests;

import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkout(){
        mainPage.open()
                .choosePlatform("PC")
                .chooseCategory("shooter")
                .chooseSort("Relevance")
                .choosePageSize("20 / page")
                .clickGamePage("Ironsight")
                .end();
    }
}