package pageobjects;

import java.util.Properties;
import java.util.logging.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentProcess {
	WebDriver driver;
	Properties prop;
	JavascriptExecutor js;
	WebDriverWait wait;
	Actions action;
	static String actual_orderConfirm_TotalCost=null;
	private static final Logger LOGGER = Logger.getLogger(PaymentProcess.class.getName());

	// xpath
	@FindBy(xpath = "//a[@class='sf-with-ul'][contains(text(),'Women')]") WebElement category;
	@FindBy(xpath = "//img[@class='replace-2x img-responsive'][@alt='Faded Short Sleeve T-shirts']") WebElement image;
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//span[text()='Quick view']")
	WebElement click_View;
	@FindBy(xpath = "//iframe[contains(@id,'fancybox-frame')]") WebElement frame;
	@FindBy(xpath = "//form[@id='buy_block']//div/div[2]//p[1]//a[2]//span//i[@class='icon-plus']") WebElement add_Qty;
	@FindBy(xpath = "//div[@class='box-cart-bottom']//p[@id='add_to_cart']//span[contains(text(),'Add to cart')]") WebElement addtoCart;
	@FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2[1]") WebElement success_Message;
	@FindBy(xpath = "//div[@class='button-container']//span[contains(text(),'Proceed to checkout')]") WebElement proceed_Checkout;
	@FindBy(xpath = "//td[@class='price']//span[@id='total_price']") WebElement shoppingCart_summaryTotalCost;
	@FindBy(xpath = "//span[@class='price']//strong") WebElement orderConfirm_totalCost;

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement summary_proceedCheckout;
	@FindBy(xpath = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]") WebElement address_proceedCheckout;
	@FindBy(xpath = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]") WebElement shipping_proceedCheckout;
	@FindBy(xpath = "//input[@name='cgv']") WebElement termsof_Service;
	@FindBy(xpath = "//a[@class='bankwire']//span[contains(text(),'(order processing will be longer)')]") WebElement payment_method;
	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]") WebElement confirm_order;
	@FindBy(xpath = "//a[@class='account']") WebElement account;
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]") WebElement order_History;
	@FindBy(xpath = "//tr[contains(@class,'first_item')]//td[@class='history_price']") WebElement orderHistory_amountOfOrder;

	// Constructor
	public PaymentProcess(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
	}

	// This function is used to select the item and add the item in to cart
	public String verify_SelectItem() throws InterruptedException {
		category.click();
		js.executeScript("window.scrollBy(0,900)");
		action.moveToElement(image).perform();
		wait.until(ExpectedConditions.elementToBeClickable(click_View)).click();
		wait.until(ExpectedConditions.visibilityOf(frame));
		if(!frame.isDisplayed()) {wait.until(ExpectedConditions.elementToBeClickable(click_View)).click();}
		driver.switchTo().frame(frame);
		wait.until(ExpectedConditions.visibilityOf(add_Qty)).click();
		addtoCart.click();
		return (success_Message).getText();
	}

	// This function is used to do the checkout and payment process
	public String verify_checkOutandPaymentProcess() {
		proceed_Checkout.click();
		String actual_shoppingCartsummary_TotalCost = shoppingCart_summaryTotalCost.getText();
		wait.until(ExpectedConditions.visibilityOf(summary_proceedCheckout)).click();
		wait.until(ExpectedConditions.visibilityOf(address_proceedCheckout)).click();
		termsof_Service.click();
		shipping_proceedCheckout.click();
		payment_method.click();
		confirm_order.click();
		actual_orderConfirm_TotalCost = orderConfirm_totalCost.getText();
		LOGGER.info(actual_orderConfirm_TotalCost);
		if (actual_orderConfirm_TotalCost.equals(actual_shoppingCartsummary_TotalCost)) {
			LOGGER.info("Correct Total cost is displayig over order confirmation page");
		} else {
			LOGGER.info(
					"Over Order confirmation page,Total cost value is not matching with SHOPPING-CART SUMMARY page");
		}
		return (driver.getTitle());
	}

	// This function is used to verify the amount of order under Profile page:-Order History
	public void verifyamountOfOrder_ProfilePage_OrderHistory() {
		js.executeScript("window.scrollBy(0,-400)");
		account.click();
		order_History.click();
		String actual_orderHistory_amountOfOrder = wait.until(ExpectedConditions.visibilityOf(orderHistory_amountOfOrder)).getText();
		if (actual_orderHistory_amountOfOrder.equals(actual_orderConfirm_TotalCost)) {
			LOGGER.info("Correct amount of order is displaying under Profile page:-Order History");
		} else {
			LOGGER.info("InCorrect amount of order is displaying under Profile page:-Order History");
		}
	}

}
