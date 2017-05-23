package com.mytask.mytask1.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlgorithmsModulePage {
    private WebDriver driver;

    public AlgorithmsModulePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath="//h1[text()='Алгоритмы действий для бизнеса']")
    public WebElement algorithms_header;

    public void clickAndWaitNextElement(WebElement element, WebElement next_elelment){
        Wait<WebDriver> wait;

        element.click();
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(next_elelment));

    }

    }

