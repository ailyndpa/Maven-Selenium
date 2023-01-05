package Pruebas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
	WebDriver driver;
		
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
	       
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--handles");
       driver = new ChromeDriver(options); 
       driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
       driver.manage().window().maximize();
	}
	
    @Test
    public void shouldAnswerWithTrue()
    {
       
       driver.findElement(By.id("autosuggest")).sendKeys("Ha");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
																			
		for (WebElement elem : list) {
			if (elem.getText().equalsIgnoreCase("Haiti")) {
				elem.click();
				break;
			}
		}
    }
    
    @AfterTest
    public void After() {
    	driver.close();
    }
}
