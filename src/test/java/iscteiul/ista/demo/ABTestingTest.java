package iscteiul.ista.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ABTestingTest {

    @Test
    public void testABTestingPage() {
        WebDriver driver = new ChromeDriver();
        ABTestingPage page = new ABTestingPage(driver);

        page.navigate();

        Assertions.assertTrue(page.isPageLoaded());
        Assertions.assertTrue(page.getHeadingText().contains("A/B Test"));

        driver.quit();
    }
}

