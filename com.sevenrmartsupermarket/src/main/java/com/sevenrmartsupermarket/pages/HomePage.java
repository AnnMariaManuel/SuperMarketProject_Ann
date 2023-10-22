package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class HomePage

{

	WebDriver driver;

	GeneralUtility generalutility = new GeneralUtility();
	Properties properties = new Properties();
	FileInputStream ip;

	@FindBy(xpath = "//a[@class='d-block']")
	private WebElement profileName;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getProfileName() {
		return generalutility.getTextOfElement(profileName);

	}
}
