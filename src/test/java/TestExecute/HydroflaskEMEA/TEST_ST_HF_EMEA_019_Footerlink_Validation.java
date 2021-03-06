package TestExecute.HydroflaskEMEA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroFlaskEMEA;

import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_EMEA_019_Footerlink_Validation {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";	
	HydroFlaskEMEA Hydro=new HydroFlaskEMEA(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void footerlink_Validation() {
		try{
	    
		Hydro.footerLinks_About_Validation();
		//Hydro.footerLinks_careers_Validation();
		Hydro.footerLinks_Press_Validation();
		Hydro.footerLinks_FAQ_Validation();
		Hydro.footerLinks_Shipping_Validation();
		Hydro.footerLinks_Returns_Validation();
		Hydro.footerLinks_Contact_Validation();
		Hydro.footerLinks_Warranty_Validation();
		Hydro.footerLinks_Privacy_policy();
		
		
		
		
		
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
//		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		  Login.signIn("chrome");
		  Hydro.acceptPrivecy();
		  //Hydro.ClosADD();*/		  
	  }


  }

