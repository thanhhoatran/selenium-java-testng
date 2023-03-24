package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Phím tắt: command+shift+O => tự động import/remove thư viện

public class Topic_10_Custom_Dropdown {
	WebDriver driver;
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
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		driver.findElement(By.cssSelector("span#speed-button")).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		
		List<WebElement>  speedDropdownItems =  driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		for (WebElement tempItem : speedDropdownItems) { // dùng biến tạm tempItem duyệt qua 5trong list speedDropdownItems
			String itemText = tempItem.getText();
			
			if (itemText.equals("Faster")) { // vòng lặp duyệt qua và kiểm tra xem có đúng giá trị mong muốn hay không 
				tempItem.click();
				break;  // thoát ra khỏi vòng lặp và không xét nữakhi đã tìm thấy item cần lấy  => nên dùng nhất là trong trường hợp có nhiều data tốn thời gian chạy vònglặpmặc dù đã tìm thấy  
			}
		}
	}

	@Test
	public void TC_02_Function() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']",  "Slow");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow"); // kiểm tra giá trij đã đươch selected 
		
		
		selectItemInDropdown("span#salutation-button","ul#salutation-menu div[role='option']",  "Other");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Other"); 

	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("i.dropdown.icon","span.text",  "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano"); 

		selectItemInDropdown("i.dropdown.icon","span.text",  "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt"); 

	}
	
	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a",  "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option"); 

	}
	
	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterAndSelectItemInDropdown("input.search","span.text", "Angola");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola"); 
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		List<WebElement>  speedDropdownItems =  driver.findElements(By.cssSelector(allItemCss));
		
		for (WebElement tempItem : speedDropdownItems) { // dùng biến tạm tempItem duyệt qua 5trong list speedDropdownItems			
			if (tempItem.getText().trim().equals(expectedItem)) { // vòng lặp duyệt qua và kiểm tra xem có đúng giá trị mong muốn hay không 
				tempItem.click();
				break;  // thoát ra khỏi vòng lặp và không xét nữakhi đã tìm thấy item cần lấy  => nên dùng nhất là trong trường hợp có nhiều data tốn thời gian chạy vònglặpmặc dù đã tìm thấy  
			}
		}
	}
	
	public void enterAndSelectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
			
		List<WebElement>  speedDropdownItems =  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		for (WebElement tempItem : speedDropdownItems) { // dùng biến tạm tempItem duyệt qua 5trong list speedDropdownItems			
			if (tempItem.getText().trim().equals(expectedTextItem)) { // vòng lặp duyệt qua và kiểm tra xem có đúng giá trị mong muốn hay không 
				sleepInSecond(1);
				tempItem.click();
				break;  // thoát ra khỏi vòng lặp và không xét nữakhi đã tìm thấy item cần lấy  => nên dùng nhất là trong trường hợp có nhiều data tốn thời gian chạy vònglặpmặc dù đã tìm thấy  
			}
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}