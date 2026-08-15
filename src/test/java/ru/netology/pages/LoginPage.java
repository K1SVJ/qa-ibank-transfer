package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id='login'] input");
    private final SelenideElement passwordField = $("[data-test-id='password'] input");
    private final SelenideElement submitButton = $(byText("Продолжить"));

    public VerificationPage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
        return new VerificationPage();
    }
}