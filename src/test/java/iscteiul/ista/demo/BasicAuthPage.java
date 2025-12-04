package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

public class BasicAuthPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(tagName = "h3")
    public WebElement heading;

    @FindBy(tagName = "p")
    public WebElement successMessage;

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToBasicAuth(String username, String password) {
        String url = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
        driver.get(url);
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(heading));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageHeading() {
        return heading.getText();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public boolean isAuthenticationSuccessful() {
        try {
            String heading = getPageHeading();
            String message = getSuccessMessage();
            return heading.contains("Basic Auth") && message.contains("Congratulations");
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}