package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_06_Web_Browser {
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
		driver.close(); // đóng tab hiện tại
		driver.getPageSource(); //đóng Browser 
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		driver.getPageSource(); // trả về Source Code HTML của page hiện  
		String loginWindowID = driver.getWindowHandle(); // Lấy ra được ID của Window/ Tab mà driver đang đứng (active)
		Set<String> allIDs = driver.getWindowHandles(); //lấy ra ID của Window/ Tab của tất cả các tab đang mở
		
		//Cookie/ Cache
		Options opt = driver.manage();
		// login thành công -> lưu  
		opt.getCookies();
		// Test case khác cần setoolkie vào lại để ko cần login 
		opt.logs();
		Timeouts time = opt.timeouts();
		time.implicitlyWait(10, TimeUnit.SECONDS); // khoảng thời gian chờ element xuất hiện
		
		time.pageLoadTimeout(5, TimeUnit.SECONDS); //khoảng thời gian chờ page load xong trong vòng x giây 
		time.setScriptTimeout(5,TimeUnit.SECONDS); // khoảng thời gian chờ script được thực thi xong trong vòng x giây 
		
		Window win = opt.window();
		win.maximize(); // mở full màn hình 
		
		// Test GUI: Font, Size, Color, Position, Location 
		win.getSize(); //get kích thước bên trong trình duyệt 
		win.getPosition(); //get vị trí, toạ độ của rình duyệt so với độ phân 
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		nav.to(""); //giống driver.get() nhưng support về history tốt hơn 
		
		TargetLocator tar = driver.switchTo();
		tar.alert(); //webdriver api - alert/ authentication alert (alert library)
		tar.frame(""); //webdriver api - frame/ iframe (frame library)
		tar.window(""); // webdriver api -  windows/ tabs 
		
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