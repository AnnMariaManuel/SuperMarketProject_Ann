package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.PushNotificationsPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;

public class PushNotificationsTest extends Base 

{
	
	PushNotificationsPage pushnotificationpage;
	LoginPage loginpage;
	ExcelReader excelreader=new ExcelReader();
	
	@Test(groups = "Sanity Test",priority = 1)
	public void verifySendPushNotification()
	{
		loginpage=new LoginPage(driver);
		pushnotificationpage=new PushNotificationsPage(driver);
		loginpage.login();
		pushnotificationpage.clickOnPushNotification();
		excelreader.setExcelFile("NotificationsData", "Notification");
	    pushnotificationpage.sendPushNotification(excelreader.getCellData(0, 0), excelreader.getCellData(0, 1));
	    Assert.assertTrue(pushnotificationpage.sendPushNotificationSuccessMessage().contains("Message send successfully"));
	}
	
	@Test(groups="Sanity Test",priority = 2)
	public void verifyPushNotificationReset()
	{
		loginpage=new LoginPage(driver);
		pushnotificationpage=new PushNotificationsPage(driver);
		loginpage.login();
		String message=pushnotificationpage.restPushNotifications("Message Alert !!!1", "This is a sample push notification !!!!!!");
		Assert.assertTrue(message.equals("Reset Successfull"),"Reset Failed !!!");
	}
	

}
