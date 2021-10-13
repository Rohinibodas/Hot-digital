package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_049_Footerlink_Validation {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void footerlink_Validation() {
		try{
		Hydro.footerLinks_AboutUs_Validation();
		Hydro.footerLinks_DealerCentral_Validation();
		Hydro.footerLinks_Personalize_Validation();
		Hydro.footerLinks_Affiliates_Validation();
		Hydro.footerLinks_ProDeal_Validation();
		Hydro.footerLinks_FAQ_Validation();
		Hydro.footerLinks_Contact_Validation();
		Hydro.footerLinks_Shipping_Validation();
		Hydro.footerLinks_Returns_Validation();
		Hydro.footerLinks_Warranty_Validation();
		Hydro.footerLinks_Track_Your_Order_Validation();
		//Hydro.footerLinks_Refer_aFriend_Validation();
		Hydro.footerLinks_Parks_For_All_Validation();
		//Hydro.footerLinks_New_Arrivals_Validation();
		Hydro.footerLinks_Bottles_Validation();
		Hydro.footerLinks_Customize_Validation();
		Hydro.footerLinks_OutdoorKitchen_Validation();
		//Hydro.footerLinks_Refer_aFriend_Validation();
		
		//Hydro.footerLinks_Careers_Validation();
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
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();		  
	  }


  }

