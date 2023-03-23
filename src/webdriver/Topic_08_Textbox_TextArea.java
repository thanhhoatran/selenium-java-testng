package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	//Actions action;
	String projectPath = System.getProperty("user.dir"); //đường dẫn đến thư mục Project
	String osName = System.getProperty("os.name");
	String employeeID=String.valueOf(rand.nextInt(99999)); //convert INT qua STRING
	String passportNumber = "40517-402-19-7203";
	String commentInput = "Lorem\nIpsum";

	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		//action = new Actions(driver); //giả lập click chuột 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		
		WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input"));
//		driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input")).clear();
//		action.doubleClick();
//		employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL,"a")); //Ctr+A cho window
		employeeIDTextbox.sendKeys(Keys.chord(Keys.COMMAND,"a")); //Cmd+A cho Mac
		employeeIDTextbox.sendKeys(Keys.DELETE); //Bam nut DELETE
		employeeIDTextbox.sendKeys(employeeID);
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']//following-sibling::div//span")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc"+employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		sleepInSecond(18);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(10);

		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentInput);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(8);

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div/div")).getText(), passportNumber);
		driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
	
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);

		driver.findElement(By.name("username")).sendKeys("afc"+employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");
		driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
		sleepInSecond(3);
		
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