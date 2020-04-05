package sampleTests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import pageobjects.Common;

public class TestBaseClass {

	WebDriver driver;
	String pageURL = "http://automationpractice.com/index.php?controller=my-account";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get(Common.BASEURL);
		driver.manage().window().maximize();
	}

	
	 @AfterTest 
	 public void close() { driver.close(); }
	 

}
