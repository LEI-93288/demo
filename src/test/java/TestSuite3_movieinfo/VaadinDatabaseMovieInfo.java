package TestSuite3_movieinfo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// page_url = https://vaadin-database-example.demo.vaadin.com/
public class VaadinDatabaseMovieInfo {

    @FindBy(css = "vaadin-grid-cell-content[slot^='vaadin-grid-cell-content-'] a[target='_blank']")
    public List<WebElement> imdbLinks;

    private WebDriver driver;

    public VaadinDatabaseMovieInfo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isImdbLinkDisplayed(int rowIndex) {
        return imdbLinks.get(rowIndex).isDisplayed();
    }

    public String getImdbLinkHref(int rowIndex) {
        return imdbLinks.get(rowIndex).getAttribute("href");
    }

    public void clickImdbLink(int rowIndex) {
        imdbLinks.get(rowIndex).click();
    }
}