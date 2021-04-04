package com.lp.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lp.qa.base.TestBase;

public class HomePaymentsPage extends TestBase {
		
	//a[contains(text(),'Home')]
	// Defining Elements here
	
		@FindBy(xpath="//a[contains(text(),'Home')]")
		WebElement homeMenu;
		
		
		public HomePaymentsPage() throws IOException{
			
			PageFactory.initElements(driver, this);
			
		}
		public String getPageTitle() {
			return driver.getTitle();
		}
		
	

}
