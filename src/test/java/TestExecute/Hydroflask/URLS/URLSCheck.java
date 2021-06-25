package TestExecute.Hydroflask.URLS;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class URLSCheck {
	
	String datafile = "Hydroflask//hydroURL.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
  @Test
  public void URL_Navigation() {
	  try {
		//  String URL="https://hydro-stg-m2.heledigital.com/featured/new-arrivals";
		/*
		 * Hydro.new_arrivals("featured"); Hydro.trail_Series("featured");
		 * Hydro.limited_edition("featured");
		 */
		 Hydro. HydroflaskURLValidation("Links");
	        
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
}
	  
	  
  
  
  @BeforeTest
  public void startTest() throws Exception {
	 System.setProperty("configFile", "Hydroflask\\config.properties");
	  Login.signIn();
	  Hydro.acceptPrivecy();
	  
  }
  @AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
}
