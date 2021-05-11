package TestExecute.BraunEMEAProduction.BraunDEP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_Changeproductquantity {
	//String datafile = "BraunEMEA//BraunUKTestData.xlsx";
	String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
	BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
	@Test(priority=1)
	public void ChangeProductQuntity() throws Exception {

		try {
			
			BraunUK.Acceptcookies();
			//BraunUK.closepopup();
			  //BraunUK.StoreSelection("Germany");
			BraunUK.GERProductselection();
			BraunUK.ChangeproductQuantity();
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/

	
	 @BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
		  Login.signIn("broswer");
		  
	  }
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "BraunUK\\config.properties");
		  Login.signIn(browser);
		  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}


}


