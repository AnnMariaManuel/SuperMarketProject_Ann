package com.sevenrmartsupermarket.utilities;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageUtility

{

	WebDriver driver;
	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void select_ByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void select_ByVisibleText(WebElement element, String text) {

		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void select_ByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void jsClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void jsScrollintoView(WebElement element) {

		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public void elementRightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}

	public void elementDoubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}

	public void elementsDragandDrop(WebElement element1, WebElement element2) {
		Actions action = new Actions(driver);
		action.dragAndDrop(element1, element2);
	}

	public void alertAccept() {
		driver.switchTo().alert().accept();
	}

	public void alertDismiss() {
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	public void switchToWindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();

		for (String item : childWindows) {
			if (!parentWindow.equals(item)) {
				driver.switchTo().window(item);
			}
		}
	}

	public void scrollAndClick(WebElement element) {
		int y = 0;
		while (!isClicked(element)) {
			js.executeScript("window.scrollBy(0," + y + ")");
			y = y + 5;
		}

	}

	public boolean isClicked(WebElement element) {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
