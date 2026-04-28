package org.example.tests;

import org.example.pages.DomainRegistrationPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DomainRegistrationTest extends BaseTest {

    @Test
    public void testOccupiedDomain() {
        performLogin();
        driver.get("https://hosting.timeweb.ru/domains/registration");
        DomainRegistrationPage domainPage = new DomainRegistrationPage(driver);
        domainPage.enterDomain("gosuslugi.ru");
        boolean errorAppeared = domainPage.waitForErrorMessage(
                "Домен уже зарегистрирован. Выберите другой домен.");
        assertTrue(errorAppeared, "Должно появиться сообщение о том, что домен занят");
    }
}