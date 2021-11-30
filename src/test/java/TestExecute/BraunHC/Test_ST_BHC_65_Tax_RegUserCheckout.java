package TestExecute.BraunHC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class Test_ST_BHC_65_Tax_RegUserCheckout {

		String datafile = "BraunHC//BraunHCTestData.xlsx";	
			
			BraunHCHelper BraunHC=new BraunHCHelper(datafile);
			
			@Test(priority=1)
     		public void RegUserCheckout() throws Exception {

				try {
					String addressFile = "BraunHC//StreetAddresswithZipcode.xlsx";
					BraunHC.RegprepareTaxData("BraunTaxDetails_Reg.xlsx");
					//BraunHC.AGREEPROCEED();
					//BraunHC.PopUp();
					BraunHC.loginBraunHC("AccountDetails");
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
					String Website=BraunHC.URL();
					//BraunHC.AccountCreationBraunHC("AccountDetails");
					
					BraunHC.Select_ProductinThermometers("ThermoScan® Lens Filters - 40 count");
					BraunHC.Addtocart();
					BraunHC.ViewandEditcartPage();
					BraunHC.checkoutPage();
					BraunHC.NewAdressfrom();
					BraunHC. RegisterShippingAddress("Address",streetAddress,City,Zipcode,state);
					BraunHC.AddressVerfication();
					//BraunHC.ShippingMethods();
					HashMap<String,String> data=BraunHC.taxValidation(tax,state);
					BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetails");		
					//BraunHC.RegistereduserOrderSuccesspage();
					
					 String OrderId=BraunHC.order_Verifying();
					 
			           // String OrderId="12345";
			            System.out.print(data);
					 BraunHC.writeResultstoXLSxReg(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
			           
					}
					
				catch (Exception e) {
					Common.closeAll();
					startTest();
					//Assert.fail(e.getMessage(), e);s
				} 
					}
					}}
					catch (Exception e) {
						
						Assert.fail(e.getMessage(), e);
			}}
			

			@BeforeTest
		    public void startTest() throws Exception {
			//System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
				System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
				   	    
		    Login.signIn();
		    }
			
			
			@AfterTest
			public void clearBrowser()
			{
				
	Common.closeAll();

			}
		}
