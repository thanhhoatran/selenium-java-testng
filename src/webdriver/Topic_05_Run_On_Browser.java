package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_05_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir"); //đường dẫn đến thư mục Project
	String osName = System.getProperty("os.name");

	/*
	  @BeforeClass public void beforeClass() 
	  {
	   	if (osName.contains("Windows")) 
	   	{
	  		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe"); 
	  	} else {
	  		System.setProperty("webdriver.gecko.driver", projectPath +"/browserDrivers/geckodriver"); }
	  
	  driver = new FirefoxDriver(); driver.manage().timeouts().implicitlyWait(30,
	  TimeUnit.SECONDS); driver.manage().window().maximize();
	  driver.get("https://live.techpanda.org/index.php/customer/account/login/"); }
	 */

	@Test
	public void TC_01_Run_Chrome() {
		if (osName.contains("Windows")) 
	   	{
	  		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe"); 
	  	} else {
	  		System.setProperty("webdriver.chrome.driver", projectPath +"/browserDrivers/chromedriver"); 
	  	}
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.quit();
	}

	@Test
	public void TC_02_Run_Firefox() {
		if (osName.contains("Windows")) 
	   	{
	  		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe"); 
	  	} else {
	  		System.setProperty("webdriver.gecko.driver", projectPath +"/browserDrivers/geckodriver"); 
	  	}
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.quit();
	}

	@Test
	public void TC_03_Run_Edge() {
		if (osName.contains("Windows")) 
	   	{
	  		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe"); 
	  	} else {
	  		System.setProperty("webdriver.edge.driver", projectPath +"/browserDrivers/msedgedriver"); 
	  	}
		driver = new EdgeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}