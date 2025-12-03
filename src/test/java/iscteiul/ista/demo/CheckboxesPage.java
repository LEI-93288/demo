package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxesPage {

    @FindBy(xpath = "//form[@id='checkboxes']/input[1]")
    public WebElement checkbox1;

    @FindBy(xpath = "//form[@id='checkboxes']/input[2]")
    public WebElement checkbox2;

    public CheckboxesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void checkCheckbox1() {
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
    }

    public void uncheckCheckbox1() {
        if (checkbox1.isSelected()) {
            checkbox1.click();
        }
    }

    public boolean isCheckbox1Checked() {
        return checkbox1.isSelected();
    }

    public void checkCheckbox2() {
        if (!checkbox2.isSelected()) {
            checkbox2.click();
        }
    }

    public void uncheckCheckbox2() {
        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
    }

    public boolean isCheckbox2Checked() {
        return checkbox2.isSelected();
    }
}