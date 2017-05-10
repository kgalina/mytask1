package com.mytask.mytask1;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.mytask.mytask1.Constants.URL;

public class OpenBZModule {
    private static WebDriver driver;
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
        WebElement acc_and_law_box = driver.findElement(By.xpath("//div[@id='accountant_and_law']//div[@class='box']"));
        acc_and_law_box.click();
        Thread.sleep(3000);
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