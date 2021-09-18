package TestExecute.Oxo;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class OXO_TAX_Caluclation {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void  Validation_CleaningAndOrganization_Header() throws Exception {

		try {

			String addressFile = "OXO//OXOAddressTax.xlsx";
			oxo.prepareTaxData("OXOTaxDetails_Guest.xlsx");
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
			

			oxo.closetheadd();
			oxo.CookingAndBaking();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.addDeliveryAddress("Address",streetAddress,City,Zipcode,state);
			oxo.selectGroundShippingMethod();
			oxo.clickAcceptingaddress();
			HashMap<String,String> data=oxo.taxValidation(tax);
			oxo.Click_CreditCard();
			oxo.creditCard_payment("PaymentDetails");
			oxo.VerifyaingConformationPage();
			//String OrderId="12345";
			 String OrderId=oxo.order_Verifying();
			oxo.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
            //honeyWell.writeResultstoXLSx("OrderId");
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
	}}
	
	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

       
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
		//Common.closeAll();

	}

}