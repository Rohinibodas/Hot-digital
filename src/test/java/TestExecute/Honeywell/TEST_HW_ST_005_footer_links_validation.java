package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_ST_005_footer_links_validation {
	String datafile = "Honeywell\\HoneyWellLinks.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void registeredUser_Checkout_CreditCard_VISA() throws Exception {

		try {
	
			honeyWell.verifyingHomePage();
			//honeyWell.productsupport();	
			//honeyWell.footerLinkalidations_Shop("footerLinkShop");
		//	honeyWell.footerValidations_aboutUs("footerLinkAboutUs");
			honeyWell.fotterValidations_HelenOfTroy("footerLinkHelenoftroyfamily");
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
