package com.sevenrmartsupermarket.constants;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermarket.utilities.ExcelReader;

public class Constants {

	ExcelReader excelreader = new ExcelReader();

	public static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\config.properties";

	public static final String EXCEL_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\ExcelFiles\\";
	public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "\\ExtentReport";
	public static final String SCREENSHOT_FILE_PATH = System.getProperty("user.dir") + "\\screenshots\\";
	public static final String IMAGES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\";

	/** Expected results **/

	/** Login Page **/
	public static final String expectedLoginErrorMessage = "UserName not valid ";

	/** AdminUserPage **/
	public static final String deactivatedUserSatus = "Inactive";
	public static final String activatedUserSatus = "Active";
	public static final String editUserNameSuccessMessage = "User Updated Successfully";
	public static final String createUserSuccessMessage = "User Created Successfully";

	/** DataProviders **/

	@DataProvider(name = "Login Credentials")
	public Object[][] userLoginData() {
		excelreader.setExcelFile("LoginData", "ValidLoginCredentials");
		return excelreader.getMultidimentionalData(3, 2);
	}

}
