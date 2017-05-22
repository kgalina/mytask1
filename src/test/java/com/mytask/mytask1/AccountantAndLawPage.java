package com.mytask.mytask1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountantAndLawPage {
    private WebDriver driver;

    public AccountantAndLawPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="//h1[text()='БУХГАЛТЕР И ЗАКОН']")
    public WebElement acc_and_law_header;

    public void clickAndWaitNextElement(WebElement element, WebElement next_elelment){
        Wait<WebDriver> wait;

        element.click();
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(next_elelment));

    }

    }

