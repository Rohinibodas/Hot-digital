package TestExecute.vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_013_Headerlinks {
	String datafile = "Vicks//vicksURLs.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void headerlinkvalidation() throws Exception {

		try {
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
          vicks.validationlinksshop("shoppagetitle");
	     vicks.headLinksValidations_Learn("PageTitle");
       		vicks.validationlinkssupport("supportpagetitle");
			
			
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
//			System.setProperty("configFile", "Vickshumdifier\\config.properties");
			  Login.signIn();
			 
			  
		  }

}

