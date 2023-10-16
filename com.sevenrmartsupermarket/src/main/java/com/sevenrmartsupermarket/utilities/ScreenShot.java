package com.sevenrmartsupermarket.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.sevenrmartsupermarket.constants.Constants;

public class ScreenShot {
	
	TakesScreenshot takescreenshot; 
	
	public void takeScreenShot(WebDriver driver, String imageName)
	{
		try 
		{
			takescreenshot=(TakesScreenshot) driver;
			File screenShot=takescreenshot.getScreenshotAs(OutputType.FILE);//screenshot capturing happens here 
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());//to get time stamp 
			String destinationPath=Constants.SCREENSHOT_FILE_PATH+imageName+"_"+timeStamp+".png"; //setting path 
			File file=new File(destinationPath);
			FileHandler.copy(screenShot, file);
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
	}

}
