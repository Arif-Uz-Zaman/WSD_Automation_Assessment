package pages;

import org.json.JSONObject; // Use this import for org.json.JSONObjectimport utility.DataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import elements.Element;
import waits.ExplicitWait;

public class CartPage {
	
	private static final Element cartPageTitle = new Element(By.xpath("//span[@class='base' and contains(text(),'Shopping Cart')]"));
	private static final Element FirstProductName = new Element(By.xpath("(//strong[@class='product-item-name']/a)[2]"));
	private static final Element firstProductSize = new Element(By.xpath("(//dl[@class='item-options' ]//dd)[1]"));
	private static final Element firstProductColor = new Element(By.xpath("(//dl[@class='item-options' ]//dd)[2]"));
	
	// check if the cart page title is displayed
	public boolean verifyFirstProductSizeAndColor(JSONObject selectProductDetails) {
	    JSONObject productDetails = selectProductDetails;
	    String productName = productDetails != null ? productDetails.optString("productName") : "";
	    
	    
	    String actualProductName = FirstProductName.getText().trim();
	    System.out.println("actualProductName: " + actualProductName);
	    System.out.println("productName: " + productName);
		String color = productDetails != null ? productDetails.optString("color") : "";
		String size = productDetails != null ? productDetails.optString("size") : "";

	    WebElement sizeElement = firstProductSize.getElement();
	    WebElement colorElement = firstProductColor.getElement();

	    String actualSize = sizeElement.getText().trim();
	    String actualColor = colorElement.getText().trim();
	    System.out.println("actualSize: " + actualSize);
	    System.out.println("size: " + size);
	    System.out.println("actualColor: " + actualColor);
	    System.out.println("color: " +	color);

		if (actualSize.equals(size) && actualColor.equals(color) && actualProductName.equals(productName)) {
			return true;
		} else {
			return false;
		}
	   
	}
	
	// check if the cart page title is displayed
	public boolean cartPageTitleIsDisplayed() {
		ExplicitWait.visibilityOfElementLocated(cartPageTitle.getLocator(), 5);
		return cartPageTitle.isDisplayed();
	}


}
