package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class ValidationContentlinks {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	//@Test (priority=14) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	/*public void OxoContactUS() {
		
		try {
			oxo.ContactUS();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=2) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoFAQ() {
		
		try {
			oxo.FAQ();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=4) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoVoluntaryRecall() {
		
		try {
			oxo.VoluntaryRecall();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=3) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoProductRegistration() {
		
		try {
			oxo.ProductRegistration();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=5) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoPrivacyPolicy() {
		
		try {
			oxo.PrivacyPolicy();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=6) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoTermsandConditions() {
		
		try {
			oxo.TermsandConditions();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=7) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoTrackOrder() {
		
		try {
			oxo.TrackOrder();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=8) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoShippingInformation() {
		
		try {
			oxo.ShippingInformation();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=9) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoBetterGuarantee() {
		
		try {
			oxo.BetterGuarantee();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=10) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoGoodTipsBlog() {
		
		try {
			oxo.GoodTipsBlog();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=11) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoInventorSubmissions() {
		
		try {
			oxo.InventorSubmissions();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=12) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoCareers() {
		
		try {
			oxo.Careers();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}
	@Test (priority=13) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoInvestorRelations() {
		
		try {
			oxo.InvestorRelations();
			}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}
	}*/
	@Test (priority=1) //(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void OxoLinkValidation() {
		
		try {
			oxo.closetheadd();
			oxo.ContactUS();
			oxo.FAQ();
			oxo.ProductRegistration();
			oxo.VoluntaryRecall();
			oxo.PrivacyPolicy();
			oxo.TermsandConditions();
			oxo.TrackOrder();
			oxo.ShippingInformation();
			oxo.BetterGuarantee();
			oxo.GoodTipsBlog();
			oxo.InventorSubmissions();
			oxo.InvestorRelations();
			oxo.Careers();
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