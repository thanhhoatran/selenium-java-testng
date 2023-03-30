package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_14_Action_P2 {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//Click vao so 1 roi giu chuot khong tha ra
		action.clickAndHold(listNumber.get(0))
		//di chuot toi so 
		.moveToElement(listNumber.get(7))
		//tha chuot trai ra
		.release()
		//execute
		.perform();
		sleepInSecond(2);
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 8);
	}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Chay duoc cho ca Mac/Win
		Keys key = null;
		if(osName.contains("Windows")) {
			key = Keys.CONTROL;
		}else {
			key = Keys.COMMAND;
		}
		
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// Nhan Ctrl/Command xuong
		action.keyDown(Keys.CONTROL).perform();
		
		// Click chon cac so random
		
		action.click(listNumber.get(0))
		.click(listNumber.get(5))
		.click(listNumber.get(7))
		.perform();
		
		// Nha phim Ctrl/Command ra\
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 3);
	}

	@Test
	public void TC_03_() {
		
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