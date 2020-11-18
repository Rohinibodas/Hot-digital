package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class ValidationofBlogs_Article_Links {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoBlogsPage() {
		
		try {
			oxo.closetheadd();
			oxo.Instagram();
			//oxo.Facebook();
			oxo.pinterest();
			oxo.YouTube();
			oxo.Twitter();
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
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
