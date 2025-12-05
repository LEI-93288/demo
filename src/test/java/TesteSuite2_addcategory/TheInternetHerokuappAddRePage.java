package TesteSuite2_addcategory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TheInternetHerokuappAddRePage {

    private WebDriver driver;

    // Botão Add Element
    @FindBy(xpath = "//button[text()='Add Element']")
    private WebElement addButton;

    // Lista de botões Delete adicionados
    @FindBy(xpath = "//button[text()='Delete']")
    public List<WebElement> deleteButtons;

    // Construtor
    public TheInternetHerokuappAddRePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Clica no botão Add Element
    public void clickAddButton() {
        addButton.click();
    }

    // Verifica se existe botão Delete no índice
    public boolean isDeleteButtonPresent(int index) {
        return deleteButtons.size() > index && deleteButtons.get(index).isDisplayed();
    }

    // Clica no botão Delete no índice
    public void clickDeleteButton(int index) {
        deleteButtons.get(index).click();
    }

    // Retorna o texto do botão Delete no índice
    public String getDeleteButtonText(int index) {
        return deleteButtons.get(index).getText();
    }
}
