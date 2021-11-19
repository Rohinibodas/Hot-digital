package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_OXO_ST_039_ValidationofWishlistFunctionality {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class,invocationCount = 1)

  public void registeredUser_Checkout_CreditCard() {
		try{
		oxo.closetheadd();
		oxo.loginOxo("AccountDetails");
		oxo.Add_product_to_Wishlist_PLP();
		oxo.Add_product_to_Wishlist_PDP();
		oxo.removeandaddtocartfrom_wishlist();

		}
catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}
	@AfterTest
	public void clearBrowser() throws Exception
	{
		Common.closeAll();
	}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo//config.properties");
		  Login.signIn();
		 
	 }
}
