package com.mytask.mytask1.OpenModules;

import com.mytask.mytask1.Pages.AlgorithmsModulePage;
import com.mytask.mytask1.Pages.AuthPage;
import com.mytask.mytask1.Pages.MainIpsPage;
import com.mytask.mytask1.Pages.MainPltPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.mytask.mytask1.Constants.Constants.URL;
import static org.junit.Assert.assertEquals;

public class OpenAlgorithmsModule {
    private static WebDriver driver;
    private static AuthPage authPage;
    private static MainIpsPage mainIpsPage;
    private static MainPltPage mainPltPage;
    private static Wait<WebDriver> wait;
    private static AlgorithmsModulePage algorithmsModulePage;
    @BeforeClass
    public static void setUp() throws InterruptedException {
        /*Шаги: 1. Залогиниться
         2. Кликнуть по прямоугольнику продукта "Гранд"
         3. Переключиться к новой вкладке
         4. Кликнуть по квадратику модуля "Алгоритмы действий"*/
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL.AUTH_DEV);
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        authPage = new AuthPage(driver);
        mainPltPage = new MainPltPage(driver);
        mainIpsPage = new MainIpsPage(driver);
        algorithmsModulePage = new AlgorithmsModulePage(driver);

        authPage.email_input.clear();
        authPage.email_input.sendKeys("Galyna.Kuleshova@ligazakon.ua");

        authPage.passwd_input.clear();
        authPage.passwd_input.sendKeys("*");

        authPage.clickAndWaitNextElement(authPage.login_btn, mainPltPage.grand_product);
        mainPltPage.grand_product.click();
//Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
// Perform the actions on new window
        mainIpsPage.algorithms_box.click();
    }
    @Test
    public void testAlgorithmsModuleName() throws InterruptedException {
        /* Проверка названия открытого модуля "Алгоритмы действий для бизнеса"*/
        wait.until(ExpectedConditions.visibilityOf(algorithmsModulePage.algorithms_header));
        assertEquals("Название модуля'Алгоритмы действий для бизнеса' не соответсвует ожидаемому", "Алгоритмы действий для бизнеса" , algorithmsModulePage.algorithms_header.getText());
    }
    @Test
    public void testAlgorithmsModuleTitle() throws InterruptedException {
        /* Проверка тайтла модуля "Алгоритмы действий для бизнеса"*/
        wait.until(ExpectedConditions.visibilityOf(algorithmsModulePage.algorithms_header));
        String algorithms_module_title = driver.getTitle();
        assertEquals("Тайтл модуля 'Алгоритмы действий для бизнеса' не соответствует ожидаемому",algorithms_module_title, "Алгоритмы действий для бизнеса");
    }
    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

}