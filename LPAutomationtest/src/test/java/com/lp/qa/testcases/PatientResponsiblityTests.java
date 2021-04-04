package com.lp.qa.testcases;

import java.awt.AWTException;
import java.io.IOException;

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
import com.lp.qa.pages.HomePaymentsPage;
import com.lp.qa.pages.LoginPage;
import com.lp.qa.pages.ManualEntryPaymentPage;
import com.lp.qa.pages.PatientResponsiblityPage;
import com.lp.qa.pages.TransactionReportPage;

import com.lp.qa.testdata.TestData;

public class PatientResponsiblityTests extends TestBase{

	LoginPage loginPage;
	ManualEntryPaymentPage manaulEntryPage;
	HomePaymentsPage Homepage;
	
	TransactionReportPage transactionReportPage;
	ContactLessPage contactLessPage;
	CommunicationReportPage communicationReportPage;
	CollectCardPage collectcardpage;
	PatientResponsiblityPage patientResponsiblityPage;
	public String RefNo;
	public String Amount;
	public String Phone_Email;
	public String LastName;
	public String FirstName;
	public String PatientName;
		
	public String WithInsRefNo;
	public String WithInsAmount;
	public String WithInsPhone_Email;
	public String WithInsLastName;
	public String WithInsFirstName;
	public String WithInsPatientName;
	String Last_4_digits_CardNumber;
	public PatientResponsiblityTests() throws IOException {
		super();
	}
	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		System.out.println("Before PR Class");
		browserInitialization();
		loginPage=new LoginPage();
		transactionReportPage=new TransactionReportPage();
		communicationReportPage=new CommunicationReportPage();
		collectcardpage=new CollectCardPage();
		patientResponsiblityPage=new PatientResponsiblityPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		driver.quit();		
	}
	
	@DataProvider(name="patientRespwithIns")
	public Object[][] getPatientRespWithInsPatientData() throws IOException {
		Object[][] data=TestData.getPatintResponsPaymentData("PRWithIns");
		return data ;								
	}
		
	@Test(dataProvider="patientRespwithIns")
	public void TC01_PR_VerifyPatRespWithInsurancePayment(String TestCase,String RefNo,String FirstName,String LastName,String Phone_Email,String Amount,String InsAmount) throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();		
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		patientResponsiblityPage=new PatientResponsiblityPage();
		Boolean success=patientResponsiblityPage.patientRespWithInsurance(RefNo, FirstName, LastName, Phone_Email, Amount,InsAmount);
		this.WithInsRefNo=RefNo;
		this.WithInsAmount=Amount;
		this.WithInsFirstName=FirstName;
		this.WithInsLastName=LastName;		
		String WithInsPatientName1=FirstName+" "+LastName;
		this.WithInsPatientName=WithInsPatientName1;
		Assert.assertTrue(success);		
		driver.quit();
	} 
	
	@Test
	public void TC02_PR_VerifyAmountInCommReport() throws InterruptedException, IOException {
		browserInitialization();
		loginPage=new LoginPage();		
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		float ReportAmount=communicationReportPage.verifyAmount(WithInsRefNo, WithInsAmount);
		Assert.assertEquals(ReportAmount,WithInsAmount);
		driver.quit();		
	
	}
			
	@Test
	public void TC03_PR_VerifyCollectCardPageforNewPatient() throws InterruptedException, IOException, AWTException {
		Boolean CollectCardSuccess=null;
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		communicationReportPage.getCollectCardPaymentlink(WithInsRefNo);
		collectcardpage=new CollectCardPage();
		if(communicationReportPage.FinalURL!=null) {
			CollectCardSuccess=collectcardpage.collectCardPayment(CommunicationReportPage.FinalURL,WithInsAmount,CommunicationReportPage.paymentType);
		}
		Assert.assertTrue(CollectCardSuccess);		
		driver.quit();
	}
			
	
	@Test()
	public void TC06_PR_verifyPRWithInsAmountInCommReport( ) throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String amount=transactionReportPage.verifyTransactionAmount(WithInsRefNo,WithInsAmount);
		Assert.assertEquals(amount,WithInsAmount);		
		driver.quit();
	}
	@Test()
	public void TC07_PR_verifyPRWithInsPatientNameInCommReport() throws IOException, InterruptedException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String PatientName=	transactionReportPage.verifyPatientName(WithInsRefNo,WithInsPatientName);
		Assert.assertEquals(PatientName,WithInsPatientName );		
		driver.quit();
	}
	@Test()
	public void TC08_PR_verifyPRWithInsLoginIDInCommReport() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String loginID=transactionReportPage.verifyuserLoginID(WithInsRefNo);
		Assert.assertEquals(loginID,prop.getProperty("username"));		
		driver.quit();
	}
	
	@Test()
	public void TC09_PR_verifyRefundFuctionalityForPatientResponTransaction() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String RefundStatus=transactionReportPage.verifyFullyRefund(WithInsRefNo,WithInsAmount);
		Assert.assertEquals(RefundStatus, "Approved");		
		driver.quit();
	}
		
	@Test()
	public void TC10_PR_verifyRefundVoidFunctForPatRespTransaction() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String RefundVoidStatus=transactionReportPage.VerifyRefundVoid(WithInsRefNo);
		Assert.assertEquals(RefundVoidStatus, "Approved");		
		driver.quit();
	}		
	@Test()
	public void TC11_PR_verifyVoidfunctforPatientRespTransaction() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String Voidstatus=transactionReportPage.verifyAuthorizeVoid(WithInsRefNo);
		Assert.assertEquals(Voidstatus,"Approved");
		
		driver.quit();
	} 
	
	@DataProvider(name="patientRespwihtoutIns")
	public Object[][] getPatientRespWithoutInsPatientData() throws IOException {
		Object[][] data=TestData.getPatintResponsPaymentData("PRWithoutIns");
		return data ;								
	}
	
	@Test(dataProvider="patientRespwihtoutIns")
	public void TC12_PR_PatientRespFormWithoutInsurance(String TestCase,String RefNo,String FirstName,String LastName,String Phone_Email,String Amount) throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		communicationReportPage=new CommunicationReportPage();
		collectcardpage=new CollectCardPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		patientResponsiblityPage=new PatientResponsiblityPage();
		Boolean Success=patientResponsiblityPage.patientRespWithoutInsurance(RefNo, FirstName, LastName, Phone_Email, Amount);
		this.RefNo=RefNo;
		this.Amount=Amount;
		this.FirstName=FirstName;
		this.LastName=LastName;
		String PatientName1=FirstName+" "+LastName;
		this.PatientName=PatientName1;
		
		Assert.assertTrue(Success);
		driver.quit();
	} 
	
	@Test
	public void TC13_PR_VerifyPatientResponsibilityWithoutInsurance() throws InterruptedException, IOException, AWTException {
		Boolean printReceipt=null;
		browserInitialization();
		loginPage=new LoginPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		communicationReportPage=new CommunicationReportPage();
		communicationReportPage.navigateToCommunicationReport();
		communicationReportPage.runCommunicationReport();
		communicationReportPage.getCollectCardPaymentlink(WithInsRefNo);
		collectcardpage=new CollectCardPage();
		if(communicationReportPage.FinalURL!=null) {
			printReceipt=collectcardpage.collectCardPayment(CommunicationReportPage.FinalURL,WithInsAmount,CommunicationReportPage.paymentType);
		}
		Assert.assertTrue(printReceipt);
		driver.quit();
	}
	
		
	@Test()
	public void TC15_PR_verifyPRWithoutInsTransactionStatus() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String TranStatus=transactionReportPage.verifyTransactionStatus( RefNo);
		Assert.assertEquals(TranStatus, "Approved");
		driver.quit();
	}
	@Test()
	public void TC17_PR_verifyPRWithoutInsTransactionAmount( ) throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String reportAmount=transactionReportPage.verifyTransactionAmount(RefNo,Amount);
		Assert.assertEquals(reportAmount, Amount);		
		driver.quit();
	}
	@Test()
	public void TC18_PR_verifyPRWithoutInsTransactionPatientName() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String patName=transactionReportPage.verifyPatientName(RefNo,PatientName);
		//verifyPatientName(manaulEntryPage.Ref_NO,manaulEntryPage.PatientName);
		Assert.assertEquals(patName,PatientName);
		driver.quit();
	}
	@Test()
	public void TC19_PR_verifyPRWithoutInsTransactionUserLoginID() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String UserLoginID=transactionReportPage.verifyuserLoginID(RefNo);
		Assert.assertEquals(UserLoginID, prop.getProperty("username"));
		driver.quit();
	}
	
	@Test()
	public void TC20_PR_verifyPRWithoutInsTransactionRefund() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String refundStatus=transactionReportPage.verifyFullyRefund(RefNo,Amount);
		Assert.assertEquals(refundStatus,"Approved");
		driver.quit();
	}
		
	@Test()
	public void TC21_PR_verifyPRWithoutInsTransactionRefundVoid() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String RefundVoidStatus=transactionReportPage.VerifyRefundVoid(RefNo);
		Assert.assertEquals(RefundVoidStatus,"Approved");
		driver.quit();
	}		
	@Test()
	public void TC22_PR_verifyPRwithoutInsTransactionAuthVoid() throws InterruptedException, IOException, AWTException {
		browserInitialization();
		loginPage=new LoginPage();	
		transactionReportPage=new TransactionReportPage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		transactionReportPage.navigateTransactionReport();
		transactionReportPage.runTransactionReport();
		String VoidStatus=transactionReportPage.verifyAuthorizeVoid(RefNo);
		Assert.assertEquals(VoidStatus,"Approved");
		driver.quit();
	} 
	@AfterClass()
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(4000);
		driver.quit();  
	} 
	
}
