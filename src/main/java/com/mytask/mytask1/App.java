package com.mytask.mytask1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App 
{
    public static void main( String[] args )
    {
    	// Create a new instance of the Firefox driver
    			// Notice that the remainder of the code relies on the interface,
    			// not the implementation.
    			
    			//System.setProperty("webdriver.gecko.driver", "D:/Projects/geckodriver-v0.15.0-win32/geckodriver.exe");
//    			WebDriver driver = new FirefoxDriver();
    			//WebDriver driver = new InternetExplorerDriver();
    			
    			//System.setProperty("webdriver.gecko.driver", "C:/Users/ievgenii.mitiguz/Downloads/geckodriver-v0.15.0-win64/geckodriver.exe");
    			
    			
    			System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
    			WebDriver driver = new ChromeDriver();
    			
    			// And now use this to visit Google
    			driver.get("https://www.google.com");
    			
    			WebElement element = driver.findElement(By.name("q"));

    			// Enter something to search for
    			element.sendKeys("Cheese!");

    			// Now submit the form. WebDriver will find the form for us from the
    			// element
    			element.submit();

    			// Check the title of the page
    			System.out.println("Page title is: " + driver.getTitle());

    			// Google's search is rendered dynamically with JavaScript.
    			// Wait for the page to load, timeout after 10 seconds
    			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
    				public Boolean apply(WebDriver d) {
    					return d.getTitle().toLowerCase().startsWith("cheese!");
    				}
    			});

    			// Should see: "cheese! - Google Search"
    			System.out.println("Page title is: " + driver.getTitle());

    			// Close the browser
    			driver.quit();
    		}
    }

