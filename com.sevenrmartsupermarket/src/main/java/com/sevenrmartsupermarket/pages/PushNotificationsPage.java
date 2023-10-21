package com.sevenrmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.WaitUtility;

public class PushNotificationsPage {

	WebDriver driver;

	@FindBy(xpath = "//p[contains(text(),'Push Notifications')]")
	WebElement pushnotificationmenu;
	@FindBy(id = "title")
	WebElement titlefield;
	@FindBy(id = "description")
	WebElement descriptionfield;
	@FindBy(xpath = "//button[@name='create']")
	WebElement sendButton;
	@FindBy(xpath = "//a[contains(text(),'Reset')]")
	WebElement resetButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successAlertmessage;

	public PushNotificationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPushNotification() {
		pushnotificationmenu.click();

	}

	public void enterTitle(String title) {
		titlefield.sendKeys(title);
	}

	public void enterDescription(String description) {
		descriptionfield.sendKeys(description);
	}

	public void clickOnSendButton() {
		sendButton.click();
	}

	public void sendPushNotification(String title, String description) {
		clickOnPushNotification();
		enterTitle(title);
		enterDescription(description);
		clickOnSendButton();
	}

	public String sendPushNotificationSuccessMessage() {

		WaitUtility waitutility = new WaitUtility(driver);
		waitutility.waitforElementToBeVisible("//div[@class='alert alert-success alert-dismissible']//button", 30);
		String message = successAlertmessage.getText();
		return message;
	}

	public String restPushNotifications(String title, String description) {
		clickOnPushNotification();
		enterTitle(title);
		enterDescription(description);
		resetButton.click();
		String titlePlaceholder = titlefield.getAttribute("placeholder");
		String descriptionPlaceholder = descriptionfield.getAttribute("placeholder");
        String actualResult;

		if ((titlePlaceholder.equals("Enter Title")) && (descriptionPlaceholder.equals("Enter Description"))) {
			actualResult = "Reset Successfull";
		} else {
			actualResult = "Reset not Successfull";
		}

		return actualResult;

	}

}
