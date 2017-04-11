package com.mytask.mytask1;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class AppTest {
    //Open уровень

	@Test
	 public void test_help_page_title() throws InterruptedException {
//		 System.setProperty("webdriver.gecko.driver", "D:/Projects/geckodriver-v0.15.0-win32/geckodriver.exe");
//      WebDriver driver = new FirefoxDriver();

//      Проверка тайтла страницы "Помощь"
      System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
      WebDriver driver = new ChromeDriver();

      driver.manage().window().maximize();
      driver.get("https://webep-dev.ligazakon.net");

      WebElement help_icon = driver.findElement(By.xpath("//div[@class='lz-ico']"));
      help_icon.click();

      String title = driver.getTitle();
      Assert.assertEquals("Title Про продукт", "Про продукт", title);
      driver.close();
}
      @Test
      public void test_level_up() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
      WebDriver driver = new ChromeDriver();

      driver.manage().window().maximize();
      driver.get("https://webep-dev.ligazakon.net");

      WebElement help_icon = driver.findElement(By.xpath("//div[@class='lz-ico']"));
      help_icon.click();

//      Проверка URL главной ips при переходе на уровень выше из помощи "i"
      WebElement level_up = driver.findElement(By.xpath("//a[contains(@class, 'lz-back')]"));
      level_up.click();

      String url = driver.getCurrentUrl();
      Assert.assertEquals("URL главной ips", "https://ips-dev.ligazakon.net/?role=all", url);

//      Проверка наличия текста "Пошук"
      WebElement search_title_element = driver.findElement(By.xpath("//div[text()='Пошук']"));
      String search_text = search_title_element.getText();
      Assert.assertEquals("Текст Пошук", "Пошук", search_text);

      driver.close();
      }

    @Test
    /* Шаги: 1. Зайти на страницу логина.
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
}