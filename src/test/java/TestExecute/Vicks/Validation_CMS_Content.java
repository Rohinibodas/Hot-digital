package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class Validation_CMS_Content{
	String datafile = "Vicks//vicks URLs.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void Validation_CMS_Content() throws Exception {

		try {
			vicks.Verifyhomepage();
			//vicks.ProductSupport("Featured");
			vicks.contactUS("Featured");
			vicks.FAQS("Featured");
			vicks.Humdifiers("Featured");
			vicks.SinusInhalers("Featured");
			vicks.FiltersAccessories("Featured");
			vicks.Heleoftroy("Featured");
			//vicks.Ourhistory("Featured");
			vicks.Blog("Featured");
			//vicks.PUR("Featured");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		
				} 
	}

	
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
		
		@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Vickshumdifier\\config.properties");
			  Login.signIn();
			 
			  
		  }

}
