package org.example.tests;

import org.example.pages.RegistrationPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidRegistrationTest extends BaseTest {

    @Test
    public void invalidRegistrationTest() {
        driver.get("https://timeweb.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.openRegistrationForm();

        regPage.enterFullName("A");
        regPage.enterEmail("not-an-email");
        regPage.enterPhone("123");
        regPage.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@data-selenium='modal-registration-person_email-error']")
        ));
        assertTrue(emailError.isDisplayed(), "Сообщение об ошибке email должно отображаться");
        assertTrue(driver.getCurrentUrl().contains("timeweb.com"));
    }
}