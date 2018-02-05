package Mathi.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Objects.LoginPage;
import utility.DataProviderTest;
import utility.TakingScreenShot;
import utility.TakingfullpagescreenShot;

public class Loginvalidation {
	
	String driverpath="src/main/java/Drivers/chromedriver.exe";
	WebDriver driver;
	
	@BeforeTest
	public void Launchbrowser()
	{
		File file = new File("C:/Users/padivv2/eclipse-workspace/Test/src/test/java/utility/datafile.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver",driverpath);
		driver=new ChromeDriver();
		driver.get(prop.getProperty("USurl"));
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="SearchProvider", dataProviderClass=DataProviderTest.class)
	public void LoginvalidationTest(String user, String password, String errormsg) throws Exception
	{
		LoginPage LP=new LoginPage(driver);
		LP.Enteremail(user);
		LP.Enterpassword(password);
		TakingScreenShot SS = new TakingScreenShot();
		SS.SSTest(driver, "C:\\Users\\padivv2\\eclipse-workspace\\Test\\ScreenShots\\Loginvalidation\\"+errormsg+"beforeclickLogin.png");
		LP.clicklogin();
		Thread.sleep(3000);
		SS.SSTest(driver, "C:\\Users\\padivv2\\eclipse-workspace\\Test\\ScreenShots\\Loginvalidation\\"+errormsg+"afterclickLogin.png");
	    	}
	@AfterTest
	
	public void Closebrowser()
	
	{
		driver.close();
	}
	

}
