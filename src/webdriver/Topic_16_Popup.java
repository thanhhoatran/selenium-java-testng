package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_16_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir"); //đường dẫn đến thư mục Project
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications"); //vô hiệu hóa thông báo chrome
        options.addArguments("--disable-popup-blocking");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // ham nay anh huong den findElement/ finElements
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Fixed_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_")).click();
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals("Tài khoản không tồn tại!", driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText());

	}

	@Test
	public void TC_02_Fixed_In_DOM_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		Assert.assertEquals("", driver.findElement(By.cssSelector("div#password-form-login-message")).getText());
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());
		
	}

	@Test
	public void TC_03_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		
		// By: chua di tim Element, WebElement thi tim element nhung moi mo tr4ang thi chua thay co element
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		// Verify chua hien thi element
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// Click cho bat Login popup
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		driver.findElement(By.cssSelector("img.close-img")).click();
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}
	@Test
	public void TC_04_Fixed_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 1);

		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
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