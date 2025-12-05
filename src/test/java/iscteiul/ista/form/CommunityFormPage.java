package iscteiul.ista.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object para o formulário Vaadin "Signup form"
 * https://vaadin-form-example.demo.vaadin.com/
 */
public class CommunityFormPage {

    private static final String URL = "https://vaadin-form-example.demo.vaadin.com/";

    public static CommunityFormPage openPage() {
        open(URL);
        return new CommunityFormPage();
    }

    // --------- Elementos ---------

    private SelenideElement firstNameField() {
        return $("vaadin-text-field[label='First name']");
    }

    private SelenideElement lastNameField() {
        return $("vaadin-text-field[label='Last name']");
    }

    private SelenideElement handleField() {
        return $("vaadin-text-field[label='Handle']");
    }

    private SelenideElement passwordField() {
        return $("vaadin-password-field[label='Password']");
    }

    private SelenideElement marketingCheckbox() {
        return $("vaadin-checkbox");
    }

    private SelenideElement submitButton() {
        return $("vaadin-button[theme*='primary']");
    }

    private SelenideElement successMessage() {
        return $("vaadin-notification-card");
    }

    // --------- Ações ---------

    public CommunityFormPage fillFirstName(String name) {
        firstNameField().setValue(name);
        return this;
    }

    public CommunityFormPage fillLastName(String name) {
        lastNameField().setValue(name);
        return this;
    }

    public CommunityFormPage fillHandle(String handle) {
        handleField().setValue(handle);
        return this;
    }

    public CommunityFormPage fillPassword(String pass) {
        passwordField().setValue(pass);
        return this;
    }

    public CommunityFormPage acceptMarketing() {
        if (!marketingCheckbox().isSelected()) {
            marketingCheckbox().click();
        }
        return this;
    }

    public void submit() {
        submitButton().click();
    }

    public String getSuccessMessageText() {
        return successMessage().getText();
    }
}
