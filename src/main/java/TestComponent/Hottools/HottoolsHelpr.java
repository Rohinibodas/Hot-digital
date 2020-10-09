package TestComponent.Hottools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.gargoylesoftware.htmlunit.Page;

import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class HottoolsHelpr {
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();

	public  HottoolsHelpr(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}
	
	
	
	public void ClicktheSignbutton() throws Exception{
		Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
		Common.clickElement("xpath", "//span[@class='authorization-link']");
		
	}
	
	public void closeCookiesbanner() throws Exception{
		Sync.waitElementPresent("id", "truste-consent-close");
		
		Common.clickElement("id", "truste-consent-close");
	}
	
	public void agreeCookiesbanner() throws Exception{
		Sync.waitElementPresent("id", "truste-consent-button");
		
		Common.clickElement("id", "truste-consent-button");
	}
	
	
	
	public void receive_updates_on(String dataSet){
		String updates=data.get(dataSet).get("receiveUpdatesOn");
		String[] updatesarry=updates.split(",");
		
		for(int i=0;i<updatesarry.length;i++){
			
			if(updatesarry[i].equals("ExclusiveOffers")){
				
				Common.clickElement("id", "exclusiveoff");
				
			}
			else if(updatesarry[i].equals("EventInvitations")){
				Common.clickElement("id", "eventoff");
			}
			else if(updatesarry[i].equals("Promotions")){
				Common.clickElement("id", "promotionoff");
			}
		}
		
		
	}
	
	public void AddressType(String dataSet){
		String adress=data.get(dataSet).get("AddressType");
		
		if(adress.equals("HomeAddress")){
			Common.clickElement("id", "promotionoff");
		}
		else if(adress.equals("SalonAddress")){
			Common.clickElement("id", "saloonaddress");
		}
		
	}

	    
	     
	
		
		

	
	public void clickCreateNewCustomerAccountButton()throws Exception{
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Create')]");
		Common.clickElement("xpath", "//a[contains(text(),'Create')]");
		
		
		
		
	}
	
	public void createNewCustomerAccount_Stylist_SalonOwner(String dataSet) throws Exception{
		ClicktheSignbutton();
		clickCreateNewCustomerAccountButton();
		
		Sync.waitElementPresent("div-toggle");
		Common.dropdown("id", "div-toggle", SelectBy.TEXT, "Retail Customer");  
		

		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "middlename", data.get(dataSet).get("MiddleName"));
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
		
		
		Common.textBoxInput("id", "date_field", data.get(dataSet).get("License Expiration Date"));
		
		Common.textBoxInput("id", "license_number", data.get(dataSet).get("License Number"));
		
		Common.textBoxInput("id", "state_ass_license", data.get(dataSet).get("State Associated With License"));
		
		Common.textBoxInput("id", "upload_license", data.get(dataSet).get("Upload License"));
		
		
		Common.dropdown("xpath", "//select[@id='estimated_no_cus']", SelectBy.TEXT, data.get(dataSet).get("Estimated Number Of Weekly Customers"));
		
		
		Common.textBoxInput("id", "phone_number", data.get(dataSet).get("Telephone"));
		
		
	}
	
	
	public void createNewCustomerAccount_RetailCustomer(String dataSet) throws Exception{
		Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
		Common.clickElement("xpath", "//span[@class='authorization-link']");
		
		
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Create')]");
		Common.clickElement("xpath", "//a[contains(text(),'Create')]");
		
		
		Sync.waitElementPresent("div-toggle");
		Common.dropdown("id", "div-toggle", SelectBy.TEXT, "Retail Customer");  
		
		
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
	//	Common.textBoxInput("id", "middlename", data.get(dataSet).get("MiddleName"));
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
		
		Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
		
		
		Thread.sleep(5000);
		
		Common.clickElement("xpath", "//button[@title='Create an Account']");
		
		//
		
	//	Create an Account
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
		
    	String cap=	Common.findElement(By.xpath("//img[@class='captcha-img']")).getAttribute("value");
		System.out.println(cap+"cap with class");
		
		String capid=	Common.findElement(By.xpath("//div[@id='captcha-container-user_create']")).getAttribute("value");
		System.out.println(capid+"cap with id");
 	
	}
	
	
	
	
	public void singin(String dataSet) throws Exception{
		
		//agreeCookiesbanner();
		
		Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
		Common.clickElement("xpath", "//span[@class='authorization-link']");
		
		
		Sync.waitElementPresent("xpath", "//a[text()='sign in']");
		Common.clickElement("xpath", "//a[text()='sign in']");
		
		
		Sync.waitElementPresent("id", "email");
		
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		
		Sync.waitElementPresent("xapth","//fieldset[@class='fieldset login']//div[3]");
		Thread.sleep(4000);
		Sync.waitElementPresent("id", "send2");
		Common.clickElement(By.id("send2"));
		
	}
	
	public void searchingProducts(String dataSet) throws Exception{
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//button[@title='Back To Top']");
		
		Sync.waitElementPresent("xpath", "//a[@title='Search']");
		
		Common.clickElement("xpath", "//a[@title='Search']");
		
		
		Sync.waitElementPresent("id", "search");
		Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
		
		Sync.waitElementClickable("xpath", "//button[@title='Search']");
		Common.clickElement(By.xpath("//button[@title='Search']"));
		
		}
	
	
	public void AddToCartProduct(String dataSet) throws Exception{
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
	//	Sync.waitElementPresent("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");
		
		Thread.sleep(6000);
		Common.clickElement("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(6000);
		Sync.waitElementPresent("id", "product-addtocart-button");
		Common.clickElement(By.id("product-addtocart-button"));
		Thread.sleep(5000);
		Sync.waitElementPresent("id", "top-cart-btn-checkout");
		Common.clickElement(By.id("top-cart-btn-checkout"));
		
		//a[@title='Mini Travel Dryer in Mint']//following::button[1]
	}
	
	
	public void sp(String dataSet)throws Exception{
		Thread.sleep(7000);
		Common.implicitWait();
	List<WebElement> shippinglabels=	Common.findElements("xpath", "//div[contains(@name,'shippingAddress')]//input[1]");
		
		for(int i=0;i<shippinglabels.size();i++){
			
			String attribute=shippinglabels.get(i).getAttribute("name");
			switch (attribute) {
			
			case "firstname":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("FirstName"));
				break;
			case "lastname":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("LastName"));
				break;
			case "street[0]":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("Street"));
				break;
			case "city":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("City"));
				break;
			
			
		}
		
		}
		Sync.waitElementPresent("xpath", "//select[@name='region_id']");
		Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Sync.waitElementPresent("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));
	
		
		Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));

		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
	}
	
	public void shippingAddress(String dataSet) throws Exception{
        Thread.sleep(7000);
		Common.implicitWait();
		Sync.waitElementPresent("name", "firstname");
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Thread.sleep(7000);
		Sync.waitElementPresent("name", "lastname");
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Thread.sleep(8000);
		Sync.waitElementPresent("name", "street[0]");
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Thread.sleep(7000);
		Sync.waitElementPresent("name", "city");
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Sync.waitElementPresent("xpath", "//select[@name='region_id']");
		Thread.sleep(7000);
		Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Sync.waitElementPresent("name", "postcode");
		Thread.sleep(7000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(7000);
		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));
	
		Thread.sleep(7000);
		Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
		Thread.sleep(5000);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		
	}
	
	public void addCardDetiles(String dataSet) throws Exception{
		Thread.sleep(7000);
		Common.clickElement(By.id("ime_paymetrictokenize"));
		Thread.sleep(7000);
		
		Common.switchFrames("paymetric_xisecure_frame");
		
		Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
		
		
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		
		
		Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		
		Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
		Common.switchToDefault();
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//*[@id='iosc-summary']/div[5]/button");
		
		Common.clickElement(By.xpath("//*[@id='iosc-summary']/div[5]/button"));
		
		
		
		Thread.sleep(6000);
		Common.clickElement("xpath","//button[contains(@class,'action-accept')]");
	}
	public void LogOut() throws Exception{
		
		Thread.sleep(8000);
		
		Common.clickElement(By.xpath("//div[@class='panel header']//ul[3]/li[2]"));
		
	
		Thread.sleep(3000);
		Common.clickElement(By.xpath("//div[@class='panel header']//ul[3]/li[2]/div/ul/li[3]"));
	}
}
