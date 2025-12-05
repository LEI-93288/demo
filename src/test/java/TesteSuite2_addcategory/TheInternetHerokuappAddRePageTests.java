package TesteSuite2_addcategory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TheInternetHerokuappAddRePageTests {

    private WebDriver driver;
    private TheInternetHerokuappAddRePage addCategoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        addCategoryPage = new TheInternetHerokuappAddRePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddButtonIsDisplayed() {
        // Clica no botão Add Element
        addCategoryPage.clickAddButton();

        // Verifica se o botão Delete foi adicionado
        assertTrue(addCategoryPage.isDeleteButtonPresent(0), "O botão Delete deveria estar visível");
        assertEquals("Delete", addCategoryPage.getDeleteButtonText(0), "O texto do botão Delete está incorreto");
    }

    @Test
    public void testClickDeleteButton() {
        // Adiciona dois botões Delete
        addCategoryPage.clickAddButton();
        addCategoryPage.clickAddButton();

        // Clica no primeiro botão Delete
        addCategoryPage.clickDeleteButton(0);

        // Verifica se o primeiro botão foi removido
        assertEquals(1, addCategoryPage.deleteButtons.size(), "Deveria restar apenas um botão Delete após remoção");
    }
}
