package TestExecute.DryBar.SystemTC; 
 import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test; 
 import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;import TestLib.Login;
    public class TEST_ST_DB_035_Validation_Of_Filters_in_PLP{	
String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);  
      @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)  
              public void Validation_Of_Filters_in_PLP() {	
	try {			
      drybar.Accept();		
	  drybar.verifyingHomePage();		 	
	  drybar.clickHairProducts();			
      drybar.SelectShampoos();				
      drybar.FilterOptionsPLP();	

		  			 	
		  		
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

