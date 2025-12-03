package iscteiul.ista.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class DropdownTest {

    private WebDriver driver;
    private DropdownPage dropdownPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        dropdownPage = new DropdownPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSelectOption1() throws InterruptedException {
        dropdownPage.dropdownElement.click();
        dropdownPage.selectOptionByValue("1");
        assertTrue(dropdownPage.isOptionSelected("Option 1"));
        sleep(1000);

    }

    @Test
    public void testSelectOption2() {
        dropdownPage.dropdownElement.click();
        dropdownPage.selectOptionByVisibleText("Option 2");
        assertTrue(dropdownPage.isOptionSelected("Option 2"));
    }

    @Test
    public void testDefaultOption()  {
        dropdownPage.dropdownElement.click();
        String selected = dropdownPage.getSelectedOption();
        assertEquals("Please select an option", selected);
    }

    @Test
    public void testOptionsCount() {
        dropdownPage.dropdownElement.click();
        assertEquals(3, dropdownPage.getOptionsCount());
    }

    @Test
    public void testOptionTexts() {
        dropdownPage.dropdownElement.click();
        List<WebElement> options = dropdownPage.getAllOptions();

        assertEquals("Please select an option", options.get(0).getText());
        assertEquals("Option 1", options.get(1).getText());
        assertEquals("Option 2", options.get(2).getText());
    }

    @Test
    public void testNotMultipleSelect() {
        assertFalse(dropdownPage.isMultiple());
    }

    @Test
    public void testInvalidSelection() {
        assertThrows(NoSuchElementException.class, () ->
                dropdownPage.selectOptionByValue("999")
        );
    }




}

