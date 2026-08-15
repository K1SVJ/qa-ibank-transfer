package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement fromField = $("[data-test-id='from'] input");   // ← ДОБАВИЛИ input
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");


    public CardsListPage transfer(String fromCard, String amount) {
        fromField.setValue(fromCard);
        transferButton.click();
        return new CardsListPage();
    }
}