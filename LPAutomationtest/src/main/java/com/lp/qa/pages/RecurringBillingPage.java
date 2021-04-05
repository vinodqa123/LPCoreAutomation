package com.lp.qa.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.lp.qa.base.TestBase;
import com.lp.qa.util.Util;

public class RecurringBillingPage extends TestBase {

	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement HomeMenu;
	
	@FindBy(xpath="//input[@id='RecurringPatientResponsibility']")
	WebElement RecurringRadioBtn;
	
	@FindBy(xpath="//input[@id='date-of-service']")
	WebElement dateOfService;
	
	@FindBy(xpath="//input[@id='patient-name']")
	WebElement patientName;
	
	@FindBy(xpath="//input[@id='mo-ph']")
	WebElement phone;
	
	@FindBy(xpath="//input[@id='email_rec']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='p-ref-no']")
	WebElement refNo;
	
	@FindBy(xpath="//input[@id='card_av']")
	WebElement radio_btn_cardOFile_Yes;
	
	@FindBy(xpath="//select[@id='saved_cards']")
	WebElement selectCard_dropDown;
	
	@FindBy(xpath="//input[@id='rec_amount']")
	WebElement ToalRecAmount;
	
	@FindBy(xpath="//select[@id='fre_lists']")
	WebElement PaymentFrequency_dropdown;
	
	@FindBy(xpath="//input[@id='period_rec']")
	WebElement toalPayments;
	
	@FindBy(xpath="//input[@id='start_date']")
	WebElement startDate;
	
	@FindBy(xpath="//input[@id='rec_pay_amount']")
	WebElement recurringPaymentAmount;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[4]/div[2]/label[2]/input[1]")
	WebElement cardEntryByPatient_radioBtn;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[4]/div[2]/label[3]/input[1]")
	WebElement cardEntryByProvider_radioBtn;	
	
	@FindBy(xpath="//input[@id='btnGet']")
	WebElement SubmitBtn;
	
	@FindBy(xpath="//button[@id='btsubmit']")
	WebElement ConfirmBtn;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[3]/form[1]/div[3]/div[1]/div[2]/div[1]/a[1]/img[1]")
	WebElement printBtn;
	
	@FindBy(xpath="//button[@id='btcancel']")
	WebElement CancelBtn;
	
	@FindBy(xpath="//input[@id='ClearFormBtn']")
	WebElement ClearBtn;
	
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
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")
	WebElement backBtn;
	
	public RecurringBillingPage() throws IOException{
		
		PageFactory.initElements(driver,this);
		
	}
		
