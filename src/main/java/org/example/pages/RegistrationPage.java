package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-selenium='header-btn-registration']")
    private WebElement openRegistrationButton;

    private final By burgerButton = By.xpath("//div[@data-selenium='layout-header-mobile-button']");
    private final By burgerRegistrationButton = By.xpath("//div[@class='menu-content']//button[contains(text(),'Регистрация')]");

    @FindBy(xpath = "//input[@data-selenium='modal-registration-person_full_name']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@data-selenium='modal-registration-person_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@data-selenium='modal-registration-person_phone']")
    private WebElement phoneField;

    @FindBy(xpath = "//button[@data-selenium='modal-registration-submit']")
    private WebElement submitButton;

    public void openRegistrationForm() {
        try {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[@data-selenium='header-btn-registration']"))).isDisplayed()) {
                openRegistrationButton.click();
            }
        } catch (TimeoutException e) {
            driver.findElement(burgerButton).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'menu-content')]")));
            WebElement regButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'menu-content')]//button[@data-selenium='header-btn-registration']")));
            regButton.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-selenium='modal-registration-person_full_name']")));
    }

    public void enterFullName(String name) {
        fullNameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPhone(String phone) {
        phoneField.sendKeys(phone);
    }

    public void submit() {
        submitButton.click();
    }
}