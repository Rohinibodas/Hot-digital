package Utilities;

import java.util.Date;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestLib.Common;
import TestLib.Driver;

public class TestListener implements  ITestListener {
	final public static log4j logger = Driver.getLogger();
	String StartTime, EndTime;
	ExtenantReportUtils report;
	
	public  void onStart(ISuite arg0) 
	{
		System.out.println("Suited Name	: "+arg0.getXmlSuite().getName());
		if(report!=null)
		report=new ExtenantReportUtils(arg0.getXmlSuite().getName());
	}
	
	


	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		System.out.println(result.getName());
		Thread.currentThread().setName(result.getTestContext().getCurrentXmlTest().getClasses().get(0).getName());
		if(report==null)
		{
			report=new ExtenantReportUtils(result.getTestContext().getCurrentXmlTest().getClasses().get(0).getName());
		}
		
	}

	
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		report.createTestcase(testName);
		String filePath=Common.getscreenShot(testName);
		report.addPassLog(testName, filePath);
		report.extent.flush();
		
	}

	
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShot(testName);
		report.addFailedLog(testName+" Failed",filePath);
	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShot(testName);
		report.addFailedLog(testName+" Failed",filePath);
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		String filePath=Common.getscreenShot(testName);
		report.addFailedLog(testName+" Failed",filePath);
		
	}

	
	public void onStart(ITestContext context) {
		String testName=context.getName();
		System.out.println(context.getName());
		if(report==null)
		{
			report=new ExtenantReportUtils(testName);
		}
		report.createTestcase(testName);
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

}
