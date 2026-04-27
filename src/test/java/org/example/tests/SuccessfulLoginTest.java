package org.example.tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuccessfulLoginTest extends BaseTest {

    private static String login;
    private static String password;

    @BeforeAll
    static void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        login = dotenv.get("TIMEWEB_LOGIN");
        password = dotenv.get("TIMEWEB_PASSWORD");
        if (login == null || password == null) {
            throw new RuntimeException("отсутствуют креды в .env ");
        }
    }

    @Test
    public void successfulLoginTest() {
        driver.get("https://hosting.timeweb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://hosting.timeweb.ru/"));

        boolean isLoginFormHidden = driver.findElements(By.xpath("//input[@name='username']")).isEmpty();
        assertTrue(isLoginFormHidden);
    }
}