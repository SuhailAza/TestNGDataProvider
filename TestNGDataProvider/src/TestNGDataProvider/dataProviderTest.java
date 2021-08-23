package TestNGDataProvider;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.testUtil;

public class dataProviderTest {
	
	WebDriver driver;
	public String driverPath = "C:\\browserdrivers\\Chrome92\\chromedriver.exe";
	
	
	@BeforeMethod
	public void setupBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		
		//keep window maximize
		driver.manage().window().maximize();
		//set wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Open Facebook signup page
		driver.get("https://www.facebook.com/r.php");
		
	}
	
	//Data Provider
	@DataProvider(name="excelData")
	public Object[][] getTestData() throws IOException {
		
		//getDataFromExcel method to get and pass values. Parameters are <excelPath> and <sheetName> 
		Object[][] arrObj = testUtil.getDataFromExcel("C:\\eclipse-workspace\\TestNGDataProvider\\src\\testData\\testData.xlsx","data");
    	return arrObj;
		
	}
	
	//dataProvider name should be same as given in @DataProvider
	@Test(dataProvider = "excelData")
	public void facebookSignupTest(String firstName, String lastName, String email, String password, String BDay, String Month, String Year) throws InterruptedException {
		
		String result= "null";
		
		//Get page elements
		WebElement txt_FirstName = driver.findElement(By.xpath("//input[@name='firstname']"));
		WebElement txt_LastName = driver.findElement(By.xpath("//input[@name='lastname']"));
		WebElement txt_Email = driver.findElement(By.xpath("//input[@name='reg_email__']"));
		WebElement txt_Password = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
		Select dd_BDay = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
		Select dd_Month = new Select(driver.findElement(By.xpath("//select[@name='birthday_month']")));
		Select dd_Year = new Select (driver.findElement(By.xpath("//select[@name='birthday_year']")));
		WebElement rb_Gender = driver.findElement(By.xpath("//input[@name='sex' and @value='2']"));
		WebElement btn_SignUp = driver.findElement(By.xpath("//button[@name='websubmit']"));
		
		//Type in fields
		txt_FirstName.sendKeys(firstName);
		txt_LastName.sendKeys(lastName);
		txt_Email.sendKeys(email);
		txt_Password.sendKeys(password);
		//Select date of birth from dropdown
		dd_BDay.selectByVisibleText(BDay);
		dd_Month.selectByVisibleText(Month);
		dd_Year.selectByVisibleText(Year);
		//Select gender
		rb_Gender.click();
		//Click SignUp
		btn_SignUp.click();

		Thread.sleep(5000);
			
	}
	
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	

}
