package TestSuite3_movieinfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VaadinDatabaseMovieInfoTests {

    private WebDriver driver;
    private VaadinDatabaseMovieInfo vaadinPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://vaadin-database-example.demo.vaadin.com/");

        vaadinPage = new VaadinDatabaseMovieInfo(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testImdbLinkIsDisplayed() {
        assertTrue(vaadinPage.isImdbLinkDisplayed(0), "IMDB link in the first row should be visible");
    }

    @Test
    public void testImdbLinkHref() {
        String href = vaadinPage.getImdbLinkHref(0);
        assertTrue(href.contains("imdb.com"), "IMDB link in the first row should contain 'imdb.com'");
    }

    @Test
    public void testClickImdbLink() throws InterruptedException {
        vaadinPage.clickImdbLink(1);

        Thread.sleep(2000);

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("imdb.com")) {
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("imdb.com"), "After clicking, the page should redirect to IMDB");

        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

}
