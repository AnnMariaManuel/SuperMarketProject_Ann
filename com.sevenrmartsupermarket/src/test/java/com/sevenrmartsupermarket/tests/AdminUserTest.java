package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.listners.RetryAnalyser;
import com.sevenrmartsupermarket.pages.AdminUsersPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.PageUtility;

public class AdminUserTest extends Base {

	AdminUsersPage adminuserspage;
	LoginPage loginpage;
	ExcelReader excelreader = new ExcelReader();
	PageUtility pageutility;
	SoftAssert softassert = new SoftAssert();

	@Test(groups = "Regression Test", priority = 1, retryAnalyzer = RetryAnalyser.class)
	public void verifyAdminUserCreation() {
		adminuserspage = new AdminUsersPage(driver);
		loginpage = new LoginPage(driver);
		pageutility = new PageUtility(driver);
		loginpage.login();
		excelreader.setExcelFile("AdminUsers", "Admindata");
		adminuserspage.createAdminUser(excelreader.getCellData(3, 0), excelreader.getCellData(3, 1),
				excelreader.getCellData(3, 2));
		Assert.assertTrue(adminuserspage.userCreationMessage(), "User creation message is not correct ");

	}

	@Test(groups = "Smoke Test", priority = 2)
	public void verifyUserDeactivation() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickOnAdminUsersMenu();
		excelreader.setExcelFile("AdminUsers", "Admindata");
		String ActualStatus = adminuserspage.deactivateUser(excelreader.getCellData(3, 0));
		if (ActualStatus.equals("Inactive")) {
			Assert.assertTrue(true);
		} else if (ActualStatus.contains("User already deactivated")) {
			Assert.assertTrue(false, "User is already deactivated , Cant deactivateed again");
		}

	}

	@Test(groups = "Smoke Test", priority = 3)
	public void verifyUserActivation() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickOnAdminUsersMenu();
		excelreader.setExcelFile("AdminUsers", "Admindata");
		String ActualStatus = adminuserspage.activateUser(excelreader.getCellData(3, 0));
		if (ActualStatus.equals("User already Active ")) {
			Assert.assertFalse(true, " User is already Active, Cant Activate again !! ");
		} else if (ActualStatus.equals("Active")) {
			Assert.assertTrue(true);
		}

	}

	@Test(groups = "Smoke Test", priority = 4)
	public void verifyEditUserName() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickOnAdminUsersMenu();
		excelreader.setExcelFile("AdminUsers", "Admindata");
		adminuserspage.editUserButtonClick(excelreader.getCellData(3, 0));
		adminuserspage.editUserName("Sooraj Menon");
		softassert.assertTrue(
				adminuserspage.editUserNameSuccessMessage().contains(Constants.editUserNameSuccessMessage),
				"Edit failed ");
		adminuserspage.alertClose();
		boolean isUserPresent = adminuserspage.searchUser("Sooraj Menon");
		softassert.assertTrue(isUserPresent, "Edited username not found , Edit function failed");
		adminuserspage.editUserButtonClick("Sooraj Menon");
		adminuserspage.editUserName(excelreader.getCellData(3, 0));
		softassert.assertAll();
	}

	@Test(groups = {"Regression Test","Sanity Test"}, priority = 5)
	public void verifyUserSearch() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickOnAdminUsersMenu();
		excelreader.setExcelFile("AdminUsers", "Admindata");
		boolean isUseravailable = adminuserspage.searchUser(excelreader.getCellData(3, 0));
		Assert.assertTrue(isUseravailable, "User not found!!");

	}

	@Test(groups =  "Regression Test" , priority = 6)
	public void verifyDeleteUser() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickOnAdminUsersMenu();
		excelreader.setExcelFile("AdminUsers", "Admindata");
		softassert.assertTrue(
				adminuserspage.deleteUser(excelreader.getCellData(3, 0)).contains("User Deleted Successfully"),
				"Deletion message not correct");
		boolean isUserPresent = adminuserspage.searchUser(excelreader.getCellData(3, 0));
		softassert.assertFalse(isUserPresent);
		softassert.assertAll();
	}

	@Test(priority = 7)
	public void verifyReset() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickOnAdminUsersMenu();
		adminuserspage.adminUsersReset("Annie");
		Assert.assertFalse(adminuserspage.adminUsersReset("Annie"), "Page not Reset");
	}

}
