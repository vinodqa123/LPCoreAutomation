package com.lp.qa.pages;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class TransactionReportPage extends TestBase {
	
	int AuthorizeCount=0;
	int RefundCount=0;
	int RefundVoidcount=0;
	int AuthVoidCount=0;
	int DeclinedTrans=0;
	float AuthAmount=0;
	float RefundAmount=0;
	float RefundVoidAmount=0;
	float AuthVoidAmount=0;
	float NetAmount=0;
	ManualEntryPaymentPage ManualEntryPage;
	Alert alt;
	String TansactionType;
	
	@FindBy(xpath="//a[@id='report']")
	WebElement Report_Menu;
	
	@FindBy(id="btnTranReport")
	WebElement TransactionReport_Menu;
	
	@FindBy(xpath="//a[contains(text(),'ALL Transaction')]")
	WebElement All_Transaction_Menu;
	
	@FindBy(xpath="//a[contains(text(),'Visa')]")
	WebElement VisaCard_Menu;
	
	@FindBy(xpath="//a[contains(text(),'Master Card')]")
	WebElement MasterCard_Menu;
	
	@FindBy(xpath="//a[contains(text(),'Amex')]")
	WebElement AmexCard_Menu;
	
	@FindBy(xpath="//a[contains(text(),'Discover')]")
	WebElement DiscoverCard_Menu;
	
	@FindBy(xpath="//a[contains(text(),'JCB')]")
	WebElement JCBCard_Menu;
	
	@FindBy(xpath="//a[contains(text(),'Unknown')]")
	WebElement UnknownCards_Menu;
	
	@FindBy(xpath="//tbody/tr[9]/td[1]/input[1]")
	WebElement E_CommerceCheckBox;
	
	@FindBy(xpath="//input[@id='devicetype']")
	WebElement Device_Checkbox;
	
	@FindBy(xpath="//input[@id='AddMerchant']")
	WebElement RunMyReport;
	
	@FindBy(xpath="//input[@id='refundamount']")
	WebElement refundAmount;
	
	@FindBy(xpath="//select[@id='FundReson']")
	WebElement reasonDropDown;
	
	@FindBy(xpath="//input[@id='Submit']")
	WebElement refund_Void_ConfirmBtn;
	
	public  Boolean TransType;
	
	public TransactionReportPage() throws IOException{
		
		PageFactory.initElements(driver,this);
		
	}
	public TransactionReportPage navigateTransactionReport() throws InterruptedException, AWTException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();		
		Actions actions = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='report']")));
		actions.moveToElement(Report_Menu).moveToElement(TransactionReport_Menu).click().build().perform();	     
	    System.out.println("Navigated to Transaction Report menu");
	    Reporter.log("Navigated to Transaction Report menu!");
		return new TransactionReportPage();
	    
	}
	
	public void runTransactionReport() throws InterruptedException {
		Thread.sleep(3000);
		Boolean EcommerceCheckbox=E_CommerceCheckBox.isSelected();		
			RunMyReport.click();
			String parent=driver.getWindowHandle();
			Set<String>s=driver.getWindowHandles();
			Thread.sleep(2000);
			// Now iterate using Iterator
			Iterator<String> I1= s.iterator();

			while(I1.hasNext())
			{
				String child_window=I1.next();

				if(!parent.equals(child_window))
				{
					driver.switchTo().window(child_window);
					Reporter.log("Transaction Report has been ran Successfully!");			
					driver.navigate().back();
					
				}					
			}
				
		
	}
	public String verifyTransactionStatus(String refNo) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+refNo+"')]/preceding-sibling::td[2]/a[1]")));
		WebElement tranStatus=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/preceding-sibling::td[2]/a[1]"));
		String Status=tranStatus.getText();
		System.out.println("Transaction Status"+Status);
		Reporter.log("Transaction Status has found in Transaction Report grid with Status as : "+Status);
		return Status;
						
	}
	public String VerifyTransactionId(String refNo,String TransID) {
		WebElement TransactionID=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/preceding-sibling::td[4]"));
		String TransID1=TransactionID.getText();
		Reporter.log("Transaction Id found in Transaction Report as : "+TransID1);
		return TransID1;
		
	}
	public String verifyuserLoginID(String refNo){
		WebElement userName=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/preceding-sibling::td[9]"));
		String loginName=userName.getText();
		Reporter.log("User Login ID found in Transaction Report grid as : "+loginName);
		return loginName;
	}
	public String verifyPatientName(String refNo,String patientName) {
		WebElement patientNames=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/preceding-sibling::td[6]"));
		String patientName2=patientNames.getText();
		Reporter.log("Patient Name found in Report grid as : "+patientName2);
		System.out.println(patientNames);
	
		return patientName2;		
	}
	public String verifyTransactionAmount(String refNo,String Amount) throws InterruptedException {
		float originalAmount=Float.parseFloat(Amount);
		WebElement TranAmount=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/preceding-sibling::td[1]"));
		String TransReportAmtwith$=TranAmount.getText(); 
		String TransReportAmt=TransReportAmtwith$.substring(1);
		System.out.println(TransReportAmt);
		Reporter.log("Transaction Amount found in Report grid as : "+TransReportAmtwith$);
		return TransReportAmt;		
	}
	public String verifyTransactionCardNumber(String refNo ,String cardNumber) {
		WebElement cardNumber3=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[2]"));
		String cardNumberT=cardNumber3.getText();
		String Last_4_digits_CardNumber="";
		if (cardNumberT.length() > 4) 
 		{
			Last_4_digits_CardNumber = cardNumberT.substring(cardNumberT.length() - 4);
 		} 
 		else
 		{
 			Last_4_digits_CardNumber = cardNumberT;
  		}
		System.out.println("Given Card Number:"+cardNumber+" Transaction Report Card Number:"+Last_4_digits_CardNumber);
		Reporter.log("Given last 4 digtis of Card :"+cardNumber+" Transaction Report last 4-digits of Card : "+Last_4_digits_CardNumber);
		return Last_4_digits_CardNumber;	
		
	}
	public void verifyPartialRefund(String refNo ,String Amount) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[2]/a[1]/img[1]")));
		WebElement refundicon=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[2]/a[1]/img[1]"));
			refundicon.click();
			String pendingRefundAmount=refundAmount.getAttribute("value");
			float originalAmount=Float.parseFloat(Amount);
			float pendAmt=Float.parseFloat(pendingRefundAmount);
			float partialrefundAmt=pendAmt/2;
			System.out.println("PartialRefundAmount"+partialrefundAmt);
					if(partialrefundAmt<=originalAmount) {
						System.out.println("Pending Refund is less than original amount");
					//	refundAmount.sendKeys(pendingRefundAmount);
						Thread.sleep(3000);
						Select dropdown=new Select(reasonDropDown);
						dropdown.selectByIndex(2);					
						refund_Void_ConfirmBtn.click();	
						alt=driver.switchTo().alert();
						alt.accept();
						WebElement refundTrans=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[5]/td[8]"));
						
					}
			
	}
	public String verifyFullyRefund(String refNo ,String Amount) throws InterruptedException {
		String Status = null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[2]/a[1]/img[1]")));
		WebElement refund=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[2]/a[1]/img[1]"));
		if(refund.isDisplayed()) {
			refund.click();
			Reporter.log("Refund action icon click Success!");
			String pendingRefundAmount=refundAmount.getAttribute("value");
			System.out.println("pendingRefundAmount");
			//	refundAmount.sendKeys(pendingRefundAmount);
				Select dropdown=new Select(reasonDropDown);
				dropdown.selectByIndex(2);
				refund_Void_ConfirmBtn.click();	
				alt=driver.switchTo().alert();
				alt.accept();
				driver.navigate().refresh();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]")));
				WebElement TansType=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]"));
				TansactionType=TansType.getText();
				TransType=TansactionType.contains("Refund");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]")));				
				WebElement tranStatus=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]"));
				Status=tranStatus.getText();
				WebElement TransID=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[7]"));
				String TransactionID=TransID.getText();
				Reporter.log("Refund Transaction has been completed!,Refund Transaction ID is :"+TransactionID);
				System.out.println("Transaction Status"+tranStatus);
				System.out.println("Fully refund success");
				
			
		}
		return Status;
	}
	public String VerifyRefundVoid(String refNo) throws InterruptedException {
		String Status=null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[1]/a[1]/img[1]")));
		WebElement reundVoid=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[1]/a[1]/img[1]"));
			reundVoid.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FundReson']")));
			Select dropdown1=new Select(reasonDropDown);
			dropdown1.selectByIndex(2);
			refund_Void_ConfirmBtn.click();	
			alt=driver.switchTo().alert();
			alt.accept();
			driver.navigate().refresh();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]")));
			WebElement TansType=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]"));
			TansactionType=TansType.getText();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]")));				
			WebElement tranStatus=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]"));
			Status=tranStatus.getText();
			TransType=TansactionType.contains("RefundVoid");
			WebElement TransID=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[7]"));
			String TransactionID=TransID.getText();
			Reporter.log("Refund Void Transaction has been completed!,RefundVoid Transaction ID is :"+TransactionID);
			System.out.println("Transaction Status"+tranStatus);
			System.out.println("Refund Void success");
			return Status;
		
	}
	public String verifyAuthorizeVoid(String refNo) throws InterruptedException {
		String Status=null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[1]/a[1]/img[1]")));
		WebElement AuthVoid=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[1]/div[1]/a[1]/img[1]"));
			AuthVoid.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FundReson']")));
			Select dropdown2=new Select(reasonDropDown);
			dropdown2.selectByIndex(2);
			refund_Void_ConfirmBtn.click();	
			alt=driver.switchTo().alert();
			alt.accept();
			driver.navigate().refresh();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]")));
			WebElement TansType=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]"));
			TansactionType=TansType.getText();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]")));				
			WebElement tranStatus=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[9]/a[1]"));
			Status=tranStatus.getText();
			TransType=TansactionType.contains("AuthorizeVoid");
			WebElement TransID=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[7]"));
			String TransactionID=TransID.getText();
			Reporter.log("Authorize Void Transaction has been completed!,Authorize Void Transaction ID is :"+TransactionID);
			System.out.println("Transaction Status"+tranStatus);
			System.out.println("Authorize void success");
			Thread.sleep(2000);
			return Status;
			
	}
	public void printReceipt(String refNo) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[3]")));
		WebElement printreceipt=driver.findElement(By.xpath("//td[contains(text(),'"+refNo+"')]/following-sibling::td[5]/div[1]/div[3]"));
		printreceipt.click();
		Reporter.log("Print Receipt action clicked successfully!");
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		Thread.sleep(2000);
		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{
			String child_window=I1.next();

			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				Reporter.log("Print Receipt pop up opened successfully!");			
				driver.close();
				driver.navigate().back();
			}					
		}
	}
	public void settelmentReport() throws InterruptedException {
		
		int page=1;
		while(true) {
			List<WebElement> totalpages=driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[2]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td"));
			int totalpages1=totalpages.size()-2;
			System.out.println("Total Pages:"+totalpages1);
			
			//above syntax is used to identify total records in grid
			List<WebElement> rows = driver.findElements(By.xpath("//body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr")); 
			int totalrecords=rows.size()-1;
			System.out.println("Total records"+totalrecords);			
			for (int i=2; i<=rows.size();i++){
					WebElement TransactionType= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr["+i+"]/td[8]"));
					WebElement paymentType=driver.findElement(By.xpath("//tbody/tr["+i+"]/td[9]/a[1]/span[1]/b[4]"));
					String paymentType1=paymentType.getText();
					System.out.println(paymentType);
					String TranType=TransactionType.getText();
					System.out.println(TranType);
					WebElement TransactionAmount= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr["+i+"]/td[10]"));
					String TransReportAmtwith$=TransactionAmount.getText(); 
					String TransReportAmt=TransReportAmtwith$.substring(1);
					float TransactionAmt=Float.parseFloat(TransReportAmt);
					WebElement tranStatus=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr["+i+"]/td[9]/a[1]"));
					String TransacionStatus=tranStatus.getText();
					System.out.println("Transaction Status"+TransacionStatus);					
					System.out.println(TransactionAmt);
					if(TranType.equals("Authorize") && TransacionStatus.equals("Approved")){
						AuthorizeCount++;
						AuthAmount=AuthAmount + TransactionAmt;
					}else if(TranType.equals("Refund") && TransacionStatus.equals("Approved")){
						RefundCount++;
						RefundAmount= RefundAmount+ TransactionAmt;
					}else if(TranType.equals("RefundVoid") && TransacionStatus.equals("Approved")){
						RefundVoidcount++;
						RefundVoidAmount= RefundVoidAmount+ TransactionAmt;
					}else if(TranType.equals("AuthorizeVoid") && TransacionStatus.equals("Approved")){
						AuthVoidCount++;
						AuthVoidAmount = AuthVoidAmount + TransactionAmt;
					}
					else{
						System.out.println("Record no:"+i+" is declined or failed");
						DeclinedTrans++;
					}
				
			
					
			}
			if(page>=totalpages1) {
				break;
			}
			page++;
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[2]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td["+page+"]")));
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/table[2]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td["+page+"]")).click();
			Thread.sleep(2000);
		}
		float C1=AuthAmount+RefundVoidAmount;
		float C2=RefundAmount+AuthVoidAmount;
		NetAmount=C1-C2;		
		System.out.println("Authorize Trans.Count :"+AuthorizeCount);
		System.out.println("Auth Trans.Amount"+AuthAmount);
		System.out.println("Refund Trans.Count :"+RefundCount);
		System.out.println("Refund Trans.Amount"+RefundAmount);
		System.out.println("Refund void Tran.Count :"+RefundVoidcount);
		System.out.println("Refund Void Tran.Amount"+RefundVoidAmount);
		System.out.println("Auth Void Tran.Count :"+AuthVoidCount);
		System.out.println("Auth Void Tran.Amount"+AuthVoidAmount);
		System.out.println("Net Amount: "+NetAmount);
	}	
	
	public void printReceiptGrid() {				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[3]/span[2]/a[1]/img[1]")));
		WebElement printReceipt=driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/span[2]/a[1]/img[1]"));
		String parent=driver.getWindowHandle();
		printReceipt.click();
		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{
		String child_window=I1.next();

		if(!parent.equals(child_window))
		{
			driver.switchTo().window(child_window);
		
		}	
	
		
		}
		Reporter.log("Print Receipt grid level action clicked successfully!");
	}
	public void exportExcelGrid() throws InterruptedException {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/span[2]/a[2]/img[1]")));
		WebElement exportExcel=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/span[2]/a[2]/img[1]"));
		exportExcel.click();
		Thread.sleep(3000);
		Reporter.log("Export Excel file downloaded successfully!");
	}
	
}
