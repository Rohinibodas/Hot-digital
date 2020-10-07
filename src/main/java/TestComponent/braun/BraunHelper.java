package TestComponent.braun;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;

public class BraunHelper {
	
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	
	
	public  BraunHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}
	public void CreateNewAccount(String dataSet) throws Exception{
		
	
		Sync.waitElementClickable(30, By.xpath("//div[@title='Close']"));
		Common.mouseOverClick("xpath","//div[@title='Close']" );
		
		Sync.waitElementClickable(30, By.xpath("//button[@id='btn-bar']"));
		Common.mouseOverClick("xpath", "//button[@id='btn-bar']");
		
		Sync.waitElementClickable(30, By.xpath("//a[text()='Register']"));
		Common.mouseOverClick("xpath", "//a[text()='Register']");
		
		
		Common.refreshpage();
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//input[@id='capture_signIn_firstName']"));
		Common.textBoxInput("id", "capture_signIn_firstName",data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "capture_signIn_lastName",data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "capture_signIn_newPassword", data.get(dataSet).get("Password"));
		
		Common.mouseOverClick("id", "showPassword");
		
		//String  passwordtype=Common.findElement(By.id("capture_signIn_newPassword")).getAttribute("type");
		
		/*if(passwordtype.equals("text"))
		{*/
			
			
	
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.dropdown("id","capture_signIn_birthdate_dateselectmonth", SelectBy.TEXT,data.get(dataSet).get("DateofBirth_month"));
			Common.dropdown("id","capture_signIn_birthdate_dateselectyear", SelectBy.TEXT,data.get(dataSet).get("DateofBirth_year"));
			Common.textBoxInput("id", "capture_signIn_addressPostalCode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("id", "capture_signIn_emailAddress", data.get(dataSet).get("Email"));
			Common.findElement(By.xpath("//button[@value='Register']")).click();
			
			//}
		
		
		
		
		
	}
	public void searchProduct(String dataSet) throws Exception
	{
		Thread.sleep(5000);
		Common.mouseOverClick("xpath", "//span[@id='search-icon']");
		Sync.waitElementPresent("search");
		Thread.sleep(2000);
		//Common.findElement("xapth", "//input[@id='search']").click();
		Common.textBoxInput("id", "search",  data.get(dataSet).get("ProductName"));
		Common.findElement("id", "search-icon-span").click();
		
		
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//h3[text()='"+data.get(dataSet).get("ProductName")+"']"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.mouseOverClick("xpath", "//h3[text()='"+data.get(dataSet).get("ProductName")+"']");
		//Common.findElement("xpath","//h3[text()='"+data.get(dataSet).get("ProductName")+"']").click();
		
		Sync.waitElementClickable(30, By.xpath("//span[@class='ps-button-label']"));
		
		Common.findElement("xpath","//span[@class='ps-button-label']").click();
		
		}
	public void SignInBraun(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		
		Sync.waitElementClickable(30, By.xpath("//button[@value='Register']//following::div[1]/a"));
		//
		
		Common.findElement("xpath", "//button[@value='Register']//following::div[1]/a").click();
		Thread.sleep(3000);
		Common.textBoxInput("id", "capture_signIn_signInEmailAddress",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "capture_signIn_currentPassword",data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[@title='Sign in']");
		Thread.sleep(3000);
		
	}
	
	public void productCheckout(String dataSet) throws Exception
	{
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//div[@data-action-retailer='"+data.get(dataSet).get("vendorname")+"']//button"));
		Common.mouseOverClick("xpath", "//div[@data-action-retailer='"+data.get(dataSet).get("vendorname")+"']//button");
		//Common.findElement(By.xpath("//div[@data-action-retailer='"+data.get(dataSet).get("vendorname")+"']//button")).click();
		Common.switchWindows();
	    
		Thread.sleep(3000);	
		
		Common.waitAndClick("xpath", "//button[@id='rcx-subscribe-submit-button-announce']");
		
	
		
	//	Common.findElement(By.xpath("//button[@id='rcx-subscribe-submit-button-announce']")).click();
		Thread.sleep(3000);	
		Common.textBoxInput("xpath", "//input[@type='email']",data.get(dataSet).get("AmazonEmail"));
		Common.clickElement("id", "continue");
		
		Common.textBoxInput("xpath", "//input[@type='password']",data.get(dataSet).get("amazonpassword"));
		Common.clickElement("id", "signInSubmit");
		
		
		
		
		
		Common.textBoxInput("xpath", "//input[contains(@placeholder,'Street address')]", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "//input[contains(@placeholder,'Apartment')]", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "//input[contains(@id,'enterAddressCity')]", data.get(dataSet).get("City"));
		Common.textBoxInput("xpath", "//input[contains(@id,'enterAddressStateOrRegion')]", data.get(dataSet).get("Region"));
		Common.textBoxInput("xpath", "//input[contains(@id,'enterAddressPostalCode')]", data.get(dataSet).get("postcode"));
		
		
		Common.textBoxInput("xpath", "//input[contains(@id,'enterAddressPhoneNumber')]", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//input[@type='submit']");
		
		Common.clickElement("xpath","//span[text()='Save Address']");
		
		Common.clickElement("xpath","//span[text()='Add a credit or debit card']");
		
		
		
		
		
		
	}
	
	public void addPaymentDetails(String dataSet) throws Exception
	{
		Thread.sleep(3000);   
		Common.switchFrames("xpath","//iframe[@name='ApxSecureIframe']");
		String nameofcard=data.get(dataSet).get("cardNumber");
		
        Common.textBoxInput("xpath", "//input[@name='addCreditCardNumber']",nameofcard);
		Common.textBoxInput("xpath", "//input[@name='ppw-accountHolderName']",data.get(dataSet).get("cardName"));
		Thread.sleep(3000);
		Common.dropdown("xpath", "//select[@name='ppw-expirationDate_month']", Common.SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@name='ppw-expirationDate_year']", Common.SelectBy.VALUE, data.get(dataSet).get("ExpYear"));
		Common.clickElement("xpath","//span[text()='Add your card']");
	//	Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	}

}
