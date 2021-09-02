package TestExecute.Revlon.SystemTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TEST_ST_RHT_031_ValidateMyAccountLeftNavigation {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void MyAccountLeftNavigation() throws Exception {

		try {revelon.Newslettersignup();
		revelon.acceptPrivecy();
			revelon.loginRevlon("AccountDetails");
			revelon.ValidateMyOrder();
			revelon.ValidateAddressBook();
			revelon.ValidateAddressinformation();
			revelon.ValidateProductReview();
			revelon.ValidateNewsletterSubscription();
			revelon.Validatebacktostock();
			revelon.ValidateQuestion();	
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	@BeforeMethod
	  public void startTest() throws Exception {
		 //System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
		  Login.signIn();
}
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
