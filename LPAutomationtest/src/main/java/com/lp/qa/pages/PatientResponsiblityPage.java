package com.lp.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lp.qa.base.TestBase;
import com.lp.qa.util.Util;

public class PatientResponsiblityPage extends TestBase{

	@FindBy(id="PatientResponsibility")
	WebElement PatientResponsibilityRadioBtn;
	
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement HomeMenu;
	
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
	
	@FindBy(name="liquidprocinsamt[]")
	WebElement InsAmt;
	
	@FindBy(id="postadjudication")
	WebElement InsCheckbox;
	
	@FindBy(xpath="//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")
	WebElement backBtn;
	
	@FindBy(xpath="//input[@id='btnGet']")
	WebElement submitButton;
	
	public PatientResponsiblityPage() throws IOException{
		PageFactory.initElements(driver,this);
		
	}
	public Boolean patientRespWithoutInsurance(String RefNo,String FirstName,String LastName,String Phone_Email1,String Amount1) throws InterruptedException, AWTException{
		Boolean backtohome=null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		HomeMenu.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PatientResponsibility")));
		PatientResponsibilityRadioBtn.click();	
		Reporter.log("PatientResponsiblity payment option has been selected successfully!");
		Thread.sleep(2000);
		Boolean CP=PatientResponsibilityRadioBtn.isSelected();
		if(CP==true){
			dateofVisit.click();
			Util.getCurrentDate();
			WebElement date=driver.findElement(By.xpath("//a[contains(text(),'"+Util.currentdate+"')]"));
			date.click();			
			Thread.sleep(2000);
			InsCheckbox.click();
			Boolean InsCheckBox=InsCheckbox.isSelected();
			if(InsCheckBox!=true) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("liquidaccountnumber[]")));			
				referenceNumber.sendKeys(RefNo);
				firstName.sendKeys(FirstName);
				lastName.sendKeys(LastName);
			//	this.PatientName=FirstName1+" "+LastName1;
				Phone_Email.sendKeys(Phone_Email1);			
				Amount.sendKeys(Amount1);			
				Thread.sleep(2000);
				Reporter.log("PatientResponsiblity without insurance payment data has been entered successfully!");
				submitButton.click();
				WebDriverWait wait = new WebDriverWait(driver,30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")));			
				backtohome=backBtn.isDisplayed();
				Robot robot=new Robot();		
				if(backtohome==true) {
					 Thread.sleep(2000);
					 backBtn.click();
					 Reporter.log("PatientResponsiblity without insurance payment data has been entered successfully!");
					 driver.quit();
				}
			
			}
			
		}
		return backtohome;
	
	}
	public Boolean patientRespWithInsurance(String RefNo,String FirstName,String LastName,String Phone_Email1,String Amount1,String Ins_Amt) throws InterruptedException, AWTException{		
		Boolean backtohome=null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		HomeMenu.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PatientResponsibility")));
		PatientResponsibilityRadioBtn.click();
		Reporter.log("PatientResponsiblity payment option has been selected successfully!");
		Thread.sleep(2000);
		Boolean CP=PatientResponsibilityRadioBtn.isSelected();
		if(CP==true){
			dateofVisit.click();
			Util.getCurrentDate();
			WebElement date=driver.findElement(By.xpath("//a[contains(text(),'"+Util.currentdate+"')]"));
			date.click();			
			Thread.sleep(2000);
			Boolean InsCheckBox=InsCheckbox.isSelected();
			if(InsCheckBox==true) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("liquidaccountnumber[]")));			
				referenceNumber.sendKeys(RefNo);
				firstName.sendKeys(FirstName);
				lastName.sendKeys(LastName);
			//	this.PatientName=FirstName1+" "+LastName1;
				Phone_Email.sendKeys(Phone_Email1);			
				Amount.sendKeys(Amount1);
				InsAmt.sendKeys(Ins_Amt);	
				
				Thread.sleep(2000);
				submitButton.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='MainContainer']/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")));			
				backtohome=backBtn.isDisplayed();
				Robot robot=new Robot();		
				if(backtohome==true) {
					 Thread.sleep(2000);
					 backBtn.click();
					 Reporter.log("PatientResponsiblity payment with Insurance data entered successfully!");
					 driver.quit();
				}
			
			}
			
		}
		return backtohome;
	
	}
}
