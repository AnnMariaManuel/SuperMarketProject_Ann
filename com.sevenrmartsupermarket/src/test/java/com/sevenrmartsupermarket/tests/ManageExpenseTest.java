package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManageExpensePage;

public class ManageExpenseTest extends Base {

	ManageExpensePage manageexpensepage;
	LoginPage loginpage;

	@Test(priority = 1)
	public void verifyAddExpenses() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		manageexpensepage = new ManageExpensePage(driver);
		String actualSuccessMessage = manageexpensepage.addExpense("Admin(Admin)", "22-10-2024", "abc", "7", "8",
				"Debit Cash", "200", "Adding expense test", "SampleImage");
		Assert.assertTrue(actualSuccessMessage.contains("Expense Record Created Successfully"), "Test case failed ");
	}

	@Test(priority = 2)
	public void verifyCancel() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		manageexpensepage = new ManageExpensePage(driver);

		Assert.assertFalse(manageexpensepage.clickOnCancelButton(), "Cancel button is not working as expected");

	}

}
