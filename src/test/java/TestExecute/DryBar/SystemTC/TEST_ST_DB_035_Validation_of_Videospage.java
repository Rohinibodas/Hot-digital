package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_035_Validation_of_Videospage {

	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validation_of_Videospage() throws Exception {
		
		
		try {
			
			
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.Click_HowTo_and_Inspo();
		drybar.Close_popup();
		drybar.Click_Videos();
		drybar.Select_ProductVideos_SubCategory();
		drybar.Select_a_Product_Video();
		drybar.Play_Video();
		drybar.Click_HowTo_and_Inspo();
		drybar.Click_Videos();
		drybar.Select_DIY_SeriesVideos_SubCategory();
		drybar.Select_DIY_Series_Video();
		drybar.Play_Video();
		drybar.Click_HowTo_and_Inspo();
		drybar.Click_Videos();
		drybar.Select_Drybar_Signature_Styles_Videos_SubCategory();
		drybar.Select_Drybar_Signatue_Style_Video();
		drybar.Play_Video();
		drybar.Click_HowTo_and_Inspo();
		drybar.Click_Videos();
		drybar.Select_OurFavorite_Videos_SubCategory();
		drybar.Select_OurFavourite_Video();
		drybar.Play_Video();
		
		
		
		
		

	    
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
		 //System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
		 
		 
		  
	  }

}

