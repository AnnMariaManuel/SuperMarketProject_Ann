package com.sevenrmartsupermarket.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility 

{
	WebDriver driver;
	WebDriverWait wait;
	
	
 public static final long IMPLICIT_WAIT=10;
 public static final long PAGE_LOAD_WAIT=20;
 
 public WaitUtility(WebDriver driver)
 {
	 this.driver=driver;
 }
 
 public void waitForElementToBeClickable(WebElement element,long waitTime)
 {
	 wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	 wait.until(ExpectedConditions.elementToBeClickable(element));
 }
 
 public void waitforElementToBeVisible(String xpath,long waitTime)
 {
	 wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
 }
 
 public void waitforElementToBeVisible(By locator,long waitTime )
 {
	 wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }
 
 public void waitforElementToBeVisible(WebElement element,long waitTime )
 {
	 wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	 wait.until(ExpectedConditions.visibilityOf(element));
 }
 public void waitforElementToBeInVisible(WebElement element,long waitTime )
 {
	 wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	 wait.until(ExpectedConditions.invisibilityOf(element));
 }
 
 
 public void fluentWaitforElementToBeVisible(By locator,long waitTime,long pollTime)
 {
	 Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(waitTime)).pollingEvery(Duration.ofSeconds(pollTime)).ignoring(Exception.class);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }
 
 
 
 

}
