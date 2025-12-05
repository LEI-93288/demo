package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ABTestingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(tagName = "h3")
    public WebElement heading;

    public ABTestingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/abtest");
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(heading));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getHeadingText() {
        return heading.getText();
    }
}

