package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir"); //đường dẫn đến thư mục Project
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_Class() {
		driver.get("https://www.demo.nopcommerce.com/search");
		
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("advs")).click();
	}
	@Test
	public void TC_04_TagName() {
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	@Test
	public void TC_05_LinkText() {
		//linkText: tuyệt đối
		driver.findElement(By.linkText("Addresses")).click();
		
		//partialLinkText: tương đối
		driver.findElement(By.partialLinkText("vendor ")).click();

	}
	@Test
	public void TC_06_Css() {
		driver.get("https://www.demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
	}
	@Test
	public void TC_07_Xpath() {
		driver.get("https://www.demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}