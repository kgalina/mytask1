package com.mytask.mytask1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenAlgorithmsModule {
    private static WebDriver driver;
    @BeforeClass
    public static void setUp() throws InterruptedException {
        /*Шаги: 1. Залогиниться
         2. Кликнуть по квадратику модуля ips
         3. Переключиться к новой вкладке
         4. Кликнуть по квадратику модуля "Алгоритмы действий"*/
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webep-dev.ligazakon.net/signinup/auth");

        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[3]"));
        email.click();
        email.clear();
        email.sendKeys("Galyna.Kuleshova@ligazakon.ua");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.clear();
        password.sendKeys("1111");

        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.click();
        Thread.sleep(6000);
//Perform the click operation that opens new window
        WebElement ips_box_ico = driver.findElement(By.xpath("//div[@class='ioco ioco-ips']"));
        ips_box_ico.click();
//Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
// Perform the actions on new window
        WebElement algorithms_box = driver.findElement(By.xpath("//div[@id='business_schema']//div[@class='box']"));
        algorithms_box.click();
        Thread.sleep(3000);
    }
    @Test
    public void testAlgorithmsModuleName() throws InterruptedException {
        /* Проверка названия открытого модуля "Алгоритмы действий для бизнеса"*/
       Assert.assertTrue("Название модуля'Алгоритмы действий для бизнеса' не соответсвует ожидаемому", !driver.findElements(By.xpath("//h1[text()='Алгоритмы действий для бизнеса']")).isEmpty());
    }
    @Test
    public void testAlgorithmsModuleTitle() throws InterruptedException {
        /* Проверка тайтла модуля "Алгоритмы действий для бизнеса"*/
        String algorithms_module_title = driver.getTitle();
        Assert.assertEquals("Тайтл модуля 'Алгоритмы действий для бизнеса' не соответствует ожидаемому",algorithms_module_title, "Алгоритмы действий для бизнеса");
    }
    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

}