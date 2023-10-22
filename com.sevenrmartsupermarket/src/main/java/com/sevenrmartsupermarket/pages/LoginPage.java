package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class LoginPage {

	WebDriver driver;
	Properties properties = new Properties();
	FileInputStream ip;

	@FindBy(xpath = " //input[@name='username']")
	private WebElement userNameField;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passWordField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement sigInButton;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement invalidUserLoginAlert;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']//button")
	private WebElement alertCloseIcon;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

		try {
			ip = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File not found!!");
		}
	}

	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassWord(String passWord) {
		passWordField.sendKeys(passWord);
	}

	public void clickOnSignInButton() {
		sigInButton.click();
	}

	public void login() {
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		enterUserName(username);
		enterPassWord(password);
		clickOnSignInButton();
	}

	public void login(String username, String password) {
		enterUserName(username);
		enterPassWord(password);
		clickOnSignInButton();
	}

	public String invalidLoginMessage() {
		WaitUtility waitutility = new WaitUtility(driver);
		waitutility.waitforElementToBeVisible(alertCloseIcon, 10l);
		return invalidUserLoginAlert.getText();
	}
}
