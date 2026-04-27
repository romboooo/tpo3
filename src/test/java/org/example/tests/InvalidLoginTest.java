package org.example.tests;

import org.example.pages.LoginPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidLoginTest extends BaseTest {

    // TS-01-05
    @Test
    public void invalidPasswordTest() {
        driver.get("https://hosting.timeweb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("test@example.com");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("Логин") || error.contains("Неизвестная"));
    }

    // TS-01-06:
    @Test
    public void emptyPasswordTest() {
        driver.get("https://hosting.timeweb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("test@example.com");
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("Необходимо заполнить «Пароль»"));
    }
}