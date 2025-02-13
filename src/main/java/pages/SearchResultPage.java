package pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject; // Use this import for org.json.JSONObject
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driver.Browser;
import elements.Element;
import utility.DataReader;
import waits.ExplicitWait;

public class SearchResultPage {

	private static final Element searchResultPageTitle = new Element(
			By.xpath("//span[@class='base' and contains(text(),'Search results for:')]"));
	private static final Element productList = new Element(
			By.cssSelector("ol.products.list.items.product-items li.item.product.product-item"));
	private static final Element cartBadge = new Element(By.cssSelector("span.counter.qty span.counter-number"));
	private static final Element cartProductName = new Element(By.cssSelector(".product-item-name a"));
	private static final Element viewAndEditbutton = new Element(By.xpath("//span[contains(text(),'View and Edit Cart')]"));

	// check if the search result page title is displayed
	public boolean searchResultPageTitleIsDisplayed() {
		ExplicitWait.visibilityOfElementLocated(searchResultPageTitle.getLocator(), 5);

		return searchResultPageTitle.isDisplayed();
	}

	// check if the product list is displayed
	public boolean productListIsDisplayed() {
		ExplicitWait.visibilityOfElementLocated(productList.getLocator(), 5);
		return productList.isDisplayed();
	}

	// check productist is not empty
	public boolean productListIsNotEmpty() {
		ExplicitWait.visibilityOfElementLocated(productList.getLocator(), 5);
		return productList.getElements().size() > 0;
	}

	// relevant products showing in the search results
	public boolean isRelevantProductShowing() {
		List<String> relevantProductNames = DataReader.getStringList("relevantProductNames", "TestData");
		ExplicitWait.visibilityOfElementLocated(productList.getLocator(), 5);
		List<WebElement> products = productList.getElements();
		Set<String> foundProducts = new HashSet<>();
		for (WebElement product : products) {
			try {
				// Find product name element and get the text
				WebElement productNameElement = product
						.findElement(By.cssSelector("strong.product.name.product-item-name a"));
				String productName = productNameElement.getText().trim();

				// If the product is in the relevant list, add it to the found set
				if (relevantProductNames.contains(productName)) {
					foundProducts.add(productName);
					System.out.println("Found relevant product: " + productName);
				}
			} catch (Exception e) {
				System.out.println("Error processing product. Skipping...");
			}
		}

		// Check if all three required products were found
		if (foundProducts.containsAll(relevantProductNames)) {
			System.out.println("All relevant products are Showing.");
			return true;
		} else {
			System.out.println("Relevant products are missing/Not Showing.");
			return false;
		}

	}

	// Add product to the cart

	public void addProductToCart(String productName) {
		// Retrieve color and size from the JSON file
		JSONObject productDetails = DataReader.getJsonObject("selectProductDetails", "TestData");
		String color = productDetails != null ? productDetails.optString("color") : "";
		String size = productDetails != null ? productDetails.optString("size") : "";

		// Wait until the product list is visible
		ExplicitWait.visibilityOfElementLocated(productList.getLocator(), 5);
		List<WebElement> products = productList.getElements();

		for (WebElement product : products) {
			WebElement productNameElement = product
					.findElement(By.cssSelector("strong.product.name.product-item-name a"));
			String productTitle = productNameElement.getText().trim();

			// If product title matches, check for color and size
			if (productTitle.equals(productName)) {
				// Find color swatch elements
				List<WebElement> colorSwatches = product.findElements(By.cssSelector("div.swatch-option.color"));

				String productColor = "";
				for (WebElement colorSwatch : colorSwatches) {
					// Retrieve the color from the option-label attribute
					@SuppressWarnings("deprecation")
					String swatchColor = colorSwatch.getAttribute("option-label");
					if (swatchColor != null && swatchColor.equalsIgnoreCase(color)) {
						productColor = swatchColor;
						// Click on the color swatch
						colorSwatch.click();
						break;
					}
				}

				// Find size swatch elements and extract the text
				List<WebElement> sizeSwatches = product.findElements(By.cssSelector("div.swatch-option.text"));

				String productSize = "";
				for (WebElement sizeSwatch : sizeSwatches) {
					// Retrieve the size from the text content
					String swatchSize = sizeSwatch.getText().trim();
					if (swatchSize.equalsIgnoreCase(size)) {
						productSize = swatchSize;
						// Click on the size swatch
						sizeSwatch.click();
						break;
					}
				}

				// If the color and size match, add the product to the cart
				if (color.equals(productColor) && size.equals(productSize)) {
					// Hover over the product name to reveal the 'Add to Cart' button
					Actions actions = new Actions(Browser.getDriver());
					actions.moveToElement(productNameElement).perform();

					// Wait for the Add to Cart button to become visible
					WebElement addToCartButton = product.findElement(By.cssSelector("button.tocart"));

					// Click on the Add to Cart button
					addToCartButton.click();
					System.out.println(
							"Added " + productName + " with color: " + color + " and size: " + size + " to the cart.");
					break;
				}
			}
		}
	}
	
	// check if the cart badge is displayed
	public boolean cartBadgeIsDisplayed() {
		ExplicitWait.visibilityOfElementLocated(cartBadge.getLocator(), 10);
		cartBadge.scrollUntilEelementIsVisiable();
		return cartBadge.isDisplayed();
	}
	

	public String getCartItemCount() {
	    // Wait until the cart badge is visible
	    ExplicitWait.visibilityOfElementLocated(cartBadge.getLocator(), 10);

	    // Wait for the loading class "_block-content-loading" to disappear
	    ExplicitWait.invisibilityOfElementLocated(By.cssSelector(".counter.qty._block-content-loading"), 10);

	    // Now, retrieve the cart badge count
	    return cartBadge.getText().trim();
	}
	
	//click on the cart icon
	public void clickOnCartIcon() {
		ExplicitWait.elementToBeClickable(cartBadge.getLocator(), 10);
		cartBadge.click();
	}
	
	//click on view and edit cart button
	public void clickOnViewAndEditCartButton() {
		ExplicitWait.elementToBeClickable(viewAndEditbutton.getLocator(), 10);
		viewAndEditbutton.click();
	}
	
	public boolean isProductInCart(String expectedProductName) {
	    // Wait for the cart to load
	    ExplicitWait.visibilityOfElementLocated(By.cssSelector(".product-item-details"), 10);

	    // Get all product names from the cart
	    List<WebElement> productNames = cartProductName.getElements();

	    // Loop through the list and check if the expected product is in the cart
	    for (WebElement product : productNames) {
	        if (product.getText().trim().equalsIgnoreCase(expectedProductName)) {
	            return true; // Product found
	        }
	    }
	    return false; // Product not found
	}


	

}
