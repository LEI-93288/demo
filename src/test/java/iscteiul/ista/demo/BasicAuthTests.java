package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class BasicAuthTests {

    private WebDriver driver;
    private BasicAuthPage basicAuthPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        basicAuthPage = new BasicAuthPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAuthenticationWithValidCredentials() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        assertTrue(basicAuthPage.isPageLoaded());
        assertTrue(basicAuthPage.isAuthenticationSuccessful());
    }

    @Test
    public void testPageHeadingContainsBasicAuth() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        String heading = basicAuthPage.getPageHeading();
        assertTrue(heading.contains("Basic Auth"));
    }

    @Test
    public void testSuccessMessageDisplayed() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        String message = basicAuthPage.getSuccessMessage();
        assertNotNull(message, "Success message should not be null");
        assertTrue(message.contains("Congratulations"), "Message should contain 'Congratulations', but got: " + message);
    }

    @Test
    public void testCurrentUrlContainsBasicAuth() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        String url = basicAuthPage.getCurrentUrl();
        assertTrue(url.contains("basic_auth"));
    }

    @Test
    public void testPageTitleNotEmpty() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        String title = basicAuthPage.getPageTitle();
        assertNotNull(title);
        assertFalse(title.isEmpty());
    }

    @Test
    public void testAuthenticationSuccess() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        assertTrue(basicAuthPage.isAuthenticationSuccessful());
    }

    @Test
    public void testPageLoadedSuccessfully() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        assertTrue(basicAuthPage.isPageLoaded(), "Page should be loaded with valid credentials");
    }

    @Test
    public void testHeadingTextIsNotEmpty() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        String heading = basicAuthPage.getPageHeading();
        assertNotNull(heading);
        assertFalse(heading.isEmpty());
    }

    @Test
    public void testSuccessMessageIsNotEmpty() {
        basicAuthPage.navigateToBasicAuth("admin", "admin");
        String message = basicAuthPage.getSuccessMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
    }

    @Test
    public void testMultipleAuthenticationAttempts() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            basicAuthPage.navigateToBasicAuth("admin", "admin");
            assertTrue(basicAuthPage.isAuthenticationSuccessful());
            Thread.sleep(500);
        }
    }
}