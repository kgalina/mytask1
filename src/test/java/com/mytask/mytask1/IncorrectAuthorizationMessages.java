package com.mytask.mytask1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IncorrectAuthorizationMessages {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws InterruptedException {
    /* Шаги: 1. Зайти на страницу авторизации через кнопку "Увійти" главной стр. платформы*/
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webep-dev.ligazakon.net");

        WebElement sign_in_btn = driver.findElement(By.xpath("//a[contains(text(),'Увійти')]"));
        sign_in_btn.click();
        Thread.sleep(2000);
    }

    @Test
    /* 2. Не заполняя поля email и password нажать "Увійти"
    ОР: Сообщения "Поле ... має бути заповнене" */
    public void test_auth_empty_fields() throws InterruptedException {
        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[3]"));
        email.click();
        email.clear();

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.clear();

        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.submit();

        Thread.sleep(1000);
        WebElement empty_email_message = driver.findElement(By.xpath("//span[contains(@ng-show, 'sign_in.email.$error.required')]"));
        Assert.assertEquals("Сообщение, если не заполнено e-mail (укр.)", "Поле e-mail має бути заповнене", empty_email_message.getText());

        WebElement empty_password_message = driver.findElement(By.xpath("//span[contains(@ng-show, 'sign_in.password.$error.required')]"));
        Assert.assertEquals("Сообщение, если не заполнен пароль", "Поле Пароль має бути заповнене", empty_password_message.getText());
    }

    @Test
    /* Шаги: 2. Ввести логин, существующего пользователя
    3. Ввести неверный пароль
    ОР: Сообщение "Невірний логін або пароль"    */
    public void test_incorrect_passwd() throws InterruptedException {

        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[3]"));
        email.click();
        email.clear();
        email.sendKeys("487873737_some@mailinator.com");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.clear();

        Steps user_inputs_passwd = new Steps();
        password.sendKeys(user_inputs_passwd.inputRandString());

        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.click();

        WebElement incorrect_passwd = driver.findElement(By.xpath("//div[contains(@ng-if, 'isShowErrorLoginAndPassword')]"));
        Assert.assertEquals("Сообщение, если неверный пароль (укр.)", "Невірний логін або пароль", incorrect_passwd.getText());
    }

    @Test
    /* Шаги: 2. Ввести email без @
    ОР: Сообщение "Введіть правильний e-mail" */
    public void test_incorrect_email() throws InterruptedException {

        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[3]"));
        email.click();
        email.clear();
        Steps user_inputs_email = new Steps();
        email.sendKeys(user_inputs_email.inputRandString());

        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.click();

        WebElement incorrect_email = driver.findElement(By.xpath("//span[contains(@ng-show, 'sign_in.email.$error.pattern')]"));
        Assert.assertEquals("Сообщение, если email без @ (укр.)", "Введіть правильний e-mail", incorrect_email.getText());
    }
    @AfterClass
    public static void tearDown() {

        driver.close();
    }
}

