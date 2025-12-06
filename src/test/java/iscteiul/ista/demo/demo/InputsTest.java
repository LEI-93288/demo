package iscteiul.ista.demo.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class InputsTest {

    private WebDriver driver;
    private InputsPage inputsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        inputsPage = new InputsPage(driver);
        inputsPage.open();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEnterNumber() {
        inputsPage.enterNumber("123");
        assertEquals("123", inputsPage.getValue());
    }

    @Test
    public void testOverwriteNumber() {
        inputsPage.enterNumber("10");
        inputsPage.enterNumber("99");

        assertEquals("99", inputsPage.getValue());
    }
}
