package com.qa.scrp.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import org.openqa.selenium.support.PageFactory;

import com.qa.scrp.base.BaseClass;



public class MainPage extends BaseClass{

	public MainPage() throws IOException {
		
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean retryingFindClick(By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	

}
