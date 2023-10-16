package com.sevenrmartsupermarket.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	WebElement adminUserMenu;
	@FindBy(xpath = "//div[@class='col-sm-12']/a[1]")
	WebElement newButton;
	@FindBy(id = "username")
	WebElement username_field;
	@FindBy(id = "password")
	WebElement password_field;
	@FindBy(id = "user_type")
	WebElement usertype_dropdown;
	@FindBy(xpath = "//button[@type='submit' and @name='Create']")
	WebElement savebutton;
	@FindBy(xpath = "//button[@type='submit' and @name='Create']/following-sibling::a")
	WebElement resetbutton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement message;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	List<WebElement> namesList;
	@FindBy(id = "username")
	WebElement usernameedit;
	@FindBy(id = "password")
	WebElement passwordedit;
	@FindBy(id = "user_type")
	WebElement usertypeedit;
	@FindBy(name = "Update")
	WebElement updatebutton;
	@FindBy(xpath="//a[@onclick='click_button(2)']")
	WebElement searchButton;
	@FindBy(xpath="//button[@class='close']")
	WebElement alertCloseIcon;
	@FindBy(xpath="//input[@id='un']")
	WebElement userNameSearch;
	@FindBy(xpath="//button[@name='Search']")
	WebElement userSearch;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	WebElement userNamesearchResult;
	@FindBy(xpath="//div[@class='col-sm-12']//a[3]")
	WebElement editpagereset;
	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnAdminUsersMenu() {
		adminUserMenu.click();
	}

	public void clickonNewButton() {
		newButton.click();
	}

	public void enterUserName(String userName) {
		username_field.sendKeys(userName);
	}

	public void enterPassword(String passWord) {
		password_field.sendKeys(passWord);
	}

	public void enterUserType(String value) {
		pageutility = new PageUtility(driver);
		usertype_dropdown.click();

		// Select select = new Select(usertype_dropdown);
		// select.selectByVisibleText("Admin");
		pageutility.select_ByVisibleText(usertype_dropdown, value);
		// pageutility.select_ByIndex(usertype_dropdown, 1);
	}

	public void clickSaveButton() {
		waitutility = new WaitUtility(driver);
		waitutility.waitForElementToBeClickable(savebutton, 20l);
		savebutton.click();
	}

	public void clickResetButton() {
		resetbutton.click();

	}

	public void createAdminUser(String userName, String passWord, String value) {
		clickOnAdminUsersMenu();
		clickonNewButton();
		enterUserName(userName);
		enterPassword(passWord);
		enterUserType(value);
		clickSaveButton();

	}

	public boolean userCreationMessage() {

		return generalutility.isTextPresent(message, "User Created Successfully");

	}

	public String deactivateuser(String personName) {
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

		WebElement statusofUser = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
		if (statusofUser.getText().equals(Constants.activatedUserSatus)) {
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

	public String activateuser(String personName) {
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
		if (statusOfUser.getText().equals(Constants.deactivatedUserSatus)) 
		{
			pageutility.scrollAndClick(activateButton);
			WebElement activeStatus = driver.findElement(By
					.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
			return activeStatus.getText();
		} 
		else {
			return "User already Active ";
		}

	}

	public void editUserClick(String personName) {

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

	public void editUSerType(String userTypeEdit) {
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
		editUSerType(dropDownValueEdit);
		clickOnUpdateButton();

	}
	public void alertClose()
	{
		alertCloseIcon.click();
	}
	
	public boolean searchUser(String userName)
	{
		searchButton.click();
		userNameSearch.click();
		userNameSearch.sendKeys(userName);
		userSearch.click();
		if (userName.equals(userNamesearchResult.getText()))
				{
			       return true;
				}
		else
		{
			return false;
		}
	
		
	}
	
	public String deleteUser(String personName)
	{
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
	
	public String deleteUsermessage()
	{
		WaitUtility waitutility = new WaitUtility(driver);
		waitutility.waitforElementToBeVisible("//button[@class='close']", 30);
		return message.getText();
	}
	
	public void clickeditResetButton()
	{
	   editpagereset.click();
	}
	public boolean adminUsersReset(String userName)
	{
		editUserClick(userName);
		clickeditResetButton();
		return usernameedit.isDisplayed();
	}

	public void paginationCheck(int page) 
	{
		pageutility = new PageUtility(driver);
		WebElement pageNumber=driver.findElement(By.xpath("//ul[@class='pagination pagination-sm m-0 float-right']/li["+page+"]//a"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", pageNumber);
		js.executeScript("arguments[0].click();", pageNumber);
		//pageutility.scrollAndClick(pageNumber);
		
		//String color =pageNumber.getCssValue("background-color");
		//String pagenum=pageNumber.getText();
		//System.out.println(color);
		//System.out.println(pagenum);
	}
}
