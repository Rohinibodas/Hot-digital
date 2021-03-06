package TestExecute.Oxo.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoMobileHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class CartPage_Validation {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileHelper oxo=new OxoMobileHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void CartPage_Validation() {
		try{
			oxo.closetheadd();
			//oxo.NavigationToggle();
			//oxo.loginOxo("AccountDetails");
			oxo.clickBaby_Toddler();
			oxo.addproducts("1");
		    oxo.clickViewCart();
			oxo.CheckPost();
			
  }
	catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}
	@AfterTest
	public void clearBrowser() throws Exception
	{
		Common.closeAll();
		
	}
	
	/*@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","iPad");
	 }*/
	@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn("chrome",Device);
	  }
}
