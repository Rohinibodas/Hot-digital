package TestExecute.BraunHC;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Test_ST_BHC_66_Validate_Address_book_AVS {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void Address_Book() throws Exception {

		try {
			
			BraunHC.AGREEPROCEED();
	        BraunHC.PopUp();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Address_Book();
			BraunHC.Validate_Address_book_AVS("Addressbook");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	


	@BeforeTest
    public void startTest() throws Exception {
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
		//System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
		   	    
    Login.signIn();
    }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
