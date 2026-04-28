package org.example.tests;

import org.example.pages.SupportPage;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupportTest extends BaseTest {

    // TS-06-03:
    @Test
    public void testEmptyMessageValidation() {
        performLogin();
        SupportPage supportPage = new SupportPage(driver);
        supportPage.open();
        supportPage.clickSend();
        String error = supportPage.getErrorMessage();
        assertTrue(error.contains("Введите текст сообщения перед отправкой"));
    }

    // TS-06-01:
    @Test
    public void testSuccessfulTicketCreation() {
        performLogin();
        SupportPage supportPage = new SupportPage(driver);
        supportPage.open();
        supportPage.enterMessage("тестовое обращение, проверка создания тикета");
        supportPage.clickSend();

        assertTrue(supportPage.isTicketCreated());
    }

    // TS-06-02:
    @Test
    public void testTicketWithAttachment() throws IOException {
        performLogin();
        SupportPage supportPage = new SupportPage(driver);
        supportPage.open();

        Path tempFile = Files.createTempFile("test_attachment", ".txt");
        Files.writeString(tempFile, "Тестовый файл вложения.");

        supportPage.enterMessage("Обращение с вложением");
        supportPage.attachFile(tempFile.toAbsolutePath().toString());
        supportPage.clickSend();

        assertTrue(supportPage.isTicketCreated());

        Files.deleteIfExists(tempFile);
    }

    // TS-06-04:
    @Test
    public void testLongDescription() {
        performLogin();
        SupportPage supportPage = new SupportPage(driver);
        supportPage.open();

        String base = "Тест ";
        StringBuilder longText = new StringBuilder();
        while (longText.length() < 5000) {
            longText.append(base);
        }

        supportPage.enterMessage(longText.toString());
        supportPage.clickSend();

        boolean ticketCreated = supportPage.isTicketCreated();
        boolean hasError = supportPage.isErrorMessageDisplayed();
        assertTrue(ticketCreated || !hasError);
    }
}