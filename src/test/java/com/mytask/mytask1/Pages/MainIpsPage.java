package com.mytask.mytask1.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainIpsPage {
    private WebDriver driver;

    public MainIpsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="//div[@id='accountant_and_law']//div[@class='box']") //квадратик модуля "БЗ"
    public WebElement acc_and_law_box;

    @FindBy (xpath = "//div[@id='business_schema']//div[@class='box new-box']") //квадратик модуля "Алгоритмы действий"
    public WebElement algorithms_box;

    public void clickAndWaitNextElement(WebElement element, WebElement next_elelment){
        Wait<WebDriver> wait;

        element.click();
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(next_elelment));

    }

    }

