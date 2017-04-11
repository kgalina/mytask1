package com.mytask.mytask1;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Login {
    @Test
    /* Шаги: 1. Зайти на страницу логина через главную стр. платформы
    2. Не заполняя поля email и password нажать "Увійти"
    ОР: Сообщения "Поле ... має бути заповнене"
    */
    public void test_login_empty_fields() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://webep-dev.ligazakon.net");

        WebElement sign_in_btn = driver.findElement(By.xpath("//a[contains(text(),'Увійти')]"));
        sign_in_btn.click();

        Thread.sleep(2000);

        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.submit();

        Thread.sleep(1000);
        WebElement empty_email_message = driver.findElement(By.xpath("//span[contains(@ng-show, 'sign_in.email.$error.required')]"));
        Assert.assertEquals("Пустое поле e-mail", "Поле e-mail має бути заповнене", empty_email_message.getText());

        WebElement empty_password_message = driver.findElement(By.xpath("//span[contains(@ng-show, 'sign_in.password.$error.required')]"));
        Assert.assertEquals("Пустое поле Пароль", "Поле Пароль має бути заповнене", empty_password_message.getText());

        driver.close();
    }

    @Test
    /* Шаги: 1. Перейти по URL на страницу логина.
    2. Ввести логин и пароль.
    3. Нажать "Увійти".
    ОР: Сообщения "Поле ... має бути заповнене"
    */
    public void test_login_successful() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://webep-dev.ligazakon.net/signinup/auth");

        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[3]"));
        email.click();
        email.clear();
        email.sendKeys("Galyna.Kuleshova@ligazakon.ua");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.clear();
        password.sendKeys("76f78255");

        Thread.sleep(3000);
        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.click();
    }
}