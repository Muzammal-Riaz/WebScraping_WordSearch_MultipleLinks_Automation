package com.qa.scrp.TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.scrp.base.BaseClass;
import com.qa.scrp.pages.*;
public class MainTest extends BaseClass {
	
	
	MainPage MainPage;
	int total_count=0, true_count=0, false_count=0, null_count, stale_count=0, webdriverException_Count=0;
	int linksOpen_count=0, linksNotOpen_count=0;
	By by=null;

	public MainTest() throws Exception {
		
		super();
		
	}
	
	@BeforeMethod
	
	public void SetUp() throws IOException
	{
		initialization();
		MainPage = new MainPage();
	}


	@Test 
	public void get_Link()
	{
		
		driver.manage().timeouts().pageLoadTimeout(com.qa.scrp.util.UtilClass.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.qa.scrp.util.UtilClass.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		List<String> links = new ArrayList<String>();
	    List<WebElement> anchors = driver.findElements(By.tagName("a"));
		
	    driver.manage().timeouts().pageLoadTimeout(com.qa.scrp.util.UtilClass.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.qa.scrp.util.UtilClass.IMPLICIT_WAIT, TimeUnit.SECONDS);

		for ( WebElement anchor : anchors )
			{  
            links.add(anchor.getAttribute("href"));
            System.out.println(anchor.getAttribute("href"));
            }
		
				
		for ( String link : links )
		{
			 total_count++;
			 try 
			 {
				 System.out.println(total_count + ") " + link);
				 driver.get(link);
				 driver.manage().timeouts().pageLoadTimeout(com.qa.scrp.util.UtilClass.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			     driver.manage().timeouts().implicitlyWait(com.qa.scrp.util.UtilClass.IMPLICIT_WAIT, TimeUnit.SECONDS);
			     Boolean Check = driver.getPageSource().contains("Consent");
			     System.out.println("Consent is present= " + Check);
				
					if (Check == true)
						{
						
						//WebElement search = driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL + "f" )  ;
						//search.sendkeys("Consent");
							true_count++;
						}
				 else
				 		{
					 		false_count++;
				 		}
					
				System.out.println("******************************************************************************************");
			
			 }
			  
			 catch (NullPointerException e)
			 {
				 System.out.println("Null Pointer Exception");
				 System.out.println("******************************************************************************************");
				 null_count++;
			 }
			 
			 catch (StaleElementReferenceException c)
			 {
				 stale_count++;
				 MainPage.retryingFindClick(by);
				 System.out.println("Stale Element Reference Exception");
				 System.out.println("******************************************************************************************");
				 
			 }
			 
			 catch (WebDriverException d)
			 {
				 System.out.println("Stale Element Reference Exception");
				 System.out.println("******************************************************************************************");
				 webdriverException_Count++;
			 }
		

		}
		
		 System.out.println("******************************************************************************************");
		 System.out.println("The total number of links are= "+ total_count);
		 System.out.println("Consent is present in the following number of links= "+ true_count);
		 System.out.println("Consent isn't present in the following number of links= "+ false_count);
		 System.out.println("Number of Null pointer exceptions are= "+ null_count);
		 System.out.println("Number of Stale Exceptions are= "+ stale_count);
		 System.out.println("Number of WebDriver Exceptions are= "+ webdriverException_Count);
		 
		 linksNotOpen_count = total_count - (true_count + false_count);
		 linksOpen_count= total_count - linksNotOpen_count;
		
		 System.out.println("******************************************************************************************");
		 System.out.println("Number of Links Opened= "+ linksOpen_count);
		 System.out.println("Number of Links Not Opened= "+ linksNotOpen_count);
		 System.out.println("******************************************************************************************");

		 
	}
	
	@AfterMethod
	
	public void TearDown()
	{
		driver.quit();
	}
	
	
}

