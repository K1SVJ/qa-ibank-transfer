package ru.netology;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.pages.CardsListPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.headless;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
    private static final String FIRST_CARD = "5559 0000 0000 0001";
    private static final String SECOND_CARD = "5559 0000 0000 0002";
    private static final long TRANSFER_AMOUNT = 1000;

    @Test
    public void shouldTransferBetweenOwnCards() {
        headless = true;
        baseUrl = "http://localhost:9999";
        timeout = 15000;
        open("/");

        CardsListPage cards = new LoginPage()
                .login("vasya", "qwerty123")
                .verify("12345");

        long firstBefore = cards.getBalanceByCardNumber(FIRST_CARD);
        long secondBefore = cards.getBalanceByCardNumber(SECOND_CARD);

        System.out.println("=== БАЛАНСЫ ДО ПЕРЕВОДА ===");
        System.out.println("Первая карта: " + firstBefore);
        System.out.println("Вторая карта: " + secondBefore);

        TransferPage transfer = cards.selectCardForTopUp(SECOND_CARD);
        CardsListPage after = transfer.transfer(FIRST_CARD, String.valueOf(TRANSFER_AMOUNT));


        long firstAfter = 0, secondAfter = 0;
        long deadline = System.currentTimeMillis() + 10000;
        do {
            firstAfter = after.getBalanceByCardNumber(FIRST_CARD);
            secondAfter = after.getBalanceByCardNumber(SECOND_CARD);
            if (firstAfter == firstBefore - TRANSFER_AMOUNT
                    && secondAfter == secondBefore + TRANSFER_AMOUNT) {
                break;
            }
            Selenide.sleep(500);
        } while (System.currentTimeMillis() < deadline);

        System.out.println("=== БАЛАНСЫ ПОСЛЕ ПЕРЕВОДА ===");
        System.out.println("Первая карта: " + firstAfter + " (ожидалось: " + (firstBefore - TRANSFER_AMOUNT) + ")");
        System.out.println("Вторая карта: " + secondAfter + " (ожидалось: " + (secondBefore + TRANSFER_AMOUNT) + ")");

        assertEquals(firstBefore - TRANSFER_AMOUNT, firstAfter, "Баланс первой карты должен уменьшиться");
        assertEquals(secondBefore + TRANSFER_AMOUNT, secondAfter, "Баланс второй карты должен увеличиться");
    }
}