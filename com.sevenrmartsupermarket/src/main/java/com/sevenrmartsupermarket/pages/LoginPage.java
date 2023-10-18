package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;

public class LoginPage {

	WebDriver driver;
	Properties properties = new Properties(); // Properties is a class in java
	FileInputStream ip;
	
	@FindBy(xpath=" //input[@name='username']")
	private WebElement userNameField;       //encapsulation to make all elements private 
	@FindBy(xpath="//input[@name='password']")
	private WebElement passWordField;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement sigInButton;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

		try {
			ip = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(ip); // to read the config.properties file
		} catch (Exception e) {
			e.printStackTrace();// whole exception definition will come in console
			System.out.println("File not found!!");
		}
	}
	
	public void enterUserName(String userName)
	{
		userNameField.sendKeys(userName);
	}
	
	public void enterPassWord(String passWord)
	{
		passWordField.sendKeys(passWord);
	}
	
	public void clickOnSignInButton()
	{
		sigInButton.click();
	}

	public void Login()
	{
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		enterUserName(username);
		enterPassWord(password);
		clickOnSignInButton();
	}
	
	public void Login(String username, String password)
	{
		enterUserName(username);
		enterPassWord(password);
		clickOnSignInButton();
	}
}
