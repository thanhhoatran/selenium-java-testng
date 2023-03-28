package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_11_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		
		jsExecutor = (JavascriptExecutor) driver; // luôn luôn tạo sau biến driver của Browser 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0905123321");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("Abcd1234");
		sleepInSecond(2);
		
		// lay color cua element trong CSS
		driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		
		Color loginButtonBackgroundColor = Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background"));
		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127"); //nên chuyển qua asHex vì tuỳ trình duyệt có thể rgb bị fail
		//Assert.assertEquals(loginButtonBackgroundColor.asRgb().toUpperCase(), "rgb(201, 33, 39)");  
	}

	@Test
	public void TC_02_Default_Checkbox_Radio_Single() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
		sleepInSecond(2);
		
		//kiem tra neu chua click thi click
		if(!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected()) 
		{
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).click();
		}
		Assert.assertEquals(true, driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected());
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).click();
		Assert.assertEquals(false, driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected());
		checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
	}

	@Test
	public void TC_03_Default_Checkbox_Radio_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		for(WebElement checkbox : allCheckboxes) {
			checkbox.click();
			sleepInSecond(2);
		}
		
		//veriy tat ca checkbox duoc chon
		for(WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
			sleepInSecond(1);
		}
		for(WebElement checkbox : allCheckboxes) {
			// neu gap 1 checkbox co ten la X thi moi click
			if(checkbox.getAttribute("value").equals("Arthritis")){
				checkbox.click();
				sleepInSecond(2);
			}
		}
		
	}
	@Test
	public void TC_04_Custom_Checkbox_Radio() {
		//CASE1: Thẻ input bị che nên ko thao tác được nhưng dùng để verify được vì hàm isSelected() chỉ work với thẻ input
	
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());

	}
	@Test
	public void TC_05_Custom_Checkbox_Radio() {
		//CASE4: Dùng JavascriptExcutor để thao tác vào thẻ input đang bị ẩn
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
//		jsExecutor.executeScript("arguments[0].click(); arguments[1].sendKeys();", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")), "Doi so 2");

		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	
	}
	@Test
	public void TC_06_Custom_Checkbox_Radio() {
		//check thay doi sau khi select => dung isDisplayed kiem tra attribute da da thay doi do
		
		
	}
	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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