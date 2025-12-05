package iscteiul.ista.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AddRemoveElementsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[text()='Add Element']")
    public WebElement addButton;

    @FindBy(className = "added-manually")
    public List<WebElement> deleteButtons;

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public boolean isDeleteButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(deleteButtons));
            return !deleteButtons.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDeleteButton() {
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
        }
    }

    public boolean isDeleteButtonRemoved() {
        return deleteButtons.isEmpty();
    }
}

