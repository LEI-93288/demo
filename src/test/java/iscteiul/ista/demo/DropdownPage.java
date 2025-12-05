package iscteiul.ista.demo;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class DropdownPage {

    @FindBy(id = "dropdown")
    public WebElement dropdownElement;

    private Select dropdown;

    public DropdownPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        dropdown = new Select(dropdownElement);
    }

    public void selectOptionByValue(String value) {
        dropdown.selectByValue(value);
    }

    public void selectOptionByVisibleText(String text) {
        dropdown.selectByVisibleText(text);
    }

    public String getSelectedOption() {
        return dropdown.getFirstSelectedOption().getText();
    }

    public boolean isOptionSelected(String text) {
        return getSelectedOption().equals(text);
    }

    public List<WebElement> getAllOptions() {
        return dropdown.getOptions();
    }

    public int getOptionsCount() {
        return dropdown.getOptions().size();
    }

    public boolean isMultiple() {
        return dropdown.isMultiple();
    }
}
