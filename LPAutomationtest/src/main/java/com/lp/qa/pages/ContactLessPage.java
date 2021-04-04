package com.lp.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lp.qa.base.TestBase;
import com.lp.qa.util.Util;

public class ContactLessPage extends TestBase {
	
	@FindBy(id="PatientAppointment")
	WebElement contactlessRadioBtn;
	
	@FindBy(name="liquiddateofvisit")
	WebElement dateofVisit;
	
	@FindBy(name="liquidaccountnumber[]")
	WebElement referenceNumber;
	
	@FindBy(name="liquidspecialist[]")
	WebElement speciality;
	
	@FindBy(name="liquidfirst[]")
	WebElement firstName;
	
	@FindBy(name="liquidlast[]")
	WebElement lastName;
	
	@FindBy(name="liquidprocphone[]")
	WebElement Phone_Email;
	
	@FindBy(name="liquidprocamt[]")
	WebElement Amount;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")
	WebElement backBtn;
	
	@FindBy(xpath="//input[@id='btnGet']")
	WebElement submitButton;
	
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement HomeMenu;
	
	public ContactLessPage() throws IOException{
		
		PageFactory.initElements(driver,this);
		
	}
	public Boolean ContactlessPayment(String RefNo,String FirstName,String LastName,String Phone_Email1,String Amount1) throws InterruptedException, AWTException{		
		Boolean backtohome = null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		HomeMenu.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PatientAppointment")));
		contactlessRadioBtn.click();
		Boolean CP=contactlessRadioBtn.isSelected();
		Reporter.log("Contactless Payment option has been selected!");
		if(CP==true){
			dateofVisit.click();
			Util.getCurrentDate();
			WebElement date=driver.findElement(By.xpath("//a[contains(text(),'"+Util.currentdate+"')]"));
			date.click();			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("liquidaccountnumber[]")));			
			referenceNumber.sendKeys(RefNo);
			firstName.sendKeys(FirstName);
			lastName.sendKeys(LastName);
		//	this.PatientName=FirstName1+" "+LastName1;
			Phone_Email.sendKeys(Phone_Email1);			
			Amount.sendKeys(Amount1);
			Thread.sleep(2000);
			submitButton.click();
			Reporter.log("Patient details entered successfully in Contactless Payment form:"
					+"\n"+"Patient Ref No :$"+RefNo+""
					+"\n"+"Patient Name :"+FirstName+" "+LastName+""
					+"\n"+"Phone/Email :"+Phone_Email1+""
					+"\n"+"Amount :$"+Amount1+""			
					);
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")));			
			backtohome=backBtn.isDisplayed();
			Robot robot=new Robot();		
			if(backtohome==true) {
				 Thread.sleep(2000);
				 backBtn.click();
				 Reporter.log("Contactless payment link has been sent to this phone/Email :"+Phone_Email1);
				
			}
			
		}
		return backtohome;
		
	}
	

}
