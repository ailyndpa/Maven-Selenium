package Pruebas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
	WebDriver driver;
		
	@BeforeTest
	public void setup(){
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option1 = new ChromeOptions();
		option1.addArguments("--handles");
		driver = new ChromeDriver(option1);
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
	    driver.manage().window().maximize();
	}
	
    @Test
    public void selectHaiti()
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
    @Test
	public void verifRadioButton(){
		WebElement oneWay = driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0"));
		WebElement	round_Trip = driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1"));
		round_Trip.click();
		if (oneWay.isSelected()) {
			WebElement Date = driver.findElement(By.xpath("//div/span[@class='date-close-disabled']"));
			Assert.assertTrue(Date.isEnabled(),"Los campos asociados a la fecha deben estar deshabilitados cuando se selecciona la opción One Way");
		} else if (round_Trip.isSelected()) {
				WebElement Date = driver.findElement(By.xpath("//div/span[@class='date-close']"));
				Assert.assertTrue(Date.isEnabled(),"Los campos asociados a la fecha deben estar habilitados cuando se selecciona la opción Round Trip");
		}
	}
    
	@Test
	public void fromTo(){
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='BLR']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR']//a[@text='Hubli (HBX)']")).click();
	}
    
    @AfterTest
    public void After() {
    	driver.close();
    }
}
