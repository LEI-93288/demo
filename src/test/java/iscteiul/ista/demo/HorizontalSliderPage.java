package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;

public class HorizontalSliderPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@type='range']")
    public WebElement slider;

    @FindBy(xpath = "//span[@id='range']")
    public WebElement rangeValue;

    public HorizontalSliderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dragSliderToValue(double targetValue) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Define o valor via JavaScript
        js.executeScript("arguments[0].value = arguments[1];", slider, targetValue);

        // Trigger change event
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", slider);

        Thread.sleep(500);
    }

    public void moveSliderWithArrowKeys(String direction, int times) throws InterruptedException {
        slider.click();
        Thread.sleep(200);

        Keys key = direction.equalsIgnoreCase("right") ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        for (int i = 0; i < times; i++) {
            slider.sendKeys(key);
            Thread.sleep(150);
        }

        Thread.sleep(500);
    }

    public double getCurrentSliderValue() {
        String value = slider.getAttribute("value");
        return Double.parseDouble(value);
    }

    public String getDisplayedRangeValue() {
        return rangeValue.getText();
    }

    public void resetSlider() throws InterruptedException {
        dragSliderToValue(0.0);
    }

    public double getMinValue() {
        String minValue = slider.getAttribute("min");
        return Double.parseDouble(minValue);
    }

    public double getMaxValue() {
        String maxValue = slider.getAttribute("max");
        return Double.parseDouble(maxValue);
    }

    public boolean isSliderValueSyncedWithDisplay() {
        double sliderValue = getCurrentSliderValue();
        String displayedValue = getDisplayedRangeValue().trim();
        String sliderValueStr = formatValue(sliderValue);
        return sliderValueStr.equals(displayedValue);
    }

    private String formatValue(double value) {
        if (value == Math.floor(value)) {
            return String.format("%.0f", value);
        } else {
            return String.valueOf(value);
        }
    }
}