package com.mytask.mytask1;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainIPS {
    private static WebDriver driver;
    /*Проверка наличия надписи лиценции на главной стр. ips
    Шаги: 1. Залогиниться с лицензией Гранд
    2. Перейти на главную ips
    3. Проверить наличие текста Гранд...
    */
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
    public void testLicenseTextIsShow() throws InterruptedException {
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
//     Проверка наличия текста с названием лицензии
       Thread.sleep(3000);
       WebElement licenseText_element = driver.findElement(By.xpath("//span[@class='conf-name ng-binding ng-scope']"));
       String licenseText = licenseText_element.getText();
       Assert.assertEquals("Наличие текста названия лицензии", "ГРАНД", licenseText);

       WebElement licenseUntilText_element = driver.findElement(By.xpath("//div[contains(@class, 'conf-name')]"));
       String licenseUntilText = licenseUntilText_element.getText();
       Assert.assertEquals("Наличие текста до какой даты лицензия","ЛИЦЕНЗИЯ ДО 31.12.2017", licenseUntilText);

//Close the new window, if that window no more required
       driver.close();
//Switch back to original browser (first window)
       driver.switchTo().window(winHandleBefore);
//continue with original browser (first window)
       driver.close();
    }
}