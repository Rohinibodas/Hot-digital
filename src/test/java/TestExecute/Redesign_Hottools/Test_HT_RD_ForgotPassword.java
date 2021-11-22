package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Login;

public class Test_HT_RD_ForgotPassword {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);

	@Test(priority=1)
	public void validationOfAccountInformation(){
		try{
			Redesign_Hottools.agreeCookiesbanner();
			Redesign_Hottools.navigateMyAccount();
			 //drybar.Accept();
			Redesign_Hottools.click_forgotpassword("RetailCustomerAccountDetails");
		     
			  
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}
	
	
	@BeforeMethod
	
	  public void startTest() throws Exception {
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "RedesignHottools\\Config_RedesignHottools_Production.properties");
		  Login.signIn(); 
	}

	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}

}
