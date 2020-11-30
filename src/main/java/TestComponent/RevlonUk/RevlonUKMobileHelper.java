package TestComponent.RevlonUk;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class RevlonUKMobileHelper {
	
	//String datafile;
	//ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	
	public  RevlonUKMobileHelper()
	{
		//excelData=new ExcelReader(datafile);
		//data=excelData.getExcelValue();
		//this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("RevlonUK");
			report.createTestcase("RevlonUKTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
	
	
	public void ValidateHomepagelogo() throws Exception
	{
		String expectedResult="Validating Home page logo and should lands on Home Page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/']")).click();
			Thread.sleep(4000);
			report.addPassLog(expectedResult, "Should redirect to home page", "Redirected to Home Page successfully", Common.getscreenShotPathforReport("Home Page Logo success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to home page", "Not Redirected to Home Page", Common.getscreenShotPathforReport("Home Page Logo Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void Collections() throws Exception{
		String expectedResult="Validating Home page Megamenu Collections and should lands on Collections page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			//Common.clickElement("className", "nav-toggle");
			//Common.clickElement("xpath", "//span[@class='action nav-toggle']/small[2]");
			//Common.javascriptclickElement("xpath", "//span[@class='action nav-toggle']/small[2]");
			//Common.clickElement("xpath", "//span[@class='action nav-toggle']");
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[@class='opener']");
			Common.clickElement("xpath", "//span[contains(text(),'One Step Family')]");
			Thread.sleep(4000);
			String collections=Common.getText("xpath", "//h1[contains(text(),'The One-Step Collection')]");
			Assert.assertEquals(collections, "The One-Step Collection");
			report.addPassLog(expectedResult, "Should redirect to Collections page", "Redirected to Collections Page successfully", Common.getscreenShotPathforReport("Collections Page success"));
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Collections page", "Not Redirected to Collections page", Common.getscreenShotPathforReport("Collections page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Dryers() throws Exception{
		String expectedResult="Validating Home page Megamenu Dryers and should lands on Dryers page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			
			Thread.sleep(3000);
			
			Common.clickElement("xpath", "//span[contains(text(),'Dryers')]");
			Thread.sleep(4000);
			String Dryer=Common.getText("xpath", "//span[contains(text(),'Dryers')]");
			Assert.assertEquals(Dryer, "Dryers");
			report.addPassLog(expectedResult, "Should redirect to Dryers page", "Redirected to Dryers Page successfully", Common.getscreenShotPathforReport("Dryers Page success"));
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Dryers page", "Not Redirected to Dryers page", Common.getscreenShotPathforReport("Dryers page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Straighteners() throws Exception{
		String expectedResult="Validating Home page Megamenu Straighteners and should lands on Straighteners page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			
			Thread.sleep(3000);	
			Common.clickElement("xpath", "//span[contains(text(),'Straighteners')]");
			Thread.sleep(4000);
			String Straighteners=Common.getText("xpath", "//span[contains(text(),'Straighteners')]");
			Assert.assertEquals(Straighteners, "Straighteners");
			report.addPassLog(expectedResult, "Should redirect to Straighteners page", "Redirected to Straighteners Page successfully", Common.getscreenShotPathforReport("Straighteners Page success"));
		
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Straighteners page", "Not Redirected to Straighteners page", Common.getscreenShotPathforReport("Straighteners page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void CurlingIrons() throws Exception{
		String expectedResult="Validating Home page Megamenu CurlingIrons and should lands on CurlingIrons page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);		
			Common.clickElement("xpath", "//span[contains(text(),'Curling Irons')]");
			Thread.sleep(4000);
			String CurlingIrons=Common.getText("xpath", "//span[contains(text(),'Curling Irons')]");
			Assert.assertEquals(CurlingIrons, "Curling Irons");
			report.addPassLog(expectedResult, "Should redirect to CurlingIrons page", "Redirected to CurlingIrons Page successfully", Common.getscreenShotPathforReport("CurlingIrons Page success"));
		
		}catch(Exception |Error e){
			//report.addFailedLog(expectedResult,"Should redirect to CurlingIrons page", "Not Redirected to CurlingIrons page", Common.getscreenShotPathforReport("CurlingIrons page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void HairBrushesElastics() throws Exception{
		String expectedResult="Validating Home page Megamenu HairBrushesElastics and should lands on HairBrushesElastics page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);			
			Common.clickElement("xpath", "//span[contains(text(),'Hair Brushes & Elastics')]");
			Thread.sleep(4000);
			String Brushes=Common.getText("xpath", "//span[contains(text(),'Brushes')]");
			Assert.assertEquals(Brushes, "Brushes");
			report.addPassLog(expectedResult, "Should redirect to HairBrushesElastics page", "Redirected to HairBrushesElastics Page successfully", Common.getscreenShotPathforReport("HairBrushesElastics Page success"));
		
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to HairBrushesElastics page", "Not Redirected to HairBrushesElastics page", Common.getscreenShotPathforReport("HairBrushesElastics page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Specialty() throws Exception{
		String expectedResult="Validating Home page Megamenu Specialty and should lands on Specialty page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);		
			Common.clickElement("xpath", "//span[contains(text(),'Specialty')]");
			Thread.sleep(4000);
			String Specialty=Common.getText("xpath", "//span[contains(text(),'Specialty')]");
			Assert.assertEquals(Specialty, "Specialty");
			report.addPassLog(expectedResult, "Should redirect to Specialty page", "Redirected to Specialty Page successfully", Common.getscreenShotPathforReport("Specialty Page success"));
		
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Specialty page", "Not Redirected to Specialty page", Common.getscreenShotPathforReport("Specialty page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}

}
