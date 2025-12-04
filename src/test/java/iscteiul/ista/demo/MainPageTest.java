package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        clickCookieBannerIfVisible();
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("input[data-test$='inner']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button'] span[data-test='button__content']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test$='inner']"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement(By.cssSelector("html > body > div:nth-of-type(2) > div:nth-of-type(1) > " +
                "div:nth-of-type(3) > header > div > div > div:nth-of-type(2) > div:nth-of-type(1) > div > nav > div:nth-of-type(2) > div"));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }

    private void clickCookieBannerIfVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            WebElement cookieAcceptBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("button.ch2-btn.ch2-allow-all-btn.ch2-btn-primary")
                    )
            );
            cookieAcceptBtn.click();
        } catch (Exception ignored) {
            // Cookie banner not present or not clickable; continue test flow
        }
    }

}
