package com.sevenrmartsupermarket.tests;

import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class SampleTest extends Base {
	@Test
	public void verifyDeleteUser() {
		System.out.println(GeneralUtility.getRandomFullName());
		System.out.println(GeneralUtility.getRandomBloodGroup());
		System.out.println(GeneralUtility.getRandomStreetAddress());
		System.out.println(GeneralUtility.getRandomFirstName());

}
	
	@Test(dataProvider = "Login Credentials" ,dataProviderClass = Constants.class)
	public void loginUserCheck(String username , String password )
	{
		//loginpage=new LoginPage(driver);
		//loginpage.Login(username, password); 
	     
		
		System.out.println(username);
		System.out.println(password);
	}
}
