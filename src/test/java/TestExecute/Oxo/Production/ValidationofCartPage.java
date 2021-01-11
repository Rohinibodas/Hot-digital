
  package TestExecute.Oxo.Production;

  import org.testng.Assert;
  import org.testng.annotations.AfterTest;
  import org.testng.annotations.BeforeMethod;
  import org.testng.annotations.Test;

  import TestComponent.oxo.OxoHelper;
  import TestComponent.oxo.OxoHelperLive;
  import TestLib.BaseDriver;
  import TestLib.Common;
  import TestLib.Login;

  public class ValidationofCartPage {
  	
  	String datafile = "oxo//OxoTestData.xlsx";	
  	OxoHelperLive oxo=new OxoHelperLive(datafile);
  	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

    public void ValidationofCartPage() {
  		try{
  			oxo.closetheadd();
  			oxo.acceptPrivecy();
			oxo.loginOxo("AccountDetails");
			oxo.clickBaby_Toddler();
			oxo.addproducts("1");
		    oxo.clickViewCart();
			oxo.CheckPost();
  		
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
