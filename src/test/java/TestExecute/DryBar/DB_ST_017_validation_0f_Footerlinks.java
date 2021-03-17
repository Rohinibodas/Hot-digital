package TestExecute.DryBar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_017_validation_0f_Footerlinks {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void navigation_of_footerlinks() throws Exception {

	  try{
		  drybar.Aggree_and_proceed();
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
		  drybar.aboutUs_footerlink();
		  
	 
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
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
