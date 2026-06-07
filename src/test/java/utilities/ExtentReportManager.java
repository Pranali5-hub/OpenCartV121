package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) { //testContext means which Test method executed so that TM details will be stored into this variable
		
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0));//Generating time stamp
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); //specify location of report
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//title of report
		sparkReporter.config().setReportName("opencart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os"); //getCuurent method will return xml & identify from which XML file test is executed
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser"); //both 2 method will capture the name of OS & Browser from xml file
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();//capture all groups into List collection & display into reports
		if(!includedGroups.isEmpty()) { //if groups name are empty or not
			
			extent.setSystemInfo("Groups", includedGroups.toString()); //if groups are available then add name into reports
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
			
	}
	
	public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //Category is nothing but the Group
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		String imgPath=new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		//This is extra code
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
		//This code will send the report automatically
		try {
			//Converting the reportPath into URL format
			
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		//Create the email message
		
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
		email.setSSLOnConnect(true);
		email.setFrom("pavanoltraining@gmail.com"); //Sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report....");
		email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
		email.attach(url, "extent report","please check report....");
		email.send(); //send the email
		}
		catch(Exception e) {
			
			e.printStackTrace();
		
		}
		
	}
	
	
}

