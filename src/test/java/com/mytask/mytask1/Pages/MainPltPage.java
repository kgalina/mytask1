package com.mytask.mytask1.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPltPage {
    private WebDriver driver;

    public MainPltPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="//a[contains(text(),'Увійти')]")//Кнопка в шапке платформы
    public WebElement sign_in_btn;

    @FindBy(xpath = "//*[contains(@class, 'grand')]")
    public WebElement grand_product;

    public void clickAndWaitNextElement(WebElement element, WebElement next_elelment){
        Wait<WebDriver> wait;

        element.click();
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(next_elelment));

    }

}

