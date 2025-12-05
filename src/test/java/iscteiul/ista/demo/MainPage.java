package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    // Botão de pesquisa no site JetBrains
    @FindBy(css = "[data-test='search-button']")
    public WebElement searchButton;

    // Menu Tools
    @FindBy(css = "[data-test='main-menu-tools']")
    public WebElement toolsMenu;

    // Botões de navegação dentro do site
    @FindBy(css = "[data-test='see-developer-tools-button']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(css = "[data-test='find-your-tools-button']")
    public WebElement findYourToolsButton;

    // Construtor
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
