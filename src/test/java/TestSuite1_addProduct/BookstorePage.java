package TestSuite1_addProduct;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BookstorePage {

    // -------------------------
    // LOGIN ELEMENTS
    // -------------------------
    private SelenideElement usernameInput = $("input[name='username']");
    private SelenideElement passwordInput = $("input[name='password']");
    private SelenideElement loginButton   = $("vaadin-button[part='vaadin-login-submit']");
    private SelenideElement mainGrid      = $("vaadin-grid");

    // -------------------------
    // TOOLBAR (Botões e Filtro)
    // -------------------------
    private SelenideElement addProductButton = $("vaadin-button[theme='primary']");

    private SelenideElement filterInput =
            $x("//vaadin-button[@theme='primary']/preceding-sibling::vaadin-text-field");

    // -------------------------
    // FORM ELEMENTS
    // -------------------------
    // Contentor principal do formulário
    private SelenideElement formLayout = $("vaadin-vertical-layout.product-form-content");

    // Campos
    private SelenideElement titleInput =
            $x("//vaadin-vertical-layout[contains(@class, 'product-form-content')]/vaadin-text-field[1]");

    private SelenideElement priceInput =
            $x("//vaadin-vertical-layout[contains(@class, 'product-form-content')]//vaadin-horizontal-layout/vaadin-text-field[1]");

    private SelenideElement stockInput =
            $x("//vaadin-vertical-layout[contains(@class, 'product-form-content')]//vaadin-horizontal-layout/vaadin-text-field[2]");

    private SelenideElement availabilitySelect =
            $x("//vaadin-vertical-layout[contains(@class, 'product-form-content')]//vaadin-select");

    private SelenideElement firstCategoryCheckbox =
            $x("//vaadin-vertical-layout[contains(@class, 'product-form-content')]//vaadin-checkbox-group//vaadin-checkbox[1]");

    private SelenideElement saveButton =
            $x("//vaadin-vertical-layout[contains(@class, 'product-form-content')]//vaadin-button[contains(., 'Save')]");


    // -------------------------
    // MÉTODOS DE AÇÃO
    // -------------------------

    public boolean isGridVisible() {
        return mainGrid.shouldBe(visible).isDisplayed();
    }

    public boolean isProductVisibleInList(String title) {
        if (filterInput.isDisplayed()) {
            setVaadinValue(filterInput, title);
            Selenide.sleep(500); // Pequena pausa para a grid reagir ao filtro
        }

        return $x("//vaadin-grid-cell-content[contains(.,'" + title + "')]").exists();
    }

    public BookstorePage login(String username, String password) {
        if (usernameInput.isDisplayed()) {
            usernameInput.setValue(username);
            passwordInput.setValue(password);
            loginButton.click();
        }
        mainGrid.shouldBe(visible);
        return this;
    }

    public BookstorePage openAddProductForm() {
        if (filterInput.exists() && filterInput.isDisplayed()) {
            String val = filterInput.getValue();
            if (val != null && !val.isEmpty()) {
                filterInput.clear();
                Selenide.sleep(300);
            }
        }

        addProductButton.shouldBe(visible);
        Selenide.executeJavaScript("arguments[0].click();", addProductButton);
        titleInput.shouldBe(visible);
        return this;
    }

    public BookstorePage fillProductForm(String title, String price, String stockInt) {
        setVaadinValue(titleInput, title);
        setVaadinValue(priceInput, price);
        setVaadinValue(stockInput, stockInt);

        availabilitySelect.shouldBe(visible).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        // Checkbox Categoria
        if (firstCategoryCheckbox.isDisplayed() && !firstCategoryCheckbox.isSelected()) {
            firstCategoryCheckbox.click();
        }
        return this;
    }

    public BookstorePage submitProduct() {
        saveButton.shouldNotHave(Condition.attribute("disabled"));
        Selenide.executeJavaScript("arguments[0].click();", saveButton);
        formLayout.should(disappear);

        return this;
    }


    private void setVaadinValue(SelenideElement element, String value) {
        element.shouldBe(visible);
        Selenide.executeJavaScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element, value
        );
    }
}