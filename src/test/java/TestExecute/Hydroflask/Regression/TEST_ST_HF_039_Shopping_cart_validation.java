package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_039_Shopping_cart_validation {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
  @Test
  public void shopping_cart_validation() {
		try{
			 Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
			 Hydro.minicart_Validation();
			// Hydro.checkOutCart();
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
//		  System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();		  
	  }
	}

 

