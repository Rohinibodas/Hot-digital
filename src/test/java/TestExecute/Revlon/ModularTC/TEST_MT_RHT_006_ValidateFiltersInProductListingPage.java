package TestExecute.Revlon.ModularTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TEST_MT_RHT_006_ValidateFiltersInProductListingPage {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	@Test(priority=1)
	public void ValidatingFilterinPLP() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.Newslettersignup();
			revelon.selectioncategory();
			revelon.PLPfilterwithColour();
			revelon.ClearFilter();
			revelon.PLPfilterwithCollections();
			revelon.ClearFilter();
			revelon.PLPfilterwithHeat();
			revelon.ClearFilter();
			revelon.PLPfilterwithSize();
			revelon.ClearFilter();
			revelon.PLPfilterwithTechnology();
			revelon.ClearFilter();
			revelon.PLPfilterwithType();
			revelon.ClearFilter();
			revelon.PLPfilterwithDualVoltage();
			revelon.ClearFilter();
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
		  Login.signIn();
}
	
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();
	}

}
