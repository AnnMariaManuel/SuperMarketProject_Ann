package com.sevenrmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class AdminUsersPage {

	WebDriver driver;
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();
	WaitUtility waitutility;

	@FindBy(xpath = "//i[@class='nav-icon fas fa-users']//following-sibling::p")
	private WebElement adminUserMenu;
	@FindBy(xpath = "//div[@class='col-sm-12']/a[1]")
	private WebElement newButton;
	@FindBy(id = "username")
	private WebElement userNameField;
	@FindBy(id = "password")
	private WebElement passWordField;
	@FindBy(id = "user_type")
	private WebElement userTypeDropdown;
	@FindBy(xpath = "//button[@type='submit' and @name='Create']")
	private WebElement saveButton;
	@FindBy(xpath = "//button[@type='submit' and @name='Create']/following-sibling::a")
	private WebElement resetButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement message;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private List<WebElement> namesList;
	@FindBy(id = "username")
	private WebElement usernameedit;
	@FindBy(id = "password")
	private WebElement passwordedit;
	@FindBy(id = "user_type")
	private WebElement usertypeedit;
	@FindBy(name = "Update")
	private WebElement updatebutton;
	@FindBy(xpath = "//a[@onclick='click_button(2)']")
	private WebElement searchButton;
	@FindBy(xpath = "//button[@class='close']")
	private WebElement alertCloseIcon;
	@FindBy(xpath = "//input[@id='un']")
	private WebElement userNameSearch;
	@FindBy(xpath = "//button[@name='Search']")
	private WebElement userSearch;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private WebElement userNamesearchResult;
	@FindBy(xpath = "//div[@class='col-sm-12']//a[3]")
	private WebElement editpagereset;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnAdminUsersMenu() {
		adminUserMenu.click();
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassword(String passWord) {
		passWordField.sendKeys(passWord);
	}

	public void enterUserType(String value) {
		pageutility = new PageUtility(driver);
		userTypeDropdown.click();
		pageutility.select_ByVisibleText(userTypeDropdown, value);

	}

	public void clickOnSaveButton() {
		waitutility = new WaitUtility(driver);
		waitutility.waitForElementToBeClickable(saveButton, 20l);
		saveButton.click();
	}

	public void clickOnResetButton() {
		resetButton.click();

	}

	public void createAdminUser(String userName, String passWord, String value) {
		clickOnAdminUsersMenu();
		clickOnNewButton();
		enterUserName(userName);
		enterPassword(passWord);
		enterUserType(value);
		clickOnSaveButton();

	}

	public boolean userCreationMessage() {

		return generalutility.isTextPresent(message, "User Created Successfully");

	}

	public String deactivateUser(String personName) {
		pageutility = new PageUtility(driver);
		int index = 0;
		generalutility = new GeneralUtility();
		List<String> names = new ArrayList<String>();
		names = generalutility.getTextOfElements(namesList);
		for (String item : names) {
			if (personName.equals(item)) {
				index++;
				break;
			}
			index++;
		}

		WebElement statusOfUser = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
		if (statusOfUser.getText().equals(Constants.activatedUserSatus)) {
			WebElement deactivateButton = driver
					.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["
							+ index + "]/td[5]/a[1]"));
			pageutility.scrollAndClick(deactivateButton);
			WebElement inactiveStatus = driver.findElement(By.xpath(
					"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
			return inactiveStatus.getText();
		} else {

			return "User already deactivated !!, Please select an Activer user to perform deactivation ";
		}
	}

	public String activateUser(String personName) {
		pageutility = new PageUtility(driver);
		int index = 0;
		generalutility = new GeneralUtility();
		List<String> names = new ArrayList<String>();
		names = generalutility.getTextOfElements(namesList);
		for (String item : names) {
			if (personName.equals(item)) {
				index++;
				break;
			}
			index++;
		}

		WebElement activateButton = driver.findElement(By.xpath(
				"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]/td[5]/a[1]"));

		WebElement statusOfUser = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
		if (statusOfUser.getText().equals(Constants.deactivatedUserSatus)) {
			pageutility.scrollAndClick(activateButton);
			WebElement activeStatus = driver.findElement(By.xpath(
					"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
			return activeStatus.getText();
		} else {
			return "User already Active ";
		}

	}

	public void editUserButtonClick(String personName) {

		pageutility = new PageUtility(driver);
		int index = 0;
		generalutility = new GeneralUtility();
		List<String> names = new ArrayList<String>();
		names = generalutility.getTextOfElements(namesList);
		for (String item : names) {
			if (personName.equals(item)) {
				index++;
				break;
			}
			index++;
		}

		WebElement editButton = driver.findElement(By.xpath(
				"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[5]/a[2]"));
		pageutility.scrollAndClick(editButton);

	}

	public void editUserName(String userNameEdit) {

		usernameedit.click();
		usernameedit.clear();
		usernameedit.sendKeys(userNameEdit);
		clickOnUpdateButton();

	}

	public String editUserNameSuccessMessage() {
		WaitUtility waitutility = new WaitUtility(driver);
		waitutility.waitforElementToBeVisible("//button[@class='close']", 30);
		String editUserSuccesMessage = message.getText();
		return editUserSuccesMessage;
	}

	public void editPassword(String passWordEdit) {
		passwordedit.click();
		passwordedit.clear();
		passwordedit.sendKeys(passWordEdit);
		clickOnUpdateButton();
	}

	public void editUserType(String userTypeEdit) {
		PageUtility pageutility = new PageUtility(driver);
		usertypeedit.click();
		pageutility.select_ByVisibleText(usertypeedit, userTypeEdit);
		clickOnUpdateButton();
	}

	public void clickOnUpdateButton() {
		updatebutton.click();
	}

	public void editUser(String userNameEdit, String passWordEdit, String dropDownValueEdit) {

		editUserName(userNameEdit);
		editPassword(passWordEdit);
		editUserType(dropDownValueEdit);
		clickOnUpdateButton();

	}

	public void alertClose() {
		alertCloseIcon.click();
	}

	public boolean searchUser(String userName) {
		searchButton.click();
		userNameSearch.click();
		userNameSearch.sendKeys(userName);
		userSearch.click();
		if (userName.equals(userNamesearchResult.getText())) {
			return true;
		} else {
			return false;
		}

	}

	public String deleteUser(String personName) {
		pageutility = new PageUtility(driver);
		int index = 0;
		generalutility = new GeneralUtility();
		List<String> names = new ArrayList<String>();
		names = generalutility.getTextOfElements(namesList);
		for (String item : names) {
			if (personName.equals(item)) {
				index++;
				break;
			}
			index++;
		}

		WebElement deleteButton = driver.findElement(By.xpath(
				"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]/td[5]/a[3]"));

		deleteButton.click();
		pageutility.alertAccept();
		return deleteUsermessage();

	}

	public String deleteUsermessage() {
		WaitUtility waitutility = new WaitUtility(driver);
		waitutility.waitforElementToBeVisible("//button[@class='close']", 30);
		return message.getText();
	}

	public void clickEditResetButton() {
		editpagereset.click();
	}

	public boolean adminUsersReset(String userName) {
		editUserButtonClick(userName);
		clickEditResetButton();
		return usernameedit.isDisplayed();
	}

}
