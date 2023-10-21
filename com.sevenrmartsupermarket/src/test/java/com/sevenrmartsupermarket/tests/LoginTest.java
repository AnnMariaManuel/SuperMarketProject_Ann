package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.ScreenShot;

public class LoginTest extends Base {

	LoginPage loginpage;
	HomePage homepage;
	ExcelReader excelreader = new ExcelReader();

	@Test
	public void verifyLoginFunctionality() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.Login();
		String actualProfileName = homepage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName,
				"Names not matching , Invalid user profile launched");
	}

	@Test
	public void verifyInvalidLoginErrorMessage() {
		loginpage = new LoginPage(driver);
		loginpage.Login("admin", "admin");

	}

	@Test
	public void verifyStaffLogin() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		excelreader.setExcelFile("LoginData", "Login Credentials");
		String userName = excelreader.getCellData(0, 0);
		String passWord = excelreader.getCellData(0, 1);
		loginpage.Login(userName, passWord);
		Assert.assertEquals(homepage.getProfileName(), "Emma", "Names not matching ,  user profile not launched");

	}

	@Test(dataProvider = "Login Credentials", dataProviderClass = Constants.class)
	public void loginUserCheck(String username, String password) {
		loginpage = new LoginPage(driver);
		loginpage.Login(username, password);

	}

}
