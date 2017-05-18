package com.mytask.mytask1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPltPage {
    private WebDriver driver;

    public MainPltPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="//a[contains(text(),'Увійти')]")
    public WebElement sign_in_btn;

}

