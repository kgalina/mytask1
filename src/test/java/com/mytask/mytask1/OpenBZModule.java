package com.mytask.mytask1;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class OpenBZModule {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {

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
    }
    @Test
    public void testBZModuleName() throws InterruptedException {
        /* Проверка названия открытого модуля "БУХГАЛТЕР И ЗАКОН"
         */
//Store the current window handle
       String winHandleBefore2 = driver.getWindowHandle();
//Perform the click operation that opens new window
       Thread.sleep(6000);
       WebElement ips_box_ico = driver.findElement(By.xpath("//div[@class='ioco ioco-ips']"));
       ips_box_ico.click();
//Switch to new window opened
       for (String winHandle2 : driver.getWindowHandles()) {
           driver.switchTo().window(winHandle2);
       }
// Perform the actions on new window
       WebElement acc_and_law_box = driver.findElement(By.xpath("//div[@id='accountant_and_law']//div[@class='box']"));
       acc_and_law_box.click();
       Assert.assertTrue("Название модуля'Бухгалтер и закон' не соответсвует ожидаемому", !driver.findElements(By.xpath("//h1[text()='БУХГАЛТЕР И ЗАКОН']")).isEmpty());

//Close the new window, if that window no more required
        driver.close();
//Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore2);
    }

    @Test
    public void testBZModuleTitle() throws InterruptedException {
        /* Проверка тайтла модуля "БУХГАЛТЕР И ЗАКОН"
         */
//Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
//Perform the click operation that opens new window
        Thread.sleep(6000);
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
        String bz_module_title = driver.getTitle();
        Assert.assertEquals("Тайтл модуля 'Бухгалтер и Закон' не соответствует ожидаемому",bz_module_title, "БУХГАЛТЕР И ЗАКОН");
//Close the new window, if that window no more required
        driver.close();
//Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}