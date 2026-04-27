package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
    public WebElement loginButton;

    @FindBy(xpath = "//button[contains(text(), 'Регистрация')]")
    private WebElement registerButton;

}
