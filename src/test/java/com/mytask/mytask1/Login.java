package com.mytask.mytask1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://webep-dev.ligazakon.net");
    }

    @Test
    /* Шаги: 1. Зайти на страницу логина через главную стр. платформы
    2. Не заполняя поля email и password нажать "Увійти"
    ОР: Сообщения "Поле ... має бути заповнене"
    */
    public void test_login_empty_fields() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

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
    }

    @Test
    /* Шаги: 1. Перейти по URL на страницу логина.
    2. Ввести логин и пароль.
    3. Нажать "Увійти".
    4. Нажать кнопку "Мой профиль"
    ОР: Отображатеся email, имя пользователя
    */
    public void test_login_successful() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        driver.get("https://webep-dev.ligazakon.net/signinup/auth");

        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[3]"));
        email.click();
        email.clear();
        email.sendKeys("Galyna.Kuleshova@ligazakon.ua");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.clear();
        password.sendKeys("76f78255");

        WebElement login_btn = driver.findElement(By.xpath("//button[contains(text(),'Увійти')]"));
        login_btn.click();

        Thread.sleep(6000);

        WebElement profile_btn = driver.findElement(By.id("profile-btn"));
        profile_btn.click();

        WebElement profile_email = driver.findElement(By.xpath("//h5[contains(@class, 'lz-user-email')][1]"));
        Assert.assertEquals("email в профиле", "galyna.kuleshova@ligazakon.ua", profile_email.getText() );

        WebElement user_name = driver.findElement(By.xpath("//*[@id='profMenu']//*[contains(@class, 'lz-user-name')]"));
        Assert.assertEquals("Имя, Фамилия в профиле", "Gala Kuleshova" , user_name.getText());

        WebElement logout_link = driver.findElement(By.id("profileExit"));
        logout_link.click();
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}