package TestExecute.Redesign_Hottools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//importTestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class TEST_HT_RD_Register_TAX_Code {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GUEST_TAX_Code() throws Exception {
		try{
			
			
			String addressFile = "Redesign_Hottools//Redesign_HottoolsTaxAddress.xlsx";
			Redesign_Hottools.prepareTaxData("Redesign_HottoolsTaxDetails_Register.xlsx");
			 Redesign_Hottools.Accept();
			  Redesign_Hottools.verifyingHomePage();
			  Redesign_Hottools.navigateMyAccount();
			  Redesign_Hottools.loginApplication("AccountDetails");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
			for(int i=0;i<addressVal.get(state).size();i++)
			{
		
			try {
				
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");
			
			
			String Website=Redesign_Hottools.URL();
		
			  Redesign_Hottools.clickHair_Tools();
			  Redesign_Hottools.Selectproduct(); 
			 // Redesign_Hottools.searchProducts("ProductName");
              Redesign_Hottools.Verify_PDP();
			Redesign_Hottools.clickAddtoBag();
			Redesign_Hottools.clickminiCartButton();
			Redesign_Hottools.clickCheckoutButton();		   
			Redesign_Hottools.addDeliveryAddress_register("ShippingAddress",streetAddress,City,Zipcode,state);
		    HashMap<String,String> data=Redesign_Hottools.taxValidation(tax,state);
		    Redesign_Hottools.creditCard_payment("CCVisa");
		    String OrderId= Redesign_Hottools.Verify_order();
		    //String OrderId="12345";
		  
		    
		    
            //String OrderId="12345";
            System.out.print(data);
            Redesign_Hottools.writeResultstoXLSx(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"));
			
			}
			
 
			
			catch (Exception e) {
				Common.closeAll();
				startTest();
				//Assert.fail(e.getMessage(), e);
			} 
				}
				}}
				catch (Exception e) {
					
					Assert.fail(e.getMessage(), e);
		}
	}
  @AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		//System.setProperty("configFile", "Redesign_Hottools\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
