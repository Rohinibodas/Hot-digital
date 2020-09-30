package Utilities;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.markuputils.Markup;

public class ExtenantReportUtils {
	
	static ExtentReports extent;
	static ExtentTest logger;
	
	public ExtenantReportUtils(String reportFileName) {
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(reportFileName+"_Report.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    extent.setSystemInfo("OS", "windows");
	    extent.setSystemInfo("Browser", "chrome");
	    extent.setSystemInfo("Environment", "Automation Testing ENV");
	    htmlReporter.config().setChartVisibilityOnOpen(true);
	    htmlReporter.config().setDocumentTitle("Extent Report Demo");
	    htmlReporter.config().setReportName("Test Report");
	    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	public static ExtentTest createTestcase(String TestCaseName)
	{
		logger=extent.createTest(TestCaseName);
		return logger;
	}
	
	public static void addInfoLog(String infoMessage)
	{
		 logger.log(Status.INFO, infoMessage);
	}
	
	public static void addPassLog(String passMessage,String screenShotPath)
	{
		try {
			logger.log(Status.PASS, passMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addFailedLog(String failMessage,String screenShotPath)
	{
		try {
			logger.log(Status.FAIL, failMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addPassLog(String passMessage)
	{
		logger.log(Status.PASS, passMessage);
	}
	
	public static void addFailedLog(String failMessage)
	{
		logger.log(Status.FAIL, failMessage);
	}
	
	public static void main(String[] args) throws IOException {
		
	ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter("ExtentReportResults.html");
    ExtentReports extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("OS", "windows");
    extent.setSystemInfo("Browser", "chrome");
    extent.setSystemInfo("Environment", "Automation Testing ENV");
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setDocumentTitle("Extent Report Demo");
    htmlReporter.config().setReportName("Test Report");
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    
    ExtentTest logger=extent.createTest("LoginTest");
    logger.log(Status.INFO, "Login to Revolon");
    logger.log(Status.PASS, "Title verified");
    logger.log(Status.FAIL, "Failed with", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\Mahendra\\Pictures\\PAN.jpg").build());
    extent.flush();
	
	}

}
