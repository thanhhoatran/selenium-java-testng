package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_12_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		// 1. Có thể switch qua và tương tác luôn 
		alert = driver.switchTo().alert();
		
		// 2.Cần wait trước khi nào nó xuất hiện thì mới switch qua và tương =. nên dùng vì có độ ổn định và ít fail hơn vì có cơ chế 
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Accept Alert
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// 1. Có thể switch qua và tương tác luôn 
		alert = driver.switchTo().alert();
		
		// 2.Cần wait trước khi nào nó xuất hiện thì mới switch qua và tương =. nên dùng vì có độ ổn định và ít fail hơn vì có cơ chế 
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Cancel Alert
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// 1. Có thể switch qua và tương tác luôn 
		alert = driver.switchTo().alert();
		
		// 2.Cần wait trước khi nào nó xuất hiện thì mới switch qua và tương =. nên dùng vì có độ ổn định và ít fail hơn vì có cơ chế 
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		
		String inputText = "Test";
		// Nhap text 
		alert.sendKeys(inputText);
		
		// Accept Alert
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + inputText);
	}
	
	@Test
	public void TC_04_Authentication_Alert_1() {
		// truyền trực tiếp usename và password vào Url để tự động Sign in vào 
		// http:// + Username : Password @ the-internet.herokuapp.com/basic_auth
//		driver.get("http://the-internet.herokuapp.com/basic_auth");
		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		
		// cách này ko bật alert mà login vào  luôn 
			
	}
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
	}
	
	@Test
	public void TC_04_Authentication_Alert_2() {
		driver.get("http://the-internet.herokuapp.com/basic_auth");		
		

	//Khi hết cách thì mới dùng AutoIT vìchỉ chạy trên Window chứ ko chạy đc trên Mac/ Linux/ CI 
	}
	
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}