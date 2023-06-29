package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_18_Window_Tab {
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
		driver.get("https://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");;
		// Window/Tab sẽ có 2 hàm lấy ra ID của Window/tab đó 
		
		String basicFormID = driver.getWindowHandle();
		System.out.println("Parent Page = "+ basicFormID);
		
		// List<WebElement> checkboxes = driver.findElements(By.xpath("")); // List cho phep TRUNG/NULL
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		switchToWindowByID(basicFormID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		String googleWindowID = driver.getWindowHandle();
		
		switchToWindowByID(googleWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");;
		// Window/Tab sẽ có 2 hàm lấy ra ID của Window/tab đó 
				
		// List<WebElement> checkboxes = driver.findElements(By.xpath("")); // List cho phep TRUNG/NULL
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		switchToWindowByTitle("Google");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	public void switchToWindowByID(String otherID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set ko cho phep TRUNG

		for(String id  : allWindowIDs) {
			if(!id.equals(otherID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	// Dùng được cho duy nhất 2 ID (Window/Tab) 
	public void switchToWindowByTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set ko cho phep TRUNG

		for(String id  : allWindowIDs) {
				// Duyet qua tung ID truoc
				driver.switchTo().window(id);
				// Lay ra Title cua page nay
				String actualPageTitle = driver.getTitle();
				
				if(actualPageTitle.equals(expectedPageTitle)) {
					sleepInSecond(2);
					break;
			}
		}
	}
	
	// Dùng được cho từ 2 ID trở lên (Window/Tab) 
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