package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SupportPage extends Page {

    private final By messageInput = By.xpath("//div[contains(@class,'emoji-wysiwyg-editor')]");
    private final By sendButton = By.xpath("//button[@type='submit' and contains(text(),'Отправить сообщение')]");
    private final By errorMessageLocator = By.xpath("//div[contains(@class,'field-otherquestion-message')]//div[contains(@class,'help-block')]");
    private final By fileInput = By.xpath("//input[@id='otherquestion-attachments']");

    private final By ticketsPageHeader = By.xpath("//div[contains(@class,'cpS-table-h-ft') and contains(text(),'Список сообщений')]");

    public SupportPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://hosting.timeweb.ru/support/help/other-question");
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageInput));
    }

    public void enterMessage(String text) {
        WebElement editor = driver.findElement(messageInput);
        editor.click();
        editor.clear();
        editor.sendKeys(text);
    }

    public void attachFile(String absoluteFilePath) {
        WebElement input = driver.findElement(fileInput);
        input.sendKeys(absoluteFilePath);
    }

    public void clickSend() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public String getErrorMessage() {
        WebElement errorEl = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorEl.getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTicketCreated() {
        try {
            wait.until(ExpectedConditions.urlContains("/tickets"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(ticketsPageHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}