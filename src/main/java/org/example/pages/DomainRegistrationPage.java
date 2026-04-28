package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DomainRegistrationPage extends Page {

    private final By domainInputLocator = By.xpath("//div[contains(@class,'domain-whois-check__input')]//input[@class='input__field js-input']");
    private final By errorMessageLocator = By.xpath("//div[contains(@class,'input__help_error')]");

    public DomainRegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // оставим для возможных других элементов
    }

    public void enterDomain(String domain) {
        WebElement domainInput = wait.until(ExpectedConditions.visibilityOfElementLocated(domainInputLocator));
        domainInput.clear();
        domainInput.sendKeys(domain);
    }

    public boolean waitForErrorMessage(String expectedText) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(errorMessageLocator, expectedText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorElement.getText();
    }
}