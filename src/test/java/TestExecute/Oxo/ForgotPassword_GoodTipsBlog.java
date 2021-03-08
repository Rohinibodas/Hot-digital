package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class ForgotPassword_GoodTipsBlog {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
  @Test
  public void ForgotPassword_GoodTipsBlog() throws Exception {

		try {
			oxo.closetheadd();
			//oxo.clickGoodTipsBlog();
			oxo.forgetpasswordGoodTipsBlock("Forgetpassoword");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
    }
}
