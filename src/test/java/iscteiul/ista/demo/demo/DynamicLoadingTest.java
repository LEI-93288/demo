package iscteiul.ista.demo.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicLoadingTest {

    private WebDriver driver;
    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.open();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDynamicLoadingFinishesCorrectly() {
        dynamicLoadingPage.clickStart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        assertEquals("Hello World!", dynamicLoadingPage.getFinishedText());
    }
}
