package com.mytask.mytask1.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage {
    private WebDriver driver;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="(//input[@id='email'])[3]")
    public WebElement email_input;

    @FindBy(id = "password")
    public WebElement passwd_input;

    @FindBy(xpath = "//button[contains(text(),'Увійти')]")
    public WebElement login_btn;

    @FindBy(xpath = "//span[contains(@ng-show, 'sign_in.email.$error.required')]")
    public WebElement empty_email_message;

    @FindBy(xpath = "//span[contains(@ng-show, 'sign_in.password.$error.required')]")
    public WebElement empty_passwd_message;

    @FindBy(xpath = "//div[contains(@ng-if, 'isShowErrorLoginAndPassword')]")
    public WebElement incorrect_passwd;

    @FindBy(xpath = "//span[contains(@ng-show, 'sign_in.email.$error.pattern')]")
    public WebElement incorrect_email;

    public void clickAndWaitNextElement(WebElement element, WebElement next_elelment){
        Wait<WebDriver> wait;

        element.click();
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(next_elelment));

    }

    }

