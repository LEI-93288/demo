package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class HorizontalSliderTests {

    private WebDriver driver;
    private HorizontalSliderPage sliderPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
        sliderPage = new HorizontalSliderPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDragSliderToMaxValue() throws InterruptedException {
        sliderPage.dragSliderToValue(5.0);
        assertEquals(5.0, sliderPage.getCurrentSliderValue());
        sleep(1000);
    }

    @Test
    public void testDragSliderToMinValue() throws InterruptedException {
        sliderPage.dragSliderToValue(0.0);
        assertEquals(0.0, sliderPage.getCurrentSliderValue());
        sleep(1000);
    }

    @Test
    public void testDragSliderToMiddleValue() throws InterruptedException {
        sliderPage.dragSliderToValue(2.5);
        assertEquals(2.5, sliderPage.getCurrentSliderValue());
        sleep(1000);
    }

    @Test
    public void testMoveSliderWithArrowKeys() throws InterruptedException {
        sliderPage.moveSliderWithArrowKeys("right", 3);
        assertTrue(sliderPage.getCurrentSliderValue() >= 1.5);
        sleep(1000);
    }

    @Test
    public void testMoveSliderLeftWithArrowKeys() throws InterruptedException {
        sliderPage.dragSliderToValue(5.0);
        double valueAfterDrag = sliderPage.getCurrentSliderValue();
        assertEquals(5.0, valueAfterDrag, "Slider should be at 5.0 after drag");

        sliderPage.moveSliderWithArrowKeys("left", 2);
        double valueAfterMove = sliderPage.getCurrentSliderValue();
        assertTrue(valueAfterMove < 5.0, "Value should decrease after moving left. Current: " + valueAfterMove);
        sleep(1000);
    }

    @Test
    public void testSliderValueSyncedWithDisplay() throws InterruptedException {
        sliderPage.dragSliderToValue(2.0);
        assertTrue(sliderPage.isSliderValueSyncedWithDisplay());
    }

    @Test
    public void testResetSlider() throws InterruptedException {
        sliderPage.dragSliderToValue(5.0);
        sliderPage.resetSlider();
        assertEquals(0.0, sliderPage.getCurrentSliderValue());
        sleep(1000);
    }

    @Test
    public void testGetMinAndMaxValues() {
        assertEquals(0.0, sliderPage.getMinValue());
        assertEquals(5.0, sliderPage.getMaxValue());
    }

    @Test
    public void testInitialSliderValue() {
        double initialValue = sliderPage.getCurrentSliderValue();
        assertTrue(initialValue >= 0 && initialValue <= 5.0);
    }

    @Test
    public void testMultipleDragOperations() throws InterruptedException {
        sliderPage.dragSliderToValue(1.0);
        assertEquals(1.0, sliderPage.getCurrentSliderValue());
        sleep(500);

        sliderPage.dragSliderToValue(4.0);
        assertEquals(4.0, sliderPage.getCurrentSliderValue());
        sleep(500);

        sliderPage.dragSliderToValue(2.0);
        assertEquals(2.0, sliderPage.getCurrentSliderValue());
        sleep(1000);
    }
}