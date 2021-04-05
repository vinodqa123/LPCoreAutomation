package com.lp.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lp.qa.base.TestBase;
import com.lp.qa.pages.CollectCardPage;
import com.lp.qa.pages.CommunicationReportPage;
import com.lp.qa.pages.ContactLessPage;
import com.lp.qa.pages.HomePaymentsPage;
import com.lp.qa.pages.LoginPage;
import com.lp.qa.pages.ManualEntryPaymentPage;
import com.lp.qa.pages.RecurringBillingPage;
import com.lp.qa.pages.TransactionReportPage;

import com.lp.qa.testdata.TestData;
import com.lp.qa.util.Util;

public class RecurringBillingTests extends TestBase{
	LoginPage loginPage;
	ManualEntryPaymentPage manaulEntryPage;
	HomePaymentsPage Homepage;
	
	TransactionReportPage transactionReportPage;
	RecurringBillingPage recBillingPage;
	CommunicationReportPage communicationReportPage;
	CollectCardPage collectcardpage;
	String patientName;
	String phone;
	String email;
	String refNo;
	String recAmt;
	String FrequencyType;
	String toalPayments;
	String recDate;
	String Last_4_digits_CardNumber;
	String SingleRecurringPayment;
	String Amount2;
	String Phone_Email;
	Float singleRecurringAmt;
	String CardNumber;
	String Exp_Month;
	String Exp_Year;
	String Cvv;
	String Address;
	String City;
	String State;
	String ZipCode;
	
	public RecurringBillingTests() throws IOException {
		super();
	}
	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		System.out.println("Before Class in Recurring");
	/*	browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		communicationReportPage=new CommunicationReportPage();
		collectcardpage=new CollectCardPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(3000);
		driver.quit(); */
	} 
	
	@DataProvider(name="cardEntryByProvider")
	public Object[][] getRecurringBillingPaymentData2() throws IOException {
		Object[][] data=TestData.getRecBillingCardEntryByProviderData("cardEntryProvider");
		return data ;
								
	}
		
	@Test(dataProvider="cardEntryByProvider")
	public void TC01_RB_CardEntryByProvider_transaction(String TestCase,String RefNo,String patientName1,String phone1,String email1,String RecAmt,String FrequencyType,String toalPayments1,String recDate,String CardNumber,String Exp_Month,String Exp_Year,String Cvv,String Address,String City,String State,String ZipCode ) throws InterruptedException, IOException, AWTException {	
		browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		this.patientName=patientName1;
		this.phone=phone1;
		this.email=email1;
		this.refNo=RefNo;
		this.recAmt=RecAmt;
		this.FrequencyType=FrequencyType;
		this.toalPayments=toalPayments1;
		this.recDate=recDate; 
		this.CardNumber=CardNumber;
		this.Exp_Month=Exp_Month;
		this.Exp_Year=Exp_Year;
		this.Cvv=Cvv;
		this.Address=Address;
		this.City=City;
		this.State=State;
		this.ZipCode=ZipCode;	
		recBillingPage.recCardEntryByProvider(patientName1, phone1, email1, RefNo, RecAmt, FrequencyType, toalPayments1, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
			
		if (CardNumber.length() > 4) 
 		{
			Last_4_digits_CardNumber = CardNumber.substring(CardNumber.length() - 4);
 		} 
 		else
 		{
 			Last_4_digits_CardNumber = CardNumber;
 		}
	
		float Amount=Float.parseFloat(RecAmt);
		int toalPayments=Integer.parseInt(toalPayments1);
		singleRecurringAmt=Amount/toalPayments;		
		driver.quit();
	//	Amount2=String.valueOf(singleRecurringAmt);
		
	} 
	/*
	
	@Test()	
	public void TC03_RB_VerifySingleRecurringAmount() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		recBillingPage.recCardEntryByProvider(patientName, phone, email, refNo, recAmt, FrequencyType, toalPayments, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
		Float singleRecPayment=recBillingPage.verifySingleRecurringAmount();
		Assert.assertEquals(singleRecPayment, singleRecurringAmt);
		driver.quit();
	} */
	
	@Test()	
	public void TC04_RB_VerifyStartDateInScheduleRecurringPopUp() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		recBillingPage.recCardEntryByProvider(patientName, phone, email, refNo, recAmt, FrequencyType, toalPayments, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
		String recStartDate=recBillingPage.verifyStartDateInScheduleRecurringPopUp();
		Util.getRecDate(recDate, FrequencyType, toalPayments);
		System.out.println("Portal rec start date:"+recStartDate);
		System.out.println("Util start date:"+Util.startDate);
		Boolean recdate=recStartDate.equalsIgnoreCase(Util.startDate);
		
		Assert.assertTrue(recdate);
		driver.quit();
		
	}
	@Test()	
	public void TC05_VerifyEndDateInScheduleRecurringPopUp() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	//	recBillingPage.recCardEntryByProvider(patientName, phone, email, refNo, recAmt, FrequencyType, toalPayments, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
//		recBillingPage.recCardEntryByProvider(patientName, phone, email, refNo, recAmt, FrequencyType, toalPayments, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
		String recEndDate=recBillingPage.verifyEndDateInScheduleRecurringPopUp();
		Util.getRecDate(recDate, FrequencyType, toalPayments);
		Boolean recdate=recEndDate.equalsIgnoreCase(Util.recEndDate);
		Assert.assertTrue(recdate);
		driver.quit(); 
	}  /*
	public void TC06_VerifyTotalBalanceDueInScheduleRecurringPopUp() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		recBillingPage.recCardEntryByProvider(patientName, phone, email, refNo, recAmt, FrequencyType, toalPayments, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
		Float totalAmountDue=recBillingPage.verifyTotalAmountDueInScheduleRecurringPopUp();
		Float totalDue=Float.parseFloat(recAmt);
		Assert.assertEquals(totalAmountDue,totalDue);
		driver.quit();
		
	}
	public void TC07_VerifyRecurringCollectingDatesInScheduleRecurringPopUp() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();	
		recBillingPage=new RecurringBillingPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		recBillingPage.recCardEntryByProvider(patientName, phone, email, refNo, recAmt, FrequencyType, toalPayments, recDate, CardNumber, Exp_Month, Exp_Year, Cvv, Address, City, State, ZipCode);
		
		Integer range=Integer.parseInt(toalPayments);
		ArrayList<String> recDates=recBillingPage.VerifyRecurringCollectingDatesInScheduleRecurringPopUp();
		for(int i=0;i<range;i++) {
			String recCollectingDate=recDates.get(i);
			if(recCollectingDate.equalsIgnoreCase(Util.recStartDate)) {
				System.out.println("Scheduled dates are matching!"
						+ "\n"
						+ "recurrring no: "+i+""
						+ "RecCollectionDate: "+recCollectingDate+""
						+"RecollectionDate: "+Util.recStartDate		);
			}else
				System.out.println("Scheduled dates are not matching!"
						+ "\n"
						+ "recurrring no: "+i+""
						+ "RecCollectionDate: "+recCollectingDate+""
						+"RecollectionDate: "+Util.recStartDate		);
		}
		driver.quit();
	}
	public void TC08_VerifyPrintInScheduleRecurringPopUp() {
		
		
		
	}
	
	
	@Test()
	public void TC03_RB_CardEntryByProvider_runTransactionReport() throws InterruptedException {
		transactionReportPage.runTransactionReport();
	}
		
	@Test()
	public void TC04_RB_CardEntryByProvider_verifyTransactionStatus() throws InterruptedException {
		transactionReportPage.verifyTransactionStatus(refNo);
	}
	@Test()
	public void TC05_RB_verifyTransactionAmount( ) throws InterruptedException {
		transactionReportPage.verifyTransactionAmount(refNo,Amount2);
	}
	@Test()
	public void TC06_RB_CardEntryByProvider_verifyTransactionPatientName() {
		transactionReportPage.verifyPatientName(refNo,patientName);
		//verifyPatientName(manaulEntryPage.Ref_NO,manaulEntryPage.PatientName);
	}
	@Test()
	public void TC07_RB_CardEntryByProvider_verifyTransactionUserLoginID() {
		transactionReportPage.verifyuserLoginID(refNo);
	}
	@Test()
	public void TC08_RB_CardEntryByProvider_verifyTransactionCardNumber() {
		transactionReportPage.verifyTransactionCardNumber(refNo,Last_4_digits_CardNumber);
	}
	@Test()
	public void TC09_RB_CardEntryByProvider_verifyRefund() throws InterruptedException {
		transactionReportPage.verifyFullyRefund(refNo,Amount2);
	}
		
	@Test()
	public void TC10_RB_CardEntryByProvider_verifyRefundVoid() throws InterruptedException {
		transactionReportPage.VerifyRefundVoid(refNo);
	}		
	@Test()
	public void TC11_RB_CardEntryByProvider_verifyAuthVoid() throws InterruptedException {
		transactionReportPage.verifyAuthorizeVoid(refNo);
		driver.quit();
	} 
	
	@DataProvider(name="cardEntryByPatient")
	public Object[][] getRecurringBillingPatientData() throws IOException {
		Object[][] data=TestData.getRecBillingCardEntryByPatientData("cardEntryPatient");
		return data ;
								
	}
		
	@Test(dataProvider="cardEntryByPatient")
	public void TC12_RB_CardEntryByPatient_transaction(String TestCase,String RefNo,String patientName1,String phone1,String email1,String RecAmt,String FrequencyType,String toalPayments1,String recDate ) throws InterruptedException, IOException, AWTException {	
		browserInitialization();
		loginPage=new LoginPage();
	
		transactionReportPage=new TransactionReportPage();
		recBillingPage=new RecurringBillingPage();
		communicationReportPage=new CommunicationReportPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(3000);		
		recBillingPage.recCardEntryByPatient(patientName1, phone1, email1, RefNo, RecAmt, FrequencyType, toalPayments1, recDate);
		this.patientName=patientName1;
		this.phone=phone1;
		this.email=email1;
		this.refNo=RefNo;
		this.recAmt=RecAmt;
		this.FrequencyType=FrequencyType;
		this.toalPayments=toalPayments1;
		this.recDate=recDate;
		float Amount=Float.parseFloat(RecAmt);
		int toalPayments=Integer.parseInt(toalPayments1);
		Float singleRecurringAmt=Amount/toalPayments;		
		Amount2=String.valueOf(singleRecurringAmt);
	} 
	
	@Test
	public void TC13_RB_CardEntryByPatient_VerifyAmt() throws InterruptedException, IOException {
		
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		communicationReportPage.verifyAmount(refNo, Amount2);
	
	}  
	@Test
	public void TC14_RB_CollectCardpaymentlink() throws InterruptedException, AWTException, IOException {
		
		Thread.sleep(3000);
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		communicationReportPage.getCollectCardPaymentlink(refNo);
	}
	
		
	@Test
	public void TC14_RB_CardEntryByPatient_CollectPayment() throws InterruptedException, IOException, AWTException {
		Thread.sleep(3000);
		if(communicationReportPage.FinalURL!=null) {
			collectcardpage.collectCardPayment(CommunicationReportPage.FinalURL,Amount2,CommunicationReportPage.paymentType);
		}
				
	}
	*/
	@Test()
	public void TC15_RB_CardEntryByPatient_RunTransactionReport() throws InterruptedException, AWTException, IOException {
		browserInitialization();
		loginPage=new LoginPage();
		
		transactionReportPage=new TransactionReportPage();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(3000);
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
			
	}
		
	@Test()
	public void TC16_RB_CardEntryByPatient_verifyTransactionStatus() throws InterruptedException {
		transactionReportPage.verifyTransactionStatus(refNo);
	}
	@Test()
	public void TC17_RB_CardEntryByPatient_verifyTransactionAmount( ) throws InterruptedException {
		transactionReportPage.verifyTransactionAmount(refNo,Amount2);
	}
	@Test()
	public void TC18_RB_CardEntryByPatient_verifyTransactionPatientName() {
		transactionReportPage.verifyPatientName(refNo,patientName);
		//verifyPatientName(manaulEntryPage.Ref_NO,manaulEntryPage.PatientName);
	}
	@Test()
	public void TC19_RB_CardEntryByPatient_verifyTransactionUserLoginID() {
		transactionReportPage.verifyuserLoginID(refNo);
	}	
		
			
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
