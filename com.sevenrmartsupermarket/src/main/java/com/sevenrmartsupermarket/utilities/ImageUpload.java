package com.sevenrmartsupermarket.utilities;

import org.openqa.selenium.WebElement;

import com.sevenrmartsupermarket.constants.Constants;

public class ImageUpload {

	public void imageFileUpload(String imagename, WebElement element) {
		try {
			String path = Constants.IMAGES_FILE_PATH + imagename + ".jpg";
			element.sendKeys(path);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("image not Found!!");
		}
	}
}
