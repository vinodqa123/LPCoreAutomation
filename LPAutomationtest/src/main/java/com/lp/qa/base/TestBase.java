package com.lp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.lp.qa.extentreport.ExtentReport;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.tools.sjavac.Log;

public class TestBase implements IReporter {
	public static Properties prop;
	public static File file;
	public static FileReader reader;
	public static FileInputStream ip;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ExtentReports extent;
	public static ExtentTest test;
	public TestBase() throws IOException { 
		
		try {		
			prop=new Properties();
			ip= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/lp/qa/config/config.properties");
			prop.load(ip);	
		}
		catch(IOException e) {
			e.printStackTrace();		
			
		}
		
	}
	public static void browserInitialization() throws InterruptedException {
		String browsername=prop.getProperty("browser");
		if(browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","D:\\Vinod\\Eclipse\\Chrome\\chromedriver.exe"); 
			driver=new ChromeDriver();
			Thread.sleep(3000);
			Reporter.log("Chrome browser launched Successfully!");	
			
		}
		else if(browsername.equals("ie")) {
			System.setProperty("webdriver.ie.driver","D:\\Vinod\\Eclipse\\IEDriverServer.exe"); 
			driver=new InternetExplorerDriver();  
			Thread.sleep(5000);
			Reporter.log("IE browser launched Successfully!");	
			
		}
		wait=new WebDriverWait(driver,30);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.get(prop.getProperty("lpurl"));
		Reporter.log("Application has been launched successfully!");	
	}
	
			
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		extent = new ExtentReports(outputDirectory +File.separator
				+ "ExtentReport.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	public void buildTestNodes(IResultMap tests, LogStatus status) {
		
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				for(String message:Reporter.getOutput(result)){
					test.setStartedTime(getTime(result.getStartMillis()));
					test.log(LogStatus.INFO, message);
                }
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
					
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	public void getreportLog(String message) {    
	   //For extentTest HTML report
	  

	}
	
	
	

}
