package com.mytask.mytask1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.mytask.mytask1.Constants.URL;

public class OpenBZModule {
    private static WebDriver driver;
    private static AuthPage authPage;
    private static MainPltPage mainPltPage;
    private static MainIpsPage mainIpsPage;
    private static AccountantAndLawPage accountantAndLawPage;
    @BeforeClass
    public static void setUp() throws InterruptedException {
        /*Шаги: 1. Залогиниться
         2. Кликнуть по квадратику модуля ips
         3. Переключиться к новой вкладке
         4. Кликнуть по квадратику модуля "БЗ"*/
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL.AUTH_DEV);
        Wait<WebDriver> wait = new WebDriverWait(driver, 5).withMessage("Элемент не найден");
        authPage = new AuthPage(driver);
        mainPltPage = new MainPltPage(driver);
        mainIpsPage = new MainIpsPage(driver);
        accountantAndLawPage = new AccountantAndLawPage(driver);

        authPage.email_input.clear();
        authPage.email_input.sendKeys("Galyna.Kuleshova@ligazakon.ua");

        authPage.passwd_input.clear();
        authPage.passwd_input.sendKeys("**");

        authPage.clickAndWaitNextElement(authPage.login_btn, mainPltPage.grand_product);
        mainPltPage.grand_product.click();
//Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
// Perform the actions on new window
        mainIpsPage.clickAndWaitNextElement(mainIpsPage.acc_and_law_box, accountantAndLawPage.acc_and_law_header);
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()='БУХГАЛТЕР И ЗАКОН']"))));
    }
    @Test
    public void testBZModuleName() throws InterruptedException {
        /* Проверка названия открытого модуля "БУХГАЛТЕР И ЗАКОН"*/
       Assert.assertTrue("Название модуля'Бухгалтер и закон' не соответсвует ожидаемому", !driver.findElements(By.xpath("//h1[text()='БУХГАЛТЕР И ЗАКОН']")).isEmpty());
    }
    @Test
    public void testBZModuleTitle() throws InterruptedException {
        /* Проверка тайтла модуля "БУХГАЛТЕР И ЗАКОН"*/
        String bz_module_title = driver.getTitle();
        Assert.assertEquals("Тайтл модуля 'Бухгалтер и Закон' не соответствует ожидаемому",bz_module_title, "БУХГАЛТЕР И ЗАКОН");
    }
    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

}