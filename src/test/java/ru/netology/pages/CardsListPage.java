package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardsListPage {
    private static final String FIRST_CARD_BALANCE_ID = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private static final String SECOND_CARD_BALANCE_ID = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";


    public long getBalanceByCardNumber(String cardNumber) {
        String id = cardNumber.endsWith("0001") ? FIRST_CARD_BALANCE_ID : SECOND_CARD_BALANCE_ID;
        String text = $("[data-test-id='" + id + "']").text();

        Matcher matcher = Pattern.compile("баланс:\\s*(\\d+)").matcher(text);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        throw new IllegalStateException("Не удалось распарсить баланс из текста: " + text);
    }


    public TransferPage selectCardForTopUp(String cardNumber) {
        int index = cardNumber.endsWith("0001") ? 0 : 1;
        List<SelenideElement> buttons = $$("[data-test-id='action-deposit']");
        buttons.get(index).click();
        return new TransferPage();
    }
}