package TestComponent.Admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class HydroflaskAdmin {

	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Map<String, Map<String,String> > cronJobData=new HashMap<String, Map<String,String>>();
	static Map<String, Map<String,String> > orderData=new HashMap<String, Map<String,String>>();
	static Map<String, Map<String,String> > importOrder=new HashMap<String, Map<String,String>>();
	static Map<String, Map<String,String> > exportOrder=new HashMap<String, Map<String,String>>();
	static Map<String, Map<String,String> > indexMangment=new HashMap<String, Map<String,String>>();
	static  Map<String,String> GoogleInsight=new HashMap<String,String>();
	static  Map<String,Boolean> helthCheckStatus=new HashMap<String,Boolean>();
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void createAccount() throws Exception {

		try {
	       AdminLogin();
       
	       
	       //CronJob Status
	       navigateCronTaskList();
	        GetCronTaskStatus();
	        System.out.println(cronJobStatus());
	        
	        
	       //ExportExecutionlog
	        navigateExportExecutionLog();
	        GetOrderExportStatus();
	        System.out.println(OrderExportProcess());
	        
	        
	        //OrderImport
	        navigateImportExecutionLog();
	        GetOrderImportStatus();
	         System.out.println(OrderExportProcess());
	         
	         //Index management
	         navigateIndexManagement();
	        GetIndexManagement();
	        System.out.println(IndexMangementStatus());
	        
	        //OrdersInformation
	       navigateOrders();
	        Thread.sleep(2000);
	        GetOrderStatus();
	        System.out.println(OrderCronStatus());
	       
	        //Processing Order
	        navigateOrders();
		       GetOrderStatus("Processing","Canada");
		       System.out.println(OrderCronStatus());
		       
		       GetOrderStatus("Processing","Default Store View");
		       System.out.println(OrderCronStatus());
		       
		       GetOrderStatus("Pending","Canada");
		       System.out.println(OrderCronStatus());
		       
		       GetOrderStatus("Pending","Default Store View");
		       System.out.println(OrderCronStatus());
		       
		      //Googleinsight
	       // checkGoogleInsights("https://www.hydroflask.com/");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
public HydroflaskAdmin() {
	
		
	/*	excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;*/
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}

	public void checkGoogleInsights(String url) throws Exception
	{
		Common.getDriver().get("https://developers.google.com/speed/pagespeed/insights/");
		Common.textBoxInput("xpath", "//input[@name='url']", url);
		Common.actionsKeyPress(Keys.ENTER);
		Common.isElementVisibleOnPage(30, "xpath", "//*[@id=\"page-speed-insights\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/a/div[2]");
		String mobile=Common.getText("xpath", "//*[@id=\"page-speed-insights\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/a/div[2]");
		Common.clickElement("xpath", "//div[text()='Desktop']");
		String desktop=Common.getText("xpath", "//*[@id=\"page-speed-insights\"]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[1]/a/div[2]");
		System.out.println("mobile=======>"+mobile);
		System.out.println("mobile=======>"+desktop);
		GoogleInsight.put("Mobile", mobile);
		GoogleInsight.put("Desktop", desktop);
	}
	public void AdminLogin() throws Exception
	{
		Common.textBoxInput("id", "username", "manojk");
		Common.textBoxInput("id", "login", "b{?e\\Gm=c8qDH9p!");
		Common.clickElement("xpath", "//button[@class='action-login action-primary']");
		Sync.waitElementPresent("xpath", "//span[text()='Dashboard']");
	}
	
	public void GetCronTaskStatus() throws Exception
	{	
		Thread.sleep(5000);
		List<WebElement> rows=Common.findElements("xpath", "//*[@id='container']/div/div[3]/table/tbody/tr[*]");
		Map<String, Map<String,String> > cronData=new HashMap<String, Map<String,String>>();
		if(rows.size()<1)
		{
			Thread.sleep(5000);
			rows=Common.findElements("xpath", "//*[@id='container']/div/div[3]/table/tbody/tr[*]");	
		}
		for(int i=1;i<=rows.size();i++)
		{
			Map<String,String> data=new HashMap();
			List<WebElement> columns=Common.findElements("xpath", "//*[@id='container']/div/div[3]/table/tbody/tr["+i+"]/td");
			for(int j=2;j<=columns.size();j++)
			{
				String header=Common.getText("xpath", "//*[@id='container']/div/div[3]/table/thead/tr/th["+j+"]/span");
				String value=Common.getText("xpath", "//*[@id='container']/div/div[3]/table/tbody/tr["+i+"]/td["+j+"]");
				if(!data.containsKey(header))
				{
					data.put(header, value);
				}
			}
			cronData.put(data.get("Job Code"), data);
			
		}
		cronJobData=cronData;
		System.out.println(cronData);
	}
		
		public boolean cronJobStatus()
		{
			boolean status=true;
			
			Set<String> keys=cronJobData.keySet();
			//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			//Date date = format.parse(string);
			for(String key:keys)
			{
				String statusValue=cronJobData.get(key).get("Status");
				if(!statusValue.equalsIgnoreCase("pending") || statusValue.equalsIgnoreCase("success"))
				{
					status=false;
					break;
				}
			}
			helthCheckStatus.put("cronJobStatus", status);
			return status;
			
		}
		public void GetOrderExportStatus() throws Exception
		{	
			Thread.sleep(5000);
			List<WebElement> rows=Common.findElements("xpath", "//*[@id='xtento_orderexport_log_grid_table']/tbody/tr[*]");
			Map<String, Map<String,String> > cronData=new HashMap<String, Map<String,String>>();
			for(int i=1;i<=rows.size();i++)
			{
				Map<String,String> data=new HashMap();
				List<WebElement> columns=Common.findElements("xpath", "//*[@id='xtento_orderexport_log_grid_table']/tbody/tr["+i+"]/td");
				for(int j=2;j<=columns.size();j++)
				{
					String header=Common.getText("xpath", "//*[@id='xtento_orderexport_log_grid_table']/thead/tr/th["+j+"]/span");
					String value=Common.getText("xpath", "//*[@id='xtento_orderexport_log_grid_table']/tbody/tr["+i+"]/td["+j+"]");
					data.put(header, value);
				}
				cronData.put(data.get("Export Type"), data);
				
			}
			exportOrder=cronData;
		System.out.println(cronData);
	}
		
		public void GetOrderImportStatus() throws Exception
		{	
			Thread.sleep(5000);
			List<WebElement> rows=Common.findElements("xpath", "//*[@id='xtento_trackingimport_log_grid_table']/tbody/tr[*]");
			Map<String, Map<String,String> > cronData=new HashMap<String, Map<String,String>>();
			for(int i=1;i<=rows.size();i++)
			{
				Map<String,String> data=new HashMap();
				List<WebElement> columns=Common.findElements("xpath", "//*[@id='xtento_trackingimport_log_grid_table']/tbody/tr["+i+"]/td");
				for(int j=2;j<=columns.size();j++)
				{
					String header=Common.getText("xpath", "//*[@id='xtento_trackingimport_log_grid_table']/thead/tr/th["+j+"]/span");
					String value=Common.getText("xpath", "//*[@id='xtento_trackingimport_log_grid_table']/tbody/tr["+i+"]/td["+j+"]");
					data.put(header, value);
				}
				cronData.put(data.get("Export Type"), data);
				
			}
			importOrder=cronData;
		System.out.println(importOrder);
	}
		
		public boolean OrderExportProcess()
		{
			boolean status=true;
			
			Set<String> keys=exportOrder.keySet();
			//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			//Date date = format.parse(string);
			for(String key:keys)
			{
				String statusValue=exportOrder.get(key).get("Result");
				if(! statusValue.equalsIgnoreCase("SUCCESS"))
				{
					status=false;
					break;
				}
			}
			helthCheckStatus.put("orderExport", status);
			return status;
			
		}
		
		public boolean OrderImportProcess()
		{
			boolean status=true;
			
			Set<String> keys=importOrder.keySet();
			//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			//Date date = format.parse(string);
			for(String key:keys)
			{
				String statusValue=importOrder.get(key).get("Result");
				if(! statusValue.equalsIgnoreCase("SUCCESS"))
				{
					status=false;
					break;
				}
			}
			helthCheckStatus.put("orderExport", status);
			return status;
			
		}
		
		public boolean OrderCronStatus()
		{
			boolean status=true;
			
			Set<String> keys=orderData.keySet();
			//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			//Date date = format.parse(string);
			if(keys.size()>0) {
			for(String key:keys)
			{
				String statusValue=orderData.get(key).get("Status");
				if(!statusValue.equalsIgnoreCase("Processing") || statusValue.equalsIgnoreCase("success"))
				{
					status=false;
					break;
				}
				
			}}
			helthCheckStatus.put("OrderCronStatus", status);
			return status;
			
		}
		
		
		public boolean IndexMangementStatus()
		{
			boolean status=true;
			
			Set<String> keys=indexMangment.keySet();
			//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			//Date date = format.parse(string);
			for(String key:keys)
			{
				String statusValue=indexMangment.get(key).get("Status");
				if(! statusValue.equalsIgnoreCase("READY"))
				{
					status=false;
					break;
				}
			}
			helthCheckStatus.put("IndexManagement", status);
			return status;
			
		}
		
		public void GetIndexManagement() throws Exception
		{	
			Thread.sleep(3000);
			List<WebElement> rows=Common.findElements("xpath", "//*[@id='gridIndexer_table']/tbody/tr[*]");
			Map<String, Map<String,String> > cronData=new HashMap<String, Map<String,String>>();
			for(int i=1;i<=rows.size();i++)
			{
				Map<String,String> data=new HashMap();
				List<WebElement> columns=Common.findElements("xpath", "//*[@id='gridIndexer_table']/tbody/tr["+i+"]/td");
				for(int j=2;j<=columns.size();j++)
				{
					String header=Common.getText("xpath", "//*[@id='gridIndexer_table']/thead/tr/th["+j+"]/span");
					String value=Common.getText("xpath", "//*[@id='gridIndexer_table']/tbody/tr["+i+"]/td["+j+"]");
					data.put(header, value);
				}
				cronData.put(data.get("Indexer"), data);
				
			}
			indexMangment=cronData;
		System.out.println(cronData);
	}
	
		public void GetOrderStatus() throws Exception
		{	
			Thread.sleep(5000);
			List<WebElement> rows=Common.findElements("xpath", "//*[@id='container']/div/div[4]/table/tbody/tr[*]");
			Map<String, Map<String,String> > cronData=new HashMap<String, Map<String,String>>();
			for(int i=1;i<=rows.size();i++)
			{
				Map<String,String> data=new HashMap();
				List<WebElement> columns=Common.findElements("xpath", "//*[@id='container']/div/div[4]/table/tbody/tr["+i+"]/td");
				for(int j=2;j<=columns.size();j++)
				{
					String header=Common.getText("xpath", "//*[@id='container']/div/div[4]/table/thead/tr/th["+j+"]/span");
					String value=Common.getText("xpath", "//*[@id='container']/div/div[4]/table/tbody/tr["+i+"]/td["+j+"]");
					data.put(header, value);
				}
				cronData.put(data.get("Purchase Point"), data);
				
			}
		System.out.println(cronData);
		orderData=cronData;
	}
	
		public int GetOrderStatus(String status, String view) throws Exception
		{	
			Thread.sleep(5000);
			String strorevalue="1";
			if(view.equalsIgnoreCase("Default Store View"))	
			{
				strorevalue="1";
			}
			if(view.equalsIgnoreCase("Canada"))	
			{
				strorevalue="3";
			}
			Common.clickElement("xpath", "//button[text()='Filters']");
			Common.dropdown("xpath", "//select[@name='store_id']", Common.SelectBy.VALUE, strorevalue);
			//Common.actionsKeyPress(Keys.ARROW_DOWN);
			//Thread.sleep(2000);
			Common.dropdown("xpath", "//select[@name='status']", Common.SelectBy.TEXT, status);
			Common.clickElement("xpath", "//span[text()='Apply Filters']");
			List<WebElement> rows=Common.findElements("xpath", "//*[@id='container']/div/div[4]/table/tbody/tr[*]");
			
			Map<String, Map<String,String> > cronData=new HashMap<String, Map<String,String>>();
			for(int i=1;i<=rows.size();i++)
			{
				Map<String,String> data=new HashMap();
				List<WebElement> columns=Common.findElements("xpath", "//*[@id='container']/div/div[4]/table/tbody/tr["+i+"]/td");
				for(int j=2;j<=columns.size();j++)
				{
					String header=Common.getText("xpath", "//*[@id='container']/div/div[4]/table/thead/tr/th["+j+"]/span");
					String value=Common.getText("xpath", "//*[@id='container']/div/div[4]/table/tbody/tr["+i+"]/td["+j+"]");
					data.put(header, value);
				}
				cronData.put(data.get("Purchase Point"), data);
				
			}
			String noOfRecords=Common.getText("xpath", "//*[@id='container']/div/div[2]/div[2]/div[2]/div/div[1]/div").split("records found")[0].trim();
			System.out.println("no of orders=====>"+noOfRecords);
		System.out.println(cronData);
		orderData=cronData;
		return Integer.parseInt(noOfRecords);
	}
	
	public void navigateCronTaskList() throws InterruptedException
	{
	
		Common.clickElement("xpath", "//span[text()='Reports']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//*[@id='menu-magento-reports-report']/div/ul/li[5]/ul/li[2]/div/ul/li[5]/a");
	}
	
	public void navigateOrders() throws InterruptedException
	{
		Common.clickElement("xpath", "//span[text()='Sales']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//*[@id='menu-magento-sales-sales']/div/ul/li/ul/li[1]/div/ul/li[1]/a");
	}
	
	public void navigateIndexManagement() throws InterruptedException
	{
		Common.clickElement("xpath", "//span[text()='System']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//*[@id='menu-magento-backend-system']/div/ul/li[2]/ul/li[1]/div/ul/li[2]/a");
	}
	
	public void navigateExportExecutionLog() throws InterruptedException
	{
		Common.clickElement("xpath", "//span[text()='Sales']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//*[@id='menu-magento-sales-sales']/div/ul/li/ul/li[2]/div/ul/li[2]/a");
	}
	
	public void navigateImportExecutionLog() throws InterruptedException
	{
		Common.clickElement("xpath", "//span[text()='Sales']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//*[@id='menu-magento-sales-sales']/div/ul/li/ul/li[3]/div/ul/li[2]/a");
	}
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\AdminConfig.properties");
		  Login.signIn();
		 
		  
	  }

}