	public void recCardEntryByPatient(String patientName1,String phone1,String email1,String RefNo,String RecAmt,String FrequencyType,String toalPayments1,String recDate ) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		System.out.println("Hi, I am in Card Entry by Patient Method");
		HomeMenu.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='RecurringPatientResponsibility']")));
		RecurringRadioBtn.click();
		Boolean RB=RecurringRadioBtn.isSelected();
		Reporter.log("Recurring Billing Payment option has selected!");
		if(RB==true){
			dateOfService.click();
			Util.getCurrentDate();
			WebElement date=driver.findElement(By.xpath("//a[contains(text(),'"+Util.currentdate+"')]"));
			date.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='patient-name']")));
			patientName.sendKeys(patientName1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mo-ph']")));
			phone.sendKeys(phone1);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email_rec']")));
			email.sendKeys(email1);
			refNo.sendKeys(RefNo);
			cardEntryByPatient_radioBtn.click();
			Reporter.log("Card Entry by Patient option has selected!");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='rec_amount']")));
			ToalRecAmount.sendKeys(RecAmt);
			Select freq=new Select(PaymentFrequency_dropdown);
			freq.selectByVisibleText(FrequencyType);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='period_rec']")));
			toalPayments.sendKeys(toalPayments1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='start_date']")));
			startDate.click();
		
			Util.getRecDate(recDate);
			WebElement year=driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/span[2]"));
			String recYear=year.getText();
			WebElement month=driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/span[1]"));
			String recMonth=month.getText();
			System.out.println("Month from portal: "+recMonth);
			System.out.println("Year from portal: "+recYear);
			Boolean month1=recMonth.contains(Util.recStartMonth);
			if(Util.recStartYear!=recYear && month1==false){
				while(true) {
					System.out.println("In While loop");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
					driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
					WebElement year2=driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/span[2]"));
					String recYear2=year2.getText();
					WebElement month2=driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/span[1]"));
					String recMonth2=month2.getText();
					recYear=recYear2;
					recMonth=recMonth2;
					Thread.sleep(1000);
					if(recYear.contains(Util.recStartYear) &&  recMonth.contains(Util.recStartMonth)){
						Thread.sleep(2000);
						WebElement recdate=driver.findElement(By.xpath("//a[contains(text(),'"+Util.recStartDate+"')]"));
						recdate.click();
						break;
					}
				}
			}else {
				WebElement recdate=driver.findElement(By.xpath("//a[contains(text(),'"+Util.recStartDate+"')]"));
				recdate.click();
			} 
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btnGet']")));
			SubmitBtn.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btsubmit']")));
			ConfirmBtn.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")));
			Boolean backtohome=backBtn.isDisplayed();
			if(backtohome==true) {				
				Thread.sleep(3000);
				backBtn.click();
				Reporter.log("Recurring Billing Verify Card link sent to phone/Email");
			}	
		
		}	
		
	}
	public void recCardEntryByProvider(String patientName1,String phone1,String email1,String RefNo,String RecAmt,String FrequencyType,String toalPayments1,String recDate,String CardNumber,String Exp_Month,String Exp_Year,String Cvv,String Address,String City,String State,String ZipCode ) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		HomeMenu.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='RecurringPatientResponsibility']")));
		RecurringRadioBtn.click();
		Boolean RB=RecurringRadioBtn.isSelected();
		if(RB==true){
			dateOfService.click();
			Util.getCurrentDate();
			WebElement date=driver.findElement(By.xpath("//a[contains(text(),'"+Util.currentdate+"')]"));
			date.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='patient-name']")));
			patientName.sendKeys(patientName1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mo-ph']")));
			phone.sendKeys(phone1);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email_rec']")));
			email.sendKeys(email1);
			refNo.sendKeys(RefNo);
			cardEntryByProvider_radioBtn.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='rec_amount']")));
			ToalRecAmount.sendKeys(RecAmt);
			Select freq=new Select(PaymentFrequency_dropdown);
			freq.selectByVisibleText(FrequencyType);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='period_rec']")));
			toalPayments.sendKeys(toalPayments1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='start_date']")));
			startDate.click();		
			Util.getRecDate(recDate);
			WebElement year=driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/span[2]"));
			String recYear=year.getText();
			WebElement month=driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/span[1]"));	
			String recMonth=month.getText();
			System.out.println("Month from portal: "+recMonth);
			System.out.println("Year from portal: "+recYear);
			Boolean month1=recMonth.equalsIgnoreCase(Util.recStartMonth);
			if(Util.recStartYear!=recYear && month1==false){
				while(true) {
					System.out.println("In While loop");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
					driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
					WebElement year2=driver.findElement(By.xpath("/html[1]/body[1]/div[4/div[1]/div[1]/span[2]"));
					String recYear2=year2.getText();
					WebElement month2=driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/span[1]"));
					String recMonth2=month2.getText();
					recYear=recYear2;
					recMonth=recMonth2;
					Thread.sleep(1000);
					if(recYear.contains(Util.recStartYear) &&  recMonth.contains(Util.recStartMonth)){
						Thread.sleep(2000);
						WebElement recdate=driver.findElement(By.xpath("//a[contains(text(),'"+Util.recStartDate+"')]"));
						recdate.click();
						break;
					}
				}
			}else {
				WebElement recdate=driver.findElement(By.xpath("//a[contains(text(),'"+Util.recStartDate+"')]"));
				recdate.click();
			} 
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btnGet']")));
			SubmitBtn.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btsubmit']")));
			ConfirmBtn.click();
		
			Thread.sleep(2000);
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
		
			Actions act= new Actions(driver);
			Thread.sleep(3000);			
			act.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
			act.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(3000);
					
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")));
			Boolean backtohome=backBtn.isDisplayed();
			if(backtohome==true) {				
				Thread.sleep(3000);
				backBtn.click();				
			}	
		
		}	
		
		
		
	}
	public Float verifySingleRecurringAmount() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rec_pay_amount")));
		WebElement recurringPayAmount=driver.findElement(By.id("rec_pay_amount"));
		String recAmount=recurringPayAmount.getAttribute("rec_pay_amount");
		Float SingleRecAmount=Float.parseFloat(recAmount);
		Reporter.log("Single Recurring Amount is: $"+SingleRecAmount);
		return SingleRecAmount;
	}
	public String verifyStartDateInScheduleRecurringPopUp() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='sdate']")));
		WebElement sd=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[6]/div[1]/div[3]/form[1]/div[3]/div[1]/div[2]/div[1]/span[1]/input[1]"));
		String startDate=sd.getText();
		System.out.println("Recurring start date in Schedule Recurring Pop up is : "+startDate);
		Reporter.log("Recurring start date in Schedule Recurring Pop up is : "+startDate);
		return startDate;
				
	}
	public String verifyEndDateInScheduleRecurringPopUp() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='edate']")));
		WebElement ed=driver.findElement(By.xpath("//input[@id='edate']"));
		String endDate=ed.getText();
		Reporter.log("Recurring End date in Schedule Recurring Pop up is : "+endDate);
		return endDate;
				
	}
	public Float verifyTotalAmountDueInScheduleRecurringPopUp() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='totalAmount']")));
		WebElement totalAmount=driver.findElement(By.xpath("//input[@id='totalAmount']"));
		String totalAmount1=totalAmount.getText();
		Float totalAmountDue=Float.parseFloat(totalAmount1);
		Reporter.log("Total Amount Due in Schedule Recurring Pop up is : "+totalAmountDue);
		return totalAmountDue;
				
	}

	
}
	
	


