package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DomainRegistrationPage extends Page {

    private final By domainInputLocator = By.xpath("//div[contains(@class,'domain-whois-check__input')]//input[@class='input__field js-input']");
    private final By successIcon = By.xpath("//div[contains(@class,'domain-whois-check__input')]//svg[contains(@class,'icon-check') and contains(@style,'fill: #68b934')]");
    private final By registerButtonLocator = By.xpath("//button[contains(@class,'domain-whois-check__button')]");
    private final By errorMessageLocator = By.xpath("//div[contains(@class,'input__help_error')]");

    public DomainRegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public boolean isDomainAvailable() {
        try {
            WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(successIcon));
            wait.until(ExpectedConditions.visibilityOf(icon));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegisterButtonEnabled() {
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(registerButtonLocator),
                    ExpectedConditions.not(ExpectedConditions.attributeContains(registerButtonLocator, "class", "disabled"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRegisterAndWaitForOrder() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(registerButtonLocator));
        btn.click();
        wait.until(ExpectedConditions.urlContains("#order/"));
    }
}