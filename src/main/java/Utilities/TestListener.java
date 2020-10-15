package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestLib.Common;
import TestLib.Driver;

public class TestListener implements  ITestListener ,ISuiteListener{
	final public static log4j logger = Driver.getLogger();
	String StartTime, EndTime;
	ExtenantReportUtils report;
	
	//private static List<String> executionDataDirectories = Arrays.asList("TestLogs/logs", "TestLogs/screenShots", "test-output", "TestLogs/videos", "TestLogs/buildlogs", "TestLogs/Listener txt files", "TestLogs/IETraceLogs", "TestLogs/DebugLogs");
	
	private static List<String> executionDataDirectories = Arrays.asList("TestLogs/logs", "TestLogs/screenShots", "test-output", "TestLogs/videos");
	public static void cleanAutomationEnvironment() {
		executionDataDirectories.forEach(eachDirectory -> deleteFilesInDirectory(new File(eachDirectory)));
	}

	public  void onStart(ISuite arg0) 
	{
		cleanAutomationEnvironment();
		System.out.println("Suited Name	: "+arg0.getXmlSuite().getName());
		/*if(report==null)
		report=new ExtenantReportUtils(arg0.getXmlSuite().getName());*/
	}
	
	private static void deleteFilesInDirectory(File directoryPath) {
		try {
			FileUtils.cleanDirectory(directoryPath);
		} catch (IOException e) {
			Driver.getLogger().error("Unable to delete files in directory. Continuing.. \n Error: ");
		}
	}
	

	public void onStart(ITestContext context) {
		String testName=context.getName();
		System.out.println(context.getName());
		/*if(report==null)
		{
			report=new ExtenantReportUtils(testName);
		}*/
		
		report=new ExtenantReportUtils(testName);
	}

	
	public void onTestStart(ITestResult result) {
		
		String testName=result.getName();
		System.out.println(result.getName());
		Thread.currentThread().setName(result.getTestContext().getCurrentXmlTest().getClasses().get(0).getName());
		if(report==null)
		{
			report=new ExtenantReportUtils(result.getTestContext().getCurrentXmlTest().getClasses().get(0).getName());
		}
		report.createTestcase(testName);
		
	}

	
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShotPathforReport(testName);
		report.addPassLog(testName, filePath);	
		
	}

	
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShotPathforReport(testName);
		report.addFailedLog(testName+" Failed",filePath);
	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShotPathforReport(testName);
		report.addFailedLog(testName+" Failed",filePath);
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShotPathforReport(testName);
		report.addFailedLog(testName+" Failed",filePath);
		
	}

	
	
	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.extent.flush();
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		report.extent.flush();
	}

	

}
