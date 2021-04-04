package com.lp.qa.pages;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.lp.qa.base.TestBase;
import com.lp.qa.util.Util;
import com.relevantcodes.extentreports.LogStatus;

public class ManualEntryPaymentPage extends TestBase {
	public String TransactionID;
	public String Ref_NO;
	public String PatientName;
	public String Amount;
	public String Last_4_digits_CardNumber;
	public String Phone_Email;
	
	public Boolean printReceiptbutton;
	Actions act ;
	@FindBy(xpath="//input[@id='ManualEntry']")
	WebElement ManualEntry_Radiobtn;
		
	@FindBy(xpath="//input[@id='liquiddateofvisit']")
	WebElement dateofVisit;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/input[1]")
	WebElement referenceNumber;	

	@FindBy(name="liquidspecialist[]")
	WebElement speciality;
	
	@FindBy(name="liquidfirst[]")
	WebElement firstName;
	
	@FindBy(name="liquidlast[]")
	WebElement Lastname;
	
	@FindBy(name="liquidprocphone[]")
	WebElement phone;
	
	@FindBy(name="liquidprocamt[]")
	WebElement amount ;
	
	@FindBy(xpath="//input[@id='btnGet']")
	WebElement SubmitFormbtn;
	
	@FindBy(xpath="//input[@id='ClearFormBtn']")
	WebElement ClearFormbtn;
		
	@FindBy(name="CardNumber1")
	WebElement cardNumber ;
	
	@FindBy(name="ExpMonth1")
	WebElement Expmonth ;
	
	@FindBy(name="ExpYear1")
	WebElement ExpYear ;
	
	@FindBy(name="CardCvv1")
	WebElement CardCVV ;
	
	@FindBy(xpath="/html[1]/head/body[1]/div[6]/div[2]/div[1]/div[1]/div[1]/div[1]/div[4]/button[1]")
	WebElement Confirmbtn;
	
	@FindBy(xpath="//body/div[6]/div[2]/div[1]/div[1]/div[1]/div[1]/div[4]/button[2]")
	WebElement Cancelbtn;
	
	@FindBy(name="StreetAddress")
	WebElement address;
	
	@FindBy(name="City")
	WebElement city;
	
	@FindBy(name="State")
	WebElement state;
	
	@FindBy(name="ZipCode")
	WebElement zipCode;
	
	@FindBy(xpath="//b[contains(text(),'Success')]")
	WebElement Success;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")
	WebElement printReceipt;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[2]/img[1]")
	WebElement Backbtn;
	
	@FindBy(xpath="//button[contains(text(),'Retry')]")
	WebElement Retry;
	
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement Home;
	@FindBy(xpath="//a[contains(text(),'Declined')]")
	WebElement Status;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logout;
		
	public ManualEntryPaymentPage() throws IOException{
		
		PageFactory.initElements(driver,this);
		
	}
	
	
	public void manualEntrypayment(String RefNo,String FirstName1,String LastName1,String Phone_Email,String Amount,String CardNumber,String Exp_Month,String Exp_Year,String Cvv,String Address,String City,String State,String ZipCode ) throws InterruptedException, IOException, AWTException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		Home.click();
		Reporter.log("Navigated to Home Payments screen!");	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ManualEntry']")));
		ManualEntry_Radiobtn.click();
		Boolean ME=ManualEntry_Radiobtn.isSelected();
		Reporter.log("Manual Entry payment option selected!");	
		if(ME==true){
			dateofVisit.click();
			Util.getCurrentDate();
			WebElement date=driver.findElement(By.xpath("//a[contains(text(),'"+Util.currentdate+"')]"));
			date.click();			
			referenceNumber.sendKeys(RefNo);
			Reporter.log("Patient Reference Number entered successfully :"+RefNo);
			this.Ref_NO=RefNo; 
			firstName.sendKeys(FirstName1);
			Reporter.log("Patient First name entered successfully :"+FirstName1);
			Lastname.sendKeys(LastName1);
			Reporter.log("Patient Last Name entered successfully :"+LastName1);
			this.PatientName=FirstName1+" "+LastName1;
			phone.sendKeys(Phone_Email);
			Reporter.log("Phone/Email entered successfully :"+Phone_Email);
			this.Phone_Email=Phone_Email;
			amount.sendKeys(Amount);
			Reporter.log("Amount entered successfully : $"+Amount);
			this.Amount=Amount;
			Thread.sleep(2000);
			SubmitFormbtn.click();
			Thread.sleep(2000);
			Reporter.log("Card Details pop up opened successfully! ");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("CardNumber1")));	
			
			cardNumber.sendKeys(CardNumber);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ExpMonth1")));
			Expmonth.sendKeys(Exp_Month);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ExpYear1")));	
			ExpYear.sendKeys(Exp_Year);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("CardCvv1")));	
			CardCVV.sendKeys(Cvv);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("StreetAddress")));	
			address.sendKeys(Address);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("City")));	
			city.sendKeys(City);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("State")));	
			state.sendKeys(State);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ZipCode")));
			zipCode.sendKeys(ZipCode);	
			Reporter.log("Card Number :"+CardNumber+","+"Expiry Month/Year :"+Exp_Month+"/"+Exp_Year+","+" CVV :"+Cvv+ " details entered successully!");
			act= new Actions(driver);
			Thread.sleep(3000);			
			act.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
			act.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(3000);
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")));
			
			List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		
			for(WebElement link:allLinks){
				
					String s1=link.getText() + " - " + link.getAttribute("href");
				 	if(s1.contains(" - https://demo.liquid-payments.com/report/receipt/iTransactionId/")) {
				 		String PrintReceiptURL=link.getText() + " - " + link.getAttribute("href");
				 		System.out.println("ReceiptURL:"+PrintReceiptURL);
				 		    			 		 
				 		if (PrintReceiptURL.length() > 6) 
				 		{
				 			TransactionID = PrintReceiptURL.substring(PrintReceiptURL.length() - 6);
				 		} 
				 		else
				 		{
				 			TransactionID = PrintReceiptURL;
				 		}
				 		 this.TransactionID=TransactionID;
				 		 System.out.println("Transaction ID is:"+TransactionID);
				 		
				 	}
			 }	
			
			Boolean backtohome=Backbtn.isDisplayed();
			Reporter.log("Manual Entry payment Transaction has successful with Transaction Id:"+TransactionID);
			printReceiptbutton=printReceipt.isDisplayed();
			Robot robot=new Robot();
		
			if(backtohome==true) {				
				Thread.sleep(3000);
				Backbtn.click();				
			}
			
						
		}
		
					
	}
	
	public void logOut() {
			logout.click();
	}
	
}
