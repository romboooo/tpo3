package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static String login;
    protected static String password;

    @BeforeAll
    static void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        login = dotenv.get("TIMEWEB_LOGIN");
        password = dotenv.get("TIMEWEB_PASSWORD");
        if (login == null || password == null) {
            throw new RuntimeException("Отсутствуют переменные TIMEWEB_LOGIN или TIMEWEB_PASSWORD в .env файле");
        }
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void performLogin() {
        driver.get("https://hosting.timeweb.ru/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://hosting.timeweb.ru/"));
    }
}