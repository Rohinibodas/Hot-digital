package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_PUR_ST_036_Validation_of_Privacy_And_TermsOfUse {
	
	String datafile = "PUR//PUR_TestData.xlsx";	
	PurHelper PUR=new PurHelper(datafile);
	
	@Test(priority=1)
	public void validatePrivacy_TermsOfUse(){

		try{
			//PUR.AgreeAndProceed();
			PUR.privacy();
			PUR.Terms_OF_Use();
			
				
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	
/*	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn("edge");
		   }
	*/
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn(browser);
	}

	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}

}
