package com.lp.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lp.qa.base.TestBase;
import com.lp.qa.extentreport.ExtentReport;
import com.lp.qa.pages.HomePaymentsPage;
import com.lp.qa.pages.LoginPage;
import com.lp.qa.pages.ManualEntryPaymentPage;
import com.lp.qa.pages.TransactionReportPage;
import com.lp.qa.testdata.TestData;
import com.relevantcodes.extentreports.LogStatus;


public class ManualEntryPaymentsTest extends TestBase {
	LoginPage loginPage;
	ManualEntryPaymentPage manaulEntryPage;
	HomePaymentsPage Homepage;
	TransactionReportPage transactionReportPage;
	ManualEntryPaymentsTest manualEntry;
	String Last_4_digits_CardNumber;
	String RefNo;
	String Amount;
	String PatientName;
	String Phone_Email;
	String TransID;
	public ManualEntryPaymentsTest() throws IOException {
		super();
	}
	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		System.out.println("Manual Entry Before Class");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
			
	} 
	
	@DataProvider(name="manaulentry")
	public Object[][] getManaulEntryPaymentData() throws IOException {
		Object[][] data=TestData.getManaulEntryPaymentData();
		return data ;
								
	}
		
	@Test(dataProvider="manaulentry")
	public void TC01_ME_Transaction(String TestCase,String RefNo,String FirstName,String LastName,String Phone_Email,String Amount,String CardNumber,String Exp_Month,String Exp_Year,String Cvv,String Address,String City,String State,String ZipCode) throws InterruptedException, IOException, AWTException {	
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		manaulEntryPage.manualEntrypayment(RefNo,FirstName,LastName,Phone_Email,Amount,CardNumber,Exp_Month,Exp_Year,Cvv,Address,City,State,ZipCode);
		this.RefNo=RefNo;
		this.Amount=Amount;
		this.PatientName=FirstName+" "+LastName;
		this.Phone_Email=Phone_Email;		
		TransID=manaulEntryPage.TransactionID;
		
		if (CardNumber.length() > 4) 
 		{
			Last_4_digits_CardNumber = CardNumber.substring(CardNumber.length() - 4);
 		} 
 		else
 		{
 			Last_4_digits_CardNumber = CardNumber;
 		}
		Assert.assertTrue(manaulEntryPage.printReceiptbutton);
		driver.quit();
		 		  		
	} 
		
	@Test()
	public void TC02_ME_verifyTransactionStatus() throws InterruptedException, IOException, AWTException {
		System.out.println("In TC02_ME_verifyTransactionStatus");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String TransactionStatus=transactionReportPage.verifyTransactionStatus(RefNo);
	//	String TransactionStatus=transactionReportPage.verifyTransactionStatus("ME 423");
		System.out.println("Transation Status :"+TransactionStatus);
		Assert.assertEquals(TransactionStatus, "Approved");
		driver.quit();
	}
	@Test()
	public void TC03_ME_verifyTransactionAmount( ) throws InterruptedException, IOException, AWTException {
		System.out.println("In TC03_ME_verifyTransactionAmount");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String TransactionAmt=transactionReportPage.verifyTransactionAmount(RefNo,Amount);
		float originalAmt=Float.parseFloat(Amount);
		Assert.assertEquals(TransactionAmt,Amount);
		driver.quit();
	}
	@Test()
	public void TC04_ME_verifyTransactionPatientName() throws InterruptedException, IOException, AWTException {
		System.out.println("In TC06_ME_verifyTransactionPatientName");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String PatientName1=transactionReportPage.verifyPatientName(RefNo,PatientName);
		Assert.assertEquals(PatientName1,PatientName);
		System.out.println("Patient Name :"+PatientName1);
		//verifyPatientName(manaulEntryPage.Ref_NO,manaulEntryPage.PatientName);
		driver.quit();
	}
	@Test()
	public void TC07_ME_verifyTransactionUserLoginID() throws InterruptedException, IOException, AWTException {
		System.out.println("In TC07_ME_verifyTransactionUserLoginID");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String loginName=transactionReportPage.verifyuserLoginID(RefNo);
		System.out.println("User Login ID :"+loginName);
		Assert.assertEquals(loginName,prop.getProperty("username"));
		driver.quit();
	}
	@Test()
	public void TC08_ME_verifyTransactionCardNumber() throws InterruptedException, IOException, AWTException {
		System.out.println("TC08_ME_verifyTransactionCardNumber");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String CardNumber= transactionReportPage.verifyTransactionCardNumber(RefNo,Last_4_digits_CardNumber);
		System.out.println("Card Number :"+CardNumber);
		Assert.assertEquals(CardNumber,Last_4_digits_CardNumber);
		driver.quit();
	}
	@Test()
	public void TC09_ME_verifyTransactionID() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String TransactionID=transactionReportPage.VerifyTransactionId(RefNo,TransID); //277855
	//	String TransactionID=transactionReportPage.VerifyTransactionId("ME 423","277855");
		System.out.println("Transaction id in Transaction Report :"+TransactionID);
		Assert.assertEquals(TransactionID,TransID);
		driver.quit();
		
	}
	@Test()
	public void TC10_ME_verifyRefund() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String refundStatus=transactionReportPage.verifyFullyRefund(RefNo,Amount); // ME 776666 3.12
	//	String refundStatus=transactionReportPage.verifyFullyRefund("ME 776666","3.12");
		if(transactionReportPage.TransType && refundStatus.contentEquals("Approved")) {
			Assert.assertEquals(refundStatus,"Approved");
		}
		else {
			Assert.assertTrue(transactionReportPage.TransType!=true, "Refund not happenned!");
		}
		
		driver.quit();
				
		
	}
	
	@Test()
	public void TC11_ME_RefundVoid() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		Thread.sleep(3000);
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String refundVoidTransStatus=transactionReportPage.VerifyRefundVoid(RefNo);
		if(transactionReportPage.TransType==true||refundVoidTransStatus.contentEquals("Approved")) {
			Assert.assertEquals(refundVoidTransStatus,"Approved");
		}
		else {
			Assert.assertTrue(transactionReportPage.TransType!=true, "Refund Void not happenned!");
		}
		driver.quit();
	}
		
	
	@Test()
	public void TC12_ME_verifyAuthVoid() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String AuthVoidTransStatus=transactionReportPage.VerifyRefundVoid(RefNo);
		if(transactionReportPage.TransType==true||AuthVoidTransStatus.contentEquals("Approved")) {
			Assert.assertEquals(AuthVoidTransStatus,"Approved");
		}
		else {
			Assert.assertTrue(transactionReportPage.TransType!=true, "Authorize Void Void not happenned!");
		}
		driver.quit();
		
	} 
	@Test()
	public void TC13_ME_verifyGridExportIcon() throws InterruptedException, AWTException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		transactionReportPage.exportExcelGrid();
		driver.quit();
		
	}
	@Test()
	public void TC14_ME_verifyGridRecieptIcon() throws InterruptedException, AWTException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		transactionReportPage.printReceiptGrid();
		driver.quit();
	} 
	@Test()
	public void TC15_ME_verifySettlementReprot() throws InterruptedException, AWTException, IOException {
		browserInitialization();
		Thread.sleep(3000);
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		manaulEntryPage=new ManualEntryPaymentPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);		
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		transactionReportPage.settelmentReport();
		driver.quit(); 
	}
	
	@AfterClass()
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(4000);
			driver.quit();  
	} 
	

}
