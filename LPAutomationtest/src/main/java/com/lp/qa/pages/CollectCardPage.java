package com.lp.qa.pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.lp.qa.base.TestBase;


public class CollectCardPage extends TestBase{
	
	public String dbURL=prop.getProperty("dbUrl");
	public String UserName=prop.getProperty("dbun");
	public String Password=prop.getProperty("dbpwd");	
	public String userlogin=prop.getProperty("username");
	
	
	@FindBy(xpath="//input[@id='CardNumber']")
	WebElement CardNumber;
	@FindBy(xpath="//select[@id='ExpMonth']")
	WebElement ExpMonth;
	@FindBy(xpath="//select[@id='ExpYear']")
	WebElement ExpYear;
	@FindBy(xpath="//input[@id='CardCvv']")
	WebElement CardCvv;
	@FindBy(xpath="//input[@id='CardHolderName']")
	WebElement CardHolderName;
	@FindBy(xpath="//body/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/div[1]/input[1]")
	WebElement PaymentAmount;
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/div[1]/p[1]")
	WebElement Speciality;
	@FindBy(xpath="//a[contains(text(),'Heartland EMail')]")
	WebElement ProviderName;
	@FindBy(xpath="//body/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")
	WebElement PayNowBtn;
	
	public CollectCardPage() throws IOException{
	
		PageFactory.initElements(driver,this);
		
	}
	
	
	public Boolean  collectCardPayment(String URL,String Amount,String PaymentType) throws InterruptedException, IOException {
		Boolean PaymentReceipt;
		Thread.sleep(2000);
		driver.get(URL);
		driver.manage().window().maximize();
		Reporter.log("Payment URL has been opened in browser,URL as follows"+URL);
		driver.findElement(By.id("CardNumber")).sendKeys("4242424242424242");
		Select Expiry_Month = new Select(driver.findElement(By.id("ExpMonth")));
		Expiry_Month.selectByVisibleText("02");
		Select Expiry_Year = new Select(driver.findElement(By.id("ExpYear")));
		Expiry_Year.selectByVisibleText("2023");
		driver.findElement(By.id("CardCvv")).sendKeys("123");
		driver.findElement(By.id("CardHolderName")).sendKeys("John Smith Holder");
		WebElement Amount1=driver.findElement(By.name("amount"));
		String amt=Amount1.getText();
		System.out.println("Amount:"+amt);		
		if(PaymentType.contains("Recurring Billing")) {
			WebElement zipCode=driver.findElement(By.xpath("//input[@id='AvsZipCode']"));
			zipCode.sendKeys("12345");
			Reporter.log("Card details entered successfully!");
			driver.findElement(By.xpath("//body/div[4]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")).click();
			
			
		}else {
			Thread.sleep(2000);
			Reporter.log("Card details entered successfully!");
			driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")).click();
		//	driver.findElement(By.xpath("//body/div[4]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")).click();
		
		}
		Thread.sleep(3000);
		WebElement transId=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[10]/td[2]"));
		String TransactionId=transId.getText();
		
		WebElement receipt=driver.findElement(By.xpath("//h2[contains(text(),'Payment Receipt')]"));
		PaymentReceipt=receipt.isDisplayed();	
		
		Reporter.log("Payment has initiated successfully with Trans Id "+TransactionId);
		Thread.sleep(4000);
		driver.close();
		return PaymentReceipt;
		
	}
	
}
