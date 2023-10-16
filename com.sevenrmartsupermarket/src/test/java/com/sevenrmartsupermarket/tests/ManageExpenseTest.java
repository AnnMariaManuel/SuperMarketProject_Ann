package com.sevenrmartsupermarket.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManageExpensePage;

public class ManageExpenseTest extends Base {
	
	
	
	ManageExpensePage manageexpensepage;
	LoginPage loginpage;
	
	@Test
	public void verifyManageExpenses()
	{
		loginpage=new LoginPage(driver);
		loginpage.Login();
		manageexpensepage=new ManageExpensePage(driver);
		manageexpensepage.clickOnManageExpenseMenu();
		manageexpensepage.newButtonClick();
		manageexpensepage.enterUserType("Staff(Staff)");
		manageexpensepage.enterDate("16-10-2023");
		manageexpensepage.selectCategory("zebra");
		manageexpensepage.selectOrderID("6");
		manageexpensepage.selectPurchaseID("7");
		manageexpensepage.selectExpensetype("Debit Bank");
		manageexpensepage.enterReamrks("This is a remark");
		manageexpensepage.chooseFileToUpload();
		
	}
	

}
