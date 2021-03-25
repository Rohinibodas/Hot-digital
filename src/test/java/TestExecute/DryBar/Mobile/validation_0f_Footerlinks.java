package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarMobile;
//import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class validation_0f_Footerlinks {
	String datafile = "DryBar//DryBarTestData.xlsx";
	DryBarMobile drybar=new DryBarMobile(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void navigation_of_footerlinks() throws Exception {

	  try{
		  drybar.Accept();
		  drybar.verifyingHomePage();
		  drybar.checkorderstatus_footerlink();
		  drybar.Returns_footerlink();
		  drybar.Shipping_Delivery_footerlink();
		  drybar.safetydata_footerlink();
		  drybar.Special_offers_footerlink();
		  drybar.warranty_footerlink();
		  drybar.Glossary_footerlink();
		  drybar.Blowout_footerlink();
		  drybar.WheretoBuy_footerlink();
		  
	 
  }
	  catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
  
	
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
  @BeforeMethod
  public void startTest() throws Exception {
	 System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn("chrome","iPad");
 }
/*@BeforeTest
@Parameters({"device"})  
  public void startTest(String Device) throws Exception {
	System.setProperty("configFile", "DryBar\\config.properties");
	Login.signIn("chrome",Device);
  
}*/
}
