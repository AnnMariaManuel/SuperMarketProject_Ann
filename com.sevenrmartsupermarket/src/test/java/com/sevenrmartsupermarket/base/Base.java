package com.sevenrmartsupermarket.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.ScreenShot;
import com.sevenrmartsupermarket.utilities.WaitUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base

{

	public WebDriver driver;
	Properties properties=new Properties(); //Properties is a class in java
	FileInputStream ip;
	
	/** constructor for initalizing config file **/
	
	public Base()
	{
		try
		{
		ip=new FileInputStream(Constants.CONFIG_FILE_PATH);
		properties.load(ip); //to read the config.properties file
		}
		catch(Exception e)
		{
			e.printStackTrace();//whole exception definition will come in console
			System.out.println("File not found ");
		}
	}

	/** Launching different browsers  **/
	
	public void initialize(String browser, String url)
	{
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitUtility.IMPLICIT_WAIT));//defined in waitutilityclass
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WaitUtility.PAGE_LOAD_WAIT));//defined in waitutilityclass
	}
    @Parameters("browser")
	@BeforeMethod(enabled=false,alwaysRun = true)
	public void launchBrowser(String browser)
	{
		String url=properties.getProperty("url");//to read data from property file
		initialize(browser, url);
		
	}
    
    @BeforeMethod(enabled = true,alwaysRun = true)
	public void launchBrowser()
	{
		String url=properties.getProperty("url");//to read data from property file
		String browser=properties.getProperty("browser");
		initialize(browser, url);
		
	}
	@AfterMethod(alwaysRun = true) //this will be run irrespective of group mentioned in suite 
	public void terminateSession(ITestResult itestresult)//testNG listener to check if a test case is pass or fail; 
	{
		ScreenShot screenshot=new ScreenShot();
		if(itestresult.getStatus()==ITestResult.FAILURE)
		{
			screenshot.takeScreenShot(driver, itestresult.getName());  //itestresult.getName() is used to get test case name 
		}
		driver.quit();
	}
}
