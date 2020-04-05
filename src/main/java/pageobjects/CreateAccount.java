package pageobjects;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccount {

	WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	Select dropdown;
	
    // xpath
	@FindBy(xpath ="//a[@class='login']") WebElement signIn;
	@FindBy(xpath="//input[@id='email_create']") WebElement element_emailAddress;
	@FindBy(xpath="//form[@id='create-account_form']//span[1]") WebElement element_createAccount;
	@FindBy(xpath="//div[@class='clearfix']//div[1]//label[1]") WebElement element_title;
	@FindBy(xpath="//input[@id='customer_firstname']") WebElement element_firstName;
	@FindBy(xpath="//input[@id='customer_lastname']") WebElement element_lastName;
	@FindBy(xpath="//input[@id='passwd']")WebElement element_password;
	@FindBy(xpath="//select[@id='days']") WebElement day;
	@FindBy(xpath="//select[@id='months']") WebElement months;
	@FindBy(xpath="//select[@id='years']")  WebElement years;
	@FindBy(xpath="//input[@id='address1']") WebElement address;
	@FindBy(xpath="//input[@id='city']") WebElement element_city;
	@FindBy(xpath="//select[@id='id_state']") WebElement element_state;
	@FindBy(xpath="//input[@id='postcode']") WebElement element_postalCode;
	@FindBy(xpath="//select[@id='id_country']") WebElement element_country;
	@FindBy(xpath="//input[@id='phone_mobile']") WebElement element_mobileNo;
	@FindBy(xpath="//input[@id='alias']") WebElement element_alias;
	@FindBy(xpath="//span[contains(text(),'Register')]") WebElement element_register;
	
	// Constructor
	public CreateAccount(WebDriver driver) throws IOException {
		this.driver = driver;
		prop = Common.get_PropertiesFilesData();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		
	}

	// This function is used for Registration:- create user account in system
	public String verify_createUserAccount() throws InterruptedException, IOException {
		signIn.click();
		element_emailAddress.sendKeys(prop.getProperty("email"));
		element_createAccount.click();
		wait.until(ExpectedConditions.visibilityOf(element_title)).click();
		element_firstName.sendKeys(prop.getProperty("firstName"));
		element_lastName.sendKeys(prop.getProperty("lastName"));
		element_password.sendKeys(Common.getPassword_AfterDecode());
		Common.selectdropdownByValue(day, "17");
		Common.selectdropdownByValue(months, "7");
		Common.selectdropdownByValue(years, "1989");
		address.sendKeys(prop.getProperty("address"));
		element_city.sendKeys(prop.getProperty("city"));
		Common.selectdropdownByVisibleText(element_state, "Indiana");
		element_postalCode.sendKeys(prop.getProperty("postalCode"));
		Common.selectdropdownByVisibleText(element_country, "United States");
		element_mobileNo.sendKeys(prop.getProperty("mobileNo"));
		if (element_alias.getText() == null) {
			element_alias.sendKeys("My address");
		}
		element_register.click();
		return (driver.getTitle());

	}

}
