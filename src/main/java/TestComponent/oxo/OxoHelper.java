package TestComponent.oxo;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;

public class OxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();

	/*public void navigateLoginPage()
	{
		
	}*/	
	
	public void CreateNewAccount(String dataSet) throws Exception
	{
		
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
		try {
		Sync.waitElementClickable(30, By.xpath("//span[text()='Create Account']"));
		}catch (Exception e) {
			// TODO: handle exception
			Common.findElement("xpath", "//a[@class='social-login']").click();
			if(Common.findElement("xpath", "//span[text()='Create Account']")==null)
			{
				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}
		Common.clickElement("xpath", "//span[text()='Create Account']");
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "email_address_create", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password-social", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.mouseOverClick("id", "button-create-social");
		Common.actionsKeyPress(Keys.ESCAPE);
	
	}	
	
	
	public void searchProductAndAddtoCart(String productName) throws Exception
	{
		Thread.sleep(4000);
		Common.clickElement("className", "search-tool");
		Sync.waitElementPresent("id", "search");
		if(Common.findElement("id", "search")==null)
		{
			Common.mouseOverClick("className", "search-tool");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "search", productName);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		//Sync.waitElementClickable("xpath", "//ol[@class='products list items product-items']/li[1]/following::button[1]");
		Common.clickElement("xpath", "//ol[@class='products list items product-items']/li[1]/following::button[1]");
		/*if(Common.findElement("xpath", "//button[@title='Add to Cart']")!=null)
		{
			Common.clickCheckBox("xpath", "//button[@title='Add to Cart']");
		}*/
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Thread.sleep(2000);
			
	}
	
	public void checkOut() throws Exception
	{
		Common.mouseOver("className", "minicart-wrapper");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.mouseOverClick("id", "top-cart-btn-checkout");
		Thread.sleep(4000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		
	}
	
	public void addDeliveryAddress(String dataSet) throws Exception
	{
		Sync.waitElementPresent("name", "street[0]");
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//*[contains(@name,'ko_unique_')]");
		Common.mouseOverClick("xpath", "//button[@class='button action continue primary']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(3000);
	}
	
	public void addPaymentDetails(String dataSet) throws Exception
	{
		Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	}
	
	public void loginOxo(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Sync.waitElementClickable(30, By.id("email"));
		if(Common.findElement("id", "email")==null)
		{
			Common.mouseOverClick("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass1",data.get(dataSet).get("Password"));
		Common.clickElement("id", "bnt-social-login-authentication");
		
	}
	
	public  OxoHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}
}
