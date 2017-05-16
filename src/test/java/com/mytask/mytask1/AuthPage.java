package com.mytask.mytask1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage {

    @FindBy (xpath="(//input[@id='email'])[3]")
    public WebElement email_element ;

    @FindBy(xpath = "//button[contains(text(),'Увійти')]")
    public WebElement login_btn_element;

    public void userClicks(WebElement element){
        element.click();
    }
}

