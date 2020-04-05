package pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Common {

	public static final String CONFIGFILEPATH = "\\src\\test\\resources\\config\\config.properties";
	public static final String EXPECTED_HOMEPAGETITLE = "My account - My Store";
	public static final String EXPECTED_LOGINPAGETITLE = "Login - My Store";
	public static final String EXPECTED_SUCCESSCARTMESSAGE = "Product successfully added to your shopping cart";
	public static final String EXPECTED_ORDERCONFIRMPAGETITLE = "Order confirmation - My Store";
	public static final String BASEURL = "http://automationpractice.com/index.php";

	static Properties prop;
	static String password;
	static Select dropdown;

	public static Properties get_PropertiesFilesData() throws IOException {
		prop = new Properties();
		InputStream inStream = new FileInputStream(System.getProperty("user.dir") + Common.CONFIGFILEPATH);
		prop.load(inStream);
		return prop;
	}

	public static String getPassword_AfterDecode() {
		byte[] decodedBytes = Base64.getDecoder().decode(prop.getProperty("password"));
		return (password = new String(decodedBytes));

	}

	public static void selectdropdownByValue(WebElement element, String value) {
		dropdown = new Select(element);
		dropdown.selectByValue(value);

	}

	public static void selectdropdownByVisibleText(WebElement element, String text) {
		dropdown = new Select(element);
		dropdown.selectByVisibleText(text);

	}

}
