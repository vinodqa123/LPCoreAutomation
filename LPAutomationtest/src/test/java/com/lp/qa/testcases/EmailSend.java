package com.lp.qa.testcases;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class EmailSend {
	
	
	@Test
	public void sendEmail1() throws EmailException {
		
		System.out.println("Report is loading & Fetching ,sending to an Email 111 ");
	}
	@Test
	public void sendEmail2() throws EmailException {
		
		System.out.println("Report is loading & Fetching ,sending to an Email 2222");
	}
	@Test
	public void sendEmail3() throws EmailException {
		
		System.out.println("Report is loading & Fetching ,sending to an Email 333");
	}
	
	
	@AfterSuite
	public void sendEmailwithAttachment() throws EmailException {
		 EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("C:\\Users\\sys\\eclipse-workspace\\LPAutomationtest\\test-output\\ExtentReport.html");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Report");
			  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  	email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("jadhavvinod9096@gmail.com", "9096171642vhj"));
			email.setSSLOnConnect(true);
		 
		  email.addTo("vhjadhav9096@gmail.com");
		  email.setFrom("jadhavvinod9096@gmail.com");
		  email.setSubject("Selenium Test script Report");
		  email.setMsg("Hi Team,"
		  		+"\n"
				+ "\n"
				+ "Selenium test scrpits execution is completed! Please check the generated Pass/Fail test cases report in attached file."
				+ "\n"
				+ "\n"
				+ "Regards,"
				+ "\n"
				+ "Vinod J");
		  
	
		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();
	}

}
