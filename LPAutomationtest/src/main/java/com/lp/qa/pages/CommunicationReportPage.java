package com.lp.qa.pages;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.lp.qa.base.TestBase;

public class CommunicationReportPage extends TestBase {
	public static String FinalURL=null;
	@FindBy(xpath="//a[@id='report']")
	WebElement Report_Menu;
	
	@FindBy(xpath="//a[contains(text(),'Communication Report')]")
	WebElement CommunicationMenu;
	
	@FindBy(xpath="//input[@id='AddMerchant']")
	WebElement runMyReport;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/input[1]")
	WebElement PatientName;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/input[1]")
	WebElement phone_email;
	
	@FindBy(xpath="//input[@id='orginalamount']")
	WebElement Amount;
	
	@FindBy(xpath="//body/div[1]/div[1]/span[1]/a[1]/img[1]")
	WebElement print;
	
	@FindBy(xpath="//body/div[1]/div[1]/span[1]/a[2]/img[1]")
	WebElement exportExcel;
	
	@FindBy(xpath="//button[@id='submit']")
	WebElement Proceed;
	
	public static String paymentType=null;	
	
	public CommunicationReportPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	public void navigateToCommunicationReport() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();	
		Actions actions = new Actions(driver);
		Thread.sleep(5000);	     
		actions.moveToElement(Report_Menu).moveToElement(CommunicationMenu).click().build().perform();	     
	    Thread.sleep(2000);
	    System.out.println("Navigated to Communication Report menu");
	    Reporter.log("Navigated to Communication Report Menu!");
	   
	}
	public void runCommunicationReport() throws InterruptedException {
		Thread.sleep(3000);
		runMyReport.click();
		Thread.sleep(3000);
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{
			String child_window=I1.next();

			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				 Reporter.log("Communication Report has been ran successfully!");
			}		
		
		}
			
		WebElement report=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/h1[1]/strong[1]/b[1]"));
	    String CommReport=report.getText();
	    Assert.assertEquals(CommReport,"Liquid Payments - Communication Report");	
		
	}	
	public Boolean resendSMSEmail(String RefNo) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[1]/div[1]/span[1]/img[1]")));
		WebElement resendICon=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[1]/div[1]/span[1]/img[1]"));		
		resendICon.click();
		Reporter.log("Resend SMS/Email pop up window opened successfully!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='orginalamount']")));
		Amount.clear();
		Amount.sendKeys("5.1");
		Proceed.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Re-sent Successfully')]")));
		WebElement resendSuccess=driver.findElement(By.xpath("//div[contains(text(),'Re-sent Successfully')]"));
		Boolean resendSuccessMessage=resendSuccess.isDisplayed();
		Reporter.log("SMS/Email has been resent successfully!");
		return resendSuccessMessage;
			
	}
	public String verifyTransactionStatus(String RefNo) {
		WebElement userLogin=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[9]"));
		String TransStatus=userLogin.getText();
		Reporter.log("Payment transaction status as :"+TransStatus);
		
		return TransStatus;
				
	}
	public Float verifyAmount(String RefNo,String Amount) {
		WebElement Amount1=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[4]"));
		String Amountwith$=Amount1.getText();
		String originalAmount=Amountwith$.substring(1);
		float Amount2=Float.parseFloat(originalAmount);
		Reporter.log("Payment amount found in Communication Report as :"+Amountwith$);
		//Assert.assertEquals(originalAmount,Amount);
		return Amount2;
		
		
	}
	public String verifyUserLoginID(String RefNo,String Amount) {
		WebElement userLogin=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//preceding-sibling::td[2]"));
		String UserLoginID=userLogin.getText();
		Reporter.log("User Login Id found in Communication Report as :"+UserLoginID);
		return UserLoginID;
				
		
	}
	public String verifyProviderName(String RefNo) {
		WebElement providerName=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//preceding-sibling::td[1]"));
		String ProviderName=providerName.getText();
		Reporter.log("Provider Name found in Communication Report as :"+ProviderName);
		
		return ProviderName;
		
	}
	
	public Boolean getCollectCardPaymentlink(String RefNo) throws InterruptedException, AWTException {
		
		Boolean paymentLink=null;
		WebElement Type=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[6]"));
		WebElement transStatus=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[9]"));
		WebElement phone_Email1=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[3]"));
		String Phone_email2=phone_Email1.getText();
		System.out.println("Phone/Email:"+Phone_email2);
		paymentType=Type.getText();
		
		System.out.println("Payment Type:"+paymentType);
		String TransactionStatus=transStatus.getText();
		System.out.println("Transaction Status:"+TransactionStatus);
	
		if(paymentType.equalsIgnoreCase("Contactless") &&TransactionStatus.equalsIgnoreCase("Not Paid") ) {
			WebElement seeMsgSentIcon=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[1]/div[2]/span[1]/img[1]"));
			seeMsgSentIcon.click();	
			Actions actions = new Actions(driver);
			Thread.sleep(5000);	 					
			System.out.println("See Msg Sent icon cliked");
			if(Phone_email2.contains("@")) {
					WebElement URL=driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));															
					String FinalURL1=URL.getAttribute("href");
					System.out.println(FinalURL1);
					this.FinalURL=FinalURL1;
					paymentLink=FinalURL.isBlank();
					Reporter.log("CollectCard Payment url found as :"+FinalURL);
											
			}else {
				WebElement GetPaymentURL=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/span[1]"));
				String PaymentUrl=GetPaymentURL.getText();
				String separator =":";
				int sepPos = PaymentUrl.indexOf(separator);
				String URL=PaymentUrl.substring(sepPos + separator.length());
				System.out.println("Substring after separator = "+PaymentUrl.substring(sepPos + separator.length()));
				String FinalURL1="https:"+URL;
				System.out.println("URL:"+FinalURL1);
				this.FinalURL=FinalURL1;
				paymentLink=FinalURL.isBlank();
				Reporter.log("CollectCard Payment url found as :"+FinalURL);
			}
		}else if(paymentType.equals("Patient Responsibility") && TransactionStatus.equalsIgnoreCase("Not Paid")){
			WebElement seeMsgSentIcon=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[1]/div[2]/span[1]/img[1]"));
			seeMsgSentIcon.click();	
			if(Phone_email2.contains("@")) {
				WebElement URL=driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));															
					String	FinalURL1=URL.getAttribute("href");
					System.out.println(FinalURL1);
					this.FinalURL=FinalURL1;
					paymentLink=FinalURL.isBlank();
					Reporter.log("CollectCard Payment url found as :"+FinalURL);
											
			}else {
				WebElement GetPaymentURL=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/span[1]"));
				String PaymentUrl=GetPaymentURL.getText();
				String separator =":";
				int sepPos = PaymentUrl.indexOf(separator);
				String URL=PaymentUrl.substring(sepPos + separator.length());
				System.out.println("Substring after separator = "+PaymentUrl.substring(sepPos + separator.length()));
				String FinalURL1="https:"+URL;
				System.out.println("URL:"+FinalURL1);
				this.FinalURL=FinalURL1;
				paymentLink=FinalURL.isBlank();
				Reporter.log("CollectCard Payment url found as :"+FinalURL);
			}
		}else if(paymentType.equals("Recurring Billing") &&TransactionStatus.equalsIgnoreCase("Not Paid")) {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[1]/span[1]/img[1]")));
			WebElement seeMsgSentIcon=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[1]/span[1]/img[1]"));
			seeMsgSentIcon.click();	
			if(Phone_email2.contains("@")) {
				Thread.sleep(5000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Click Here')]")));
				WebElement URL=driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));															
				String FinalURL1=URL.getAttribute("href");
				System.out.println(FinalURL1);
				this.FinalURL=FinalURL1;
				paymentLink=FinalURL.isBlank();
				Reporter.log("CollectCard Payment url found as :"+FinalURL);
				
										
			}else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/span[1]")));
				WebElement GetPaymentURL=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/span[1]"));
				String PaymentUrl=GetPaymentURL.getText();
				int i1=PaymentUrl.indexOf('?');
				String url1=PaymentUrl.substring(i1);
				String url2=url1.substring(0, 45);
				String FinalURL1="https://apisandbox.liquid-payments.com/collectcard/payment/verifycard"+url2;
				System.out.println("FInal URL:"+FinalURL1);
				this.FinalURL=FinalURL1;
				paymentLink=FinalURL.isBlank();
				Reporter.log("CollectCard Payment url found as :"+FinalURL);
			}
		
		}
		return paymentLink;
		
	}
	public void printReportOfCommReport() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/span[1]/a[1]/img[1]")));
		WebElement print=driver.findElement(By.xpath("//body/div[1]/div[1]/span[1]/a[1]/img[1]"));
		print.click();
		Reporter.log("Print report icon clicked successfully! ");
	
		
	}

	public void ExportExcelReportOfCommReport() {
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/span[1]/a[2]/img[1]]")));
	WebElement excel=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/span[1]/a[2]/img[1]"));
	excel.click();
	Reporter.log("Export Excel of Communication downloaded successfully! ");
		
	}
	public String viewTransactionInCommReport(String RefNo) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[2]/div[1]/span[1]/img[1]")));
		WebElement viewTransIcon=driver.findElement(By.xpath("//td[contains(text(),'"+RefNo+"')]//following-sibling::td[10]/div[2]/div[1]/span[1]/img[1]"));
		viewTransIcon.click();
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Authorize')]")));
		WebElement tranStatus=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]"));
		String Status=tranStatus.getText();
		System.out.println("Transaction Status"+Status);
		Reporter.log("Transaction Status has found in Transaction Report grid with Status as : "+Status);
		return Status;
	}
	
	
}
