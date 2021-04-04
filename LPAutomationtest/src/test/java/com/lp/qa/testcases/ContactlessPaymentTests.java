package com.lp.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lp.qa.base.TestBase;
import com.lp.qa.pages.CollectCardPage;
import com.lp.qa.pages.CommunicationReportPage;
import com.lp.qa.pages.ContactLessPage;
import com.lp.qa.pages.LoginPage;
import com.lp.qa.pages.ManualEntryPaymentPage;
import com.lp.qa.pages.TransactionReportPage;

import com.lp.qa.testdata.TestData;

public class ContactlessPaymentTests extends TestBase{
	LoginPage loginPage;
	ManualEntryPaymentPage manaulEntryPage;
	TransactionReportPage transactionReportPage;
	ContactLessPage contactLessPage;
	CommunicationReportPage communicationReportPage;
	CollectCardPage collectcardpage;
	public String RefNo;
	public String Amount;
	public String Phone_Email;
	public String LastName;
	public String FirstName;
	public String PatientName;
		
	String Last_4_digits_CardNumber;
	public ContactlessPaymentTests() throws IOException {
		super();
	}
	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		System.out.println("Before Class in CA");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	
	}
	
	@DataProvider(name="contactless")
	public Object[][] getContactlessPatientData() throws IOException {
		Object[][] data=TestData.getContactLessPaymentData();
		return data ;								
	}
		
	@Test(dataProvider="contactless")
	public void TC01_CA_ContactlessPatientPaymentForm(String TestCase,String RefNo,String FirstName,String LastName,String Phone_Email,String Amount) throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Boolean backToHomebtn=contactLessPage.ContactlessPayment(RefNo,FirstName,LastName,Phone_Email,Amount);
		this.RefNo=RefNo;
		this.Amount=Amount;
		this.FirstName=FirstName;
		this.LastName=LastName;
		String PatientName1=FirstName+" "+LastName;
		this.PatientName=PatientName1;
		Assert.assertTrue(backToHomebtn);
		driver.quit();
	} 
	@Test
	public void TC02_CA_VerifyContaclessPatientAmountInCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		float ReportAmount=communicationReportPage.verifyAmount(RefNo, Amount);
		float originalAmount=Float.parseFloat(Amount);
		Assert.assertEquals(ReportAmount,originalAmount);
		driver.quit();
	}
	@Test
	public void TC03_CA_VerifyUserLoginIDInCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		collectcardpage=new CollectCardPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		String UserLoginID=communicationReportPage.verifyUserLoginID(RefNo, Amount);
		Assert.assertEquals(UserLoginID, prop.getProperty("username"));
		
		
	} 
	@Test
	public void TC04_CA_verifyProviderNameInCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		collectcardpage=new CollectCardPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(3000);
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		String ProviderName=communicationReportPage.verifyProviderName(RefNo);
		Assert.assertEquals(ProviderName,"Heartland EMail");
		driver.quit();
	
	} 
	@Test
	public void TC05_CA_verifyResendSMSEmailFunInCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		Boolean resendStatus=communicationReportPage.resendSMSEmail(RefNo);
		Assert.assertTrue(resendStatus);
		driver.quit();
		
	}
	
	@Test
	public void TC06_CA_VerifyContaclessPaymentPaymentlink() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		Boolean Paymentlink=communicationReportPage.getCollectCardPaymentlink(RefNo);
		Assert.assertFalse(Paymentlink);
			
	}
	@Test
	public void TC07_CA_InitiateContactLessPaymentWithCollectCardLink() throws InterruptedException, IOException {
		browserInitialization();
		collectcardpage=new CollectCardPage();
		Boolean payment=collectcardpage.collectCardPayment(CommunicationReportPage.FinalURL,Amount,CommunicationReportPage.paymentType);
	//	Boolean payment=collectcardpage.collectCardPayment("https://demo.liquid-payments.com/collectcard/?reference=48471;","5.54","Contactless");
		Assert.assertTrue(payment);
		driver.quit();

	}
	
	@Test()
	public void TC08_CA_verifyContactLessPaymentTransStatusInTransactionReport() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		Thread.sleep(2000);
		String TransactionStatus= transactionReportPage.verifyTransactionStatus(RefNo);
		Assert.assertEquals(TransactionStatus, "Approved");
		driver.quit();
	}
	@Test()
	public void TC11_CA_verifyContactlessPaymentAmountInTransReport( ) throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String TransactionAmt=transactionReportPage.verifyTransactionAmount(RefNo,Amount);
		Assert.assertEquals(TransactionAmt,Amount);
		driver.quit();
	}
	@Test()
	public void TC12_CA_verifyContactlessPaymentPatientNameInTransReport() throws InterruptedException, IOException, AWTException{
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String PatientName1=transactionReportPage.verifyPatientName(RefNo,PatientName);
		Assert.assertEquals(PatientName1,PatientName);
		driver.quit();
		
	}
	@Test()
	public void TC13_CA_verifyTransactionUserLoginIDInTransReport() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String loginName=transactionReportPage.verifyuserLoginID(RefNo);
		Assert.assertEquals(loginName,prop.getProperty("username"));
		driver.quit();
	}
	@Test()
	public void TC14_CA_verifyContactlessPaymentTransCardNumberInTransReprot() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String card=transactionReportPage.verifyTransactionCardNumber(RefNo,"4242");
		Assert.assertEquals(card,"4242");
		driver.quit();
	}
	
	@Test()
	public void TC15_CA_verifyRefundForContactlessPaymentTransaction() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String refundStatus=transactionReportPage.verifyFullyRefund(RefNo,Amount);
		if(transactionReportPage.TransType && refundStatus.contentEquals("Approved")) {
			Assert.assertEquals(refundStatus,"Approved");
		}
		else {
			Assert.assertTrue(transactionReportPage.TransType!=true, "Refund not happenned!");
		}
		driver.quit();
	}
		
	@Test()
	public void TC16_CA_verifyRefundVoidForContactlessPaymentTransaction() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
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
	public void TC17_CA_verifyAuthorizeVoidForContactlessPaymentTransaction() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(2000);
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String AuthVoidTransStatus=transactionReportPage.verifyAuthorizeVoid(RefNo);
		if(transactionReportPage.TransType==true||AuthVoidTransStatus.contentEquals("Approved")) {
			Assert.assertEquals(AuthVoidTransStatus,"Approved");
		}
		else {
			Assert.assertTrue(transactionReportPage.TransType!=true, "Authorize Void Void not happenned!");
		}
		driver.quit();
	} 
	@Test()
	public void TC18_CA_printReportOfCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		communicationReportPage.printReportOfCommReport();
		driver.quit();
	}
	@Test()
	public void TC19_CA_ExportExcelOfCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		communicationReportPage.ExportExcelReportOfCommReport();
		driver.quit();
	}
	@Test()
	public void TC20_CA_ViewTransactionsIconInCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		contactLessPage=new ContactLessPage();
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		String status=communicationReportPage.viewTransactionInCommReport(RefNo);
		Assert.assertEquals(status, "Approved");
		driver.quit();
	}
	@AfterClass()
	public void closeBrowser() throws InterruptedException {
		driver.quit();  
	} 
	
}
