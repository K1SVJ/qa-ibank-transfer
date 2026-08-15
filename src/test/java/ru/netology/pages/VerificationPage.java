package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement submitButton = $(byText("Продолжить"));

    public CardsListPage verify(String code) {
        codeField.setValue(code);
        submitButton.click();
        return new CardsListPage();
    }
}