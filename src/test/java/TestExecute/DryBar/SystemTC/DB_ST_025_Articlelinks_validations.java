package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_025_Articlelinks_validations {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Articlelinks() {
		try {
			
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.Facebook();
			  drybar.Instagram();
			  drybar.Twitter();
			  drybar.Pinterest();
			  drybar.Youtube();
			  
			  
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
			 System.setProperty("configFile", "DryBar\\config.properties");
			  Login.signIn();
			 
			  
		  }


		

}

