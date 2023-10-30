package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class HomeTest extends Base

{
	HomePage homepage;
	LoginPage loginpage;
	
	@Test
	public void verifyProfileNameDisplayed()
	{
		HomePage homepage=new HomePage(driver);
		loginpage=new LoginPage(driver);
		loginpage.login();
		Assert.assertEquals(homepage.getProfileName(), "Admin");
	}
	

}
