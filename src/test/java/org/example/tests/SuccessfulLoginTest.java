package org.example.tests;

import org.example.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuccessfulLoginTest extends BaseTest {

    @Test
    public void successfulLoginTest() {
        driver.get("https://hosting.timeweb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://hosting.timeweb.ru/"));
        boolean isLoginFormHidden = driver.findElements(By.xpath("//input[@name='username']")).isEmpty();
        assertTrue(isLoginFormHidden, "После успешного входа форма логина не должна отображаться");
    }
}