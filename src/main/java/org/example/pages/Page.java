package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void open(String url){
        driver.get(url);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public boolean isDisplayed(WebElement element){
        try{
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
}
