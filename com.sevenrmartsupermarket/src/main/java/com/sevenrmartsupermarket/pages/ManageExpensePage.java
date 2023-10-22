package com.sevenrmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.ImageUpload;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class ManageExpensePage

{
	WebDriver driver;
	LoginPage loginpage;

	@FindBy(xpath = "//ul[@class='nav nav-pills nav-sidebar flex-column']//li[4]")
	private WebElement manageExpenseExpand;
	@FindBy(xpath = "//a[@href='https://groceryapp.uniqassosiates.com/admin/list-expense']")
	private WebElement manageExpenseMenu;
	@FindBy(xpath = "//div[@class='col-sm-12']//a[1]")
	private WebElement newButton;
	@FindBy(id = "user_id")
	private WebElement userDropDown;
	@FindBy(id = "ex_date")
	private WebElement dateSelector;
	@FindBy(id = "ex_cat")
	private WebElement categoryDropDown;
	@FindBy(id = "order_id")
	private WebElement orderIdDropDown;
	@FindBy(id = "purchase_id")
	private WebElement purchaseIdDropDown;
	@FindBy(id = "ex_type")
	private WebElement expenseTypeDropDown;
	@FindBy(id = "amount")
	private WebElement amount;
	@FindBy(name = "remarks")
	private WebElement remark;
	@FindBy(xpath = "//input[@type='file']")
	private WebElement chooseFile;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='card-footer']//a")
	private WebElement cancelButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement expenseRecordCreateMessage;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']//button")
	private WebElement alertCloseIcon;

	public ManageExpensePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnManageExpenseMenu() {
		manageExpenseExpand.click();
		manageExpenseMenu.click();
	}

	public void newButtonClick() {
		newButton.click();
	}

	public void enterUserType(String userTypevalue) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(userDropDown, userTypevalue);
	}

	public void enterDate(String date) {
		dateSelector.clear();
		dateSelector.sendKeys(date);
	}

	public void selectCategory(String category) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(categoryDropDown, category);

	}

	public void selectOrderID(String orderNum) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(orderIdDropDown, orderNum);
	}

	public void selectPurchaseID(String purchaseNum) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(purchaseIdDropDown, purchaseNum);
	}

	public void selectExpensetype(String expenseType) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(expenseTypeDropDown, expenseType);
	}

	public void enterAmount(String amountValue) {
		amount.sendKeys(amountValue);
	}

	public void enterRemarks(String remarkValue) {
		remark.sendKeys(remarkValue);
	}

	public void chooseFileToUpload(String imageName) {

		ImageUpload imageupload = new ImageUpload();
		imageupload.imageFileUpload(imageName, chooseFile);

	}

	public void clickOnSaveButton() {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.scrollAndClick(saveButton);

	}

	public String expenseCreationSuccessMessage() {
		WaitUtility waitutility = new WaitUtility(driver);
		waitutility.waitforElementToBeVisible(alertCloseIcon, 30);
		return expenseRecordCreateMessage.getText();
	}

	public String addExpense(String userType, String date, String category, String orderId, String purchaseId,
			String expenseType, String amountValue, String remarkValue, String imageName) {
		clickOnManageExpenseMenu();
		newButtonClick();
		enterUserType(userType);
		enterDate(date);
		selectCategory(category);
		selectOrderID(orderId);
		selectPurchaseID(purchaseId);
		selectExpensetype(expenseType);
		enterAmount(amountValue);
		enterRemarks(remarkValue);
		chooseFileToUpload(imageName);
		clickOnSaveButton();
		return expenseCreationSuccessMessage();

	}

	public boolean clickOnCancelButton() {
		PageUtility pageutility = new PageUtility(driver);
		WaitUtility waitutility = new WaitUtility(driver);
		GeneralUtility generalutility = new GeneralUtility();
		clickOnManageExpenseMenu();
		newButtonClick();
		pageutility.scrollAndClick(cancelButton);
		waitutility.waitforElementToBeInVisible(saveButton, 30);
		return generalutility.is_Displayed(saveButton);
	}

}
