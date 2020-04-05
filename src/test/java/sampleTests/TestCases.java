package sampleTests;

import java.io.IOException;
import java.util.logging.Logger;
import pageobjects.Common;
import pageobjects.CreateAccount;
import pageobjects.Login;
import pageobjects.PaymentProcess;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends TestBaseClass {

	CreateAccount createAccount_object;
	Login login_object;
	PaymentProcess paymentProcess_object;
	String pageURL = "http://automationpractice.com/index.php?controller=my-account";
	private static final Logger LOGGER = Logger.getLogger(TestCases.class.getName());

	// @Test(priority = 1,dataProvider="UserData")
	@Test(priority = 1)
	public void signUp() throws InterruptedException, IOException {
		LOGGER.info("Scenario 1 is running");
		createAccount_object = new CreateAccount(driver);
		String actual_homePageTitle = createAccount_object.verify_createUserAccount();
		Assert.assertEquals(actual_homePageTitle, Common.EXPECTED_HOMEPAGETITLE);
	}

	@Test(priority = 2)
	public void signOut() throws IOException {
		login_object = new Login(driver);
		String actual_loginPageTitle = login_object.verify_SignOut();
		Assert.assertEquals(actual_loginPageTitle, Common.EXPECTED_LOGINPAGETITLE);
	}
	 

	@Test(priority = 3)
	public void signIn() throws IOException {
		LOGGER.info("Scenario 2 is running");
		login_object = new Login(driver);
		String actual_homePageTitle = login_object.verify_SignIn();
		Assert.assertEquals(actual_homePageTitle, Common.EXPECTED_HOMEPAGETITLE);
	}

	@Test(priority = 4)
	public void selectProduct() throws IOException, InterruptedException {
		LOGGER.info("Scenario 3 is running");
		paymentProcess_object = new PaymentProcess(driver);
		String actual_successcartMessage = paymentProcess_object.verify_SelectItem();
		Assert.assertEquals(actual_successcartMessage, Common.EXPECTED_SUCCESSCARTMESSAGE);
	}

	@Test(priority = 5)
	public void checkoutandPaymentProcess() throws IOException, InterruptedException {
		LOGGER.info("Scenario 4 is running");
		paymentProcess_object = new PaymentProcess(driver);
		String actual_OrderConfirmPageTitle = paymentProcess_object.verify_checkOutandPaymentProcess();
		Assert.assertEquals(actual_OrderConfirmPageTitle, Common.EXPECTED_ORDERCONFIRMPAGETITLE);
		}
	
	@Test(priority = 6)
	public void checkamountoforder_OrderHistory() throws IOException, InterruptedException {
		LOGGER.info("Scenario 5 is running");
		paymentProcess_object = new PaymentProcess(driver);
		paymentProcess_object.verifyamountOfOrder_ProfilePage_OrderHistory();
		}

	/*
	  @DataProvider(name="UserData") public Object[][] getDataFromDataprovider(){
	  return new Object[][] {
	  {"Suhail","Mahajan","B-5,Marol","Mumbai","400059","9137060625"} }; }
	 */

	

}
