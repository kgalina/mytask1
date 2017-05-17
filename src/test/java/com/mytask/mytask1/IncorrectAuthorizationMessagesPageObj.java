package com.mytask.mytask1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.mytask.mytask1.Constants.URL;
import static org.junit.Assert.assertEquals;

public class IncorrectAuthorizationMessagesPageObj {
    private static WebDriver driver;
    private static AuthPage authPage;
    @BeforeClass
    public static void setUp() throws InterruptedException {
    /* Шаги: 1. Зайти на страницу авторизации через кнопку "Увійти" главной стр. платформы*/
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL.DEV);

        authPage = new AuthPage(driver);

        WebElement sign_in_btn = driver.findElement(By.xpath("//a[contains(text(),'Увійти')]"));
        sign_in_btn.click();
        Thread.sleep(2000);
        authPage.clickElement(authPage.login_btn);
    }
    @Test
    /* 2. Не заполняя поля email и password нажать "Увійти"
    ОР: Сообщения "Поле ... має бути заповнене" */
    public void testAuthEmptyFields() throws InterruptedException {
        authPage.email_input.clear();
        authPage.passwd_input.clear();
        assertEquals("Сообщение, если не заполнено e-mail (укр.)", "Поле e-mail має бути заповнене", authPage.empty_email_message.getText());
        assertEquals("Сообщение, если не заполнен пароль", "Поле Пароль має бути заповнене", authPage.empty_passwd_message.getText());
    }
    @Test
    /* Шаги: 2. Ввести логин, существующего пользователя
    3. Ввести неверный пароль
    ОР: Сообщение "Невірний логін або пароль"    */
    public void testIncorrectPasswd() throws InterruptedException {

        authPage.email_input.clear();
        authPage.email_input.sendKeys("487873737_some@mailinator.com");
        authPage.passwd_input.clear();

        Steps user_inputs_passwd = new Steps();
        authPage.passwd_input.sendKeys(user_inputs_passwd.inputRandString());
        authPage.clickElement(authPage.login_btn);

        assertEquals("Сообщение, если неверный пароль (укр.)", "Невірний логін або пароль", authPage.incorrect_passwd.getText());
    }
    @Test
    /* Шаги: 2. Ввести email без @
    ОР: Сообщение "Введіть правильний e-mail" */
    public void testIncorrectEmail() throws InterruptedException {

        authPage.email_input.clear();
        Steps user_inputs_email = new Steps();
        authPage.email_input.sendKeys(user_inputs_email.inputRandString());
        authPage.clickElement(authPage.login_btn);

        assertEquals("Сообщение, если email без @ (укр.)", "Введіть правильний e-mail", authPage.incorrect_email.getText());
    }
    @AfterClass
    public static void tearDown() {

        driver.quit();
    }
}

