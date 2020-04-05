package pageobjects;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver;
	Properties prop;

	// xpath
	@FindBy(xpath = "//a[@class='logout']") WebElement signOut;
	@FindBy(xpath = "//a[@class='login']")  WebElement signIn;
	@FindBy(xpath = "//input[@id='email']") WebElement email;
	@FindBy(xpath = "//input[@id='passwd']")WebElement password;
	@FindBy(xpath = "//p[@class='submit']//span[1]") WebElement submit;

	// Constructor
	public Login(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		prop = Common.get_PropertiesFilesData();
		
	}

	// This function is used to sign out from system
	public String verify_SignOut() {
		signOut.click();
		return (driver.getTitle());
	}

	// This function is used for sign-in
	public String verify_SignIn() {
		signIn.click();
		email.sendKeys(prop.getProperty("email"));
		password.sendKeys(Common.getPassword_AfterDecode());
		submit.click();
		return (driver.getTitle());
	}

}
