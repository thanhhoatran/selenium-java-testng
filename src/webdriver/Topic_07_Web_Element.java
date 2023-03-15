package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_07_Web_Element {
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
	public void TC_01_() {
		driver.get("https://www.nopcommerce.com/en/login?returnUrl=%2Fen%2Fget-started");
		WebElement element = driver.findElement(By.id(""));
		element.isDisplayed();
		element.clear();
		element.sendKeys("");
		element.click();
		String searchAttribute = element.getAttribute("placeholder");
		element.getCssValue("background-color");
		Point point = element.getLocation(); //lấy vị trí element so với ntrang web (bên ngoài)
		point.x = 324;
		point.y= 324;
		
		Dimension di = element.getSize(); // lấy ra kích thước (bên trong) 
		di.getHeight();
		di.getWidth();
		
		Rectangle rect = element.getRect(); // Location +size
		
		// Chụp màn hình 
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		element.getTagName(); // lấy ra tên thẻ HTML 
		element.getText();
		element.isDisplayed();
		
		Assert.assertTrue(element.isDisplayed());
		Assert.assertTrue(element.isEnabled());
		Assert.assertTrue(element.isSelected());

		element.submit(); // phải nằm trong thẻ <form> để thay hành vi bấm ENTER ccuar nguwoif dùng 
		
		By emailTextboxBy = By.id("email"); // chưa đi tìm element ngay 
		driver.findElement(emailTextboxBy);



	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}