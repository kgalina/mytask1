package com.mytask.mytask1;

import org.junit.AfterClass;
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
        Thread.sleep(1000);
        assertEquals("Сообщение, если не заполнено e-mail (укр.)", "Поле e-mail має бути заповнене", authPage.empty_email_message.getText());
        assertEquals("Сообщение, если не заполнен пароль", "Поле Пароль має бути заповнене", authPage.empty_passwd_message.getText());
    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }
}

