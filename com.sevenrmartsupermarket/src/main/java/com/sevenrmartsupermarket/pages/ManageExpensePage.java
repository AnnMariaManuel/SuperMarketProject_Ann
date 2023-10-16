package com.sevenrmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.PageUtility;

public class ManageExpensePage 


{
	WebDriver driver;
	LoginPage loginpage;
	
	@FindBy(xpath="//ul[@class='nav nav-pills nav-sidebar flex-column']//li[4]")
	WebElement manageExpenseExpand;
	@FindBy(xpath="//a[@href='https://groceryapp.uniqassosiates.com/admin/list-expense']")
	WebElement manageExpenseMenu;
	@FindBy(xpath="//div[@class='col-sm-12']//a[1]")
	WebElement newButton;
	@FindBy(id="user_id")
	WebElement userDropDown;
	@FindBy(id="ex_date")
	WebElement dateSelector;
	@FindBy(id="ex_cat")
	WebElement categoryDropDown;
	@FindBy(id="order_id")
	WebElement orderIdDropDown;
	@FindBy(id="purchase_id")
	WebElement purchaseIdDropDown;
	@FindBy(id="ex_type")
	WebElement expenseTypeDropDown;
	@FindBy(id="amount")
	WebElement amount;
	@FindBy(name="remarks")
	WebElement remark;
	@FindBy(xpath="//input[@type='file']")
	WebElement chooseFile;
	@FindBy(xpath="//button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath="//div[@class='card-footer']//a")
	WebElement cancelButton;
	
	
	public ManageExpensePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnManageExpenseMenu()
	{
		manageExpenseExpand.click();
		manageExpenseMenu.click();
	}
	
	public void newButtonClick()
	{
		newButton.click();
	}
	public void enterUserType(String value)
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.select_ByVisibleText(userDropDown, value);
	}
	public void enterDate(String date)
	{
		dateSelector.clear();
		dateSelector.sendKeys(date);
	}
	
	public void selectCategory(String category)
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.select_ByVisibleText(categoryDropDown, category);
	
	}
	public void selectOrderID(String orderNum)
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.select_ByVisibleText(orderIdDropDown, orderNum);
	}
	public void selectPurchaseID(String purchaseNum)
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.select_ByVisibleText(purchaseIdDropDown, purchaseNum);
	}
	public void selectExpensetype(String expenseType)
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.select_ByVisibleText(expenseTypeDropDown, expenseType);
	}
	
	public void enterAmount(String amountValue)
	{
		amount.sendKeys(amountValue);
	}
	public void enterReamrks(String remarkValue)
	{
		remark.sendKeys(remarkValue);
	}
	
	public void chooseFileToUpload()
	{
		chooseFile.click();
		chooseFile.sendKeys("C:\\Users\\Tony joseph\\OneDrive\\Desktop\\Selenium files\\Expensereport.jfif");
	}
	

}
