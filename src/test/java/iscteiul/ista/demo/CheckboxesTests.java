package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class CheckboxesTests {

    private WebDriver driver;
    private CheckboxesPage checkboxesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        checkboxesPage = new CheckboxesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCheckbox1Check() throws InterruptedException {
        checkboxesPage.checkCheckbox1();
        assertTrue(checkboxesPage.checkbox1.isSelected());
        sleep(1000);
    }
    @Test
    public void testCheckbox1UnCheck() throws InterruptedException {
        checkboxesPage.uncheckCheckbox1();
        assertFalse(checkboxesPage.checkbox1.isSelected());
        sleep(1000);
    }

    @Test
    public void testCheckbox2Uncheck() {
        checkboxesPage.uncheckCheckbox2();
        assertFalse(checkboxesPage.checkbox2.isSelected());
    }

    @Test
    public void testCheckBoth() {
        checkboxesPage.checkCheckbox1();
        checkboxesPage.checkCheckbox2();

        assertTrue(checkboxesPage.isCheckbox1Checked());
        assertTrue(checkboxesPage.isCheckbox2Checked());
    }
    @Test
    public void testInitialStates() throws InterruptedException {
        boolean cb1 = checkboxesPage.checkbox1.isSelected();
        boolean cb2 = checkboxesPage.checkbox2.isSelected();

        assertFalse(cb1);
        assertTrue(cb2);

        sleep(1000);
    }
}