package com.mytask.mytask1;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigationOpenLevel {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webep-dev.ligazakon.net");
    }

    @Test
    public void test_help_page_title() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "D:/Projects/geckodriver-v0.15.0-win32/geckodriver.exe");
//      WebDriver driver = new FirefoxDriver();

//      Проверка тайтла страницы "Помощь"
        WebElement help_icon = driver.findElement(By.xpath("//div[@class='lz-ico']"));
        help_icon.click();

        String title = driver.getTitle();
        Assert.assertEquals("Title Про продукт", "Про продукт", title);
     }

    @Test
    public void test_level_up() throws InterruptedException {

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
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}