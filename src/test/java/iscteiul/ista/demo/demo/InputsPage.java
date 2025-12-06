package iscteiul.ista.demo.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputsPage {

    private WebDriver driver;
    private By inputField = By.tagName("input");

    public InputsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/inputs");
    }

    public void enterNumber(String number) {
        WebElement input = driver.findElement(inputField);
        input.clear();
        input.sendKeys(number);
    }

    public String getValue() {
        return driver.findElement(inputField).getAttribute("value");
    }
}
