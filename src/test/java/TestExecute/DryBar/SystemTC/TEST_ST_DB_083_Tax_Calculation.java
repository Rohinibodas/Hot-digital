package TestExecute.DryBar.SystemTC;

import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

 

public class TEST_ST_DB_083_Tax_Calculation {

	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
    public void Guest_Tax_Calculation() throws Exception {
    	try {
    		String addressFile = "DryBar//DryBarTax.xlsx";
	     	drybar.prepareTaxData("DryBarTaxDetails_Guest.xlsx");
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
			drybar.Accept();
			drybar.verifyingHomePage();
			drybar.Search_productname("ProductName");
			drybar.Select_Searched_Product();
		    drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();		   
		    drybar.click_GuestCheckOut();
		    drybar.addDeliveryAddress("ShippingAddress",streetAddress,City,Zipcode,state);
		    HashMap<String,String> data=drybar.IL_taxValidation(tax);
		    drybar.creditCard_payment("CCVisa");
		    drybar.order_Success();      
            String OrderId="12345";
            System.out.print(data);
			drybar.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
			
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
	
	@BeforeMethod
	public void startTest() throws Exception {
		//System.setProperty("configFile", "DryBar\\config.properties");
		Login.signIn();
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
