package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.pages.CardsListPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.headless;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferBugTest {
    private static final String FIRST_CARD = "5559 0000 0000 0001";
    private static final String SECOND_CARD = "5559 0000 0000 0002";

    @Test
    public void shouldRejectTransferAmountExceedingBalance() {
        headless = true;
        baseUrl = "http://localhost:9999";
        timeout = 15000;
        open("/");

        CardsListPage cards = new LoginPage()
                .login("vasya", "qwerty123")
                .verify("12345");

        long firstBefore = cards.getBalanceByCardNumber(FIRST_CARD);

        // Пытаемся перевести 20000 с карты с балансом 10000
        TransferPage transfer = cards.selectCardForTopUp(SECOND_CARD);
        CardsListPage after = transfer.transfer(FIRST_CARD, "20000");

        long firstAfter = after.getBalanceByCardNumber(FIRST_CARD);

        // Баланс не должен быть отрицательным!
        assertTrue(firstAfter >= 0,
                "БАГ: Баланс карты не может быть отрицательным. Было: " + firstBefore + ", стало: " + firstAfter);
    }
}