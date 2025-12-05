package iscteiul.ista.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddRemoveElementsTest {

    @Test
    public void testAddRemoveElements() {
        WebDriver driver = new ChromeDriver();
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver);

        page.navigate();
        page.clickAddButton();

        Assertions.assertTrue(page.isDeleteButtonVisible());

        page.clickDeleteButton();
        Assertions.assertTrue(page.isDeleteButtonRemoved());

        driver.quit();
    }
}
