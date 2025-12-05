package iscteiul.ista.demo.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicLoadingPage {

    private WebDriver driver;

    private By startButton = By.cssSelector("#start button");
    private By finishText = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }

    public String getFinishedText() {
        WebElement finish = driver.findElement(finishText);
        return finish.getText();
    }
}
