package com.mytask.mytask1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    private WebDriver driver;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="(//input[@id='email'])[3]")
    public WebElement email_element ;

    @FindBy(xpath = "//button[contains(text(),'Увійти')]")
    public WebElement login_btn;

    public void clickElement(WebElement element){

        element.click();
    }
}

