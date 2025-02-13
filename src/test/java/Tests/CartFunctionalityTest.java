package Tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchResultPage;
import utility.DataReader;

public class CartFunctionalityTest extends TestBase {
	
	String cartProductName = DataReader.getStringValue("cartProductName", "TestData");
	JSONObject productDetails = DataReader.getJsonObject("selectProductDetails","TestData");
	
	@Test(groups="Cart Functionality Test")
	public void verifyAddToCartFunctionality() {
		HomePage homePage = new HomePage();
		SearchResultPage searchResultsPage = new SearchResultPage();
		boolean isHomePageTitleDisplayed = homePage.HomePageTitleIsDisplayed();
		CartPage cartPage = new CartPage();
		Assert.assertTrue(isHomePageTitleDisplayed, "Home Page title is not displayed");
		// Step 1: Search for a product
		homePage.enterSearchText(cartProductName);
		homePage.clickOnSearchButton();
		
		boolean isSearchResultPageTitleDisplayed = searchResultsPage.searchResultPageTitleIsDisplayed();
		Assert.assertTrue(isSearchResultPageTitleDisplayed, "Search results page title is not displayed");
		boolean isProductListDisplayed = searchResultsPage.productListIsDisplayed();
		Assert.assertTrue(isProductListDisplayed, "Product list is not displayed");
		boolean isProductListNotEmpty = searchResultsPage.productListIsNotEmpty();
		Assert.assertTrue(isProductListNotEmpty, "Product list is empty");
		
		searchResultsPage.addProductToCart(cartProductName);
		boolean isCartBadgeDisplayed = searchResultsPage.cartBadgeIsDisplayed();
		Assert.assertTrue(isCartBadgeDisplayed, "Cart badge is not displayed");
		String cartBadgeCount = searchResultsPage.getCartItemCount();
		Assert.assertEquals(cartBadgeCount, "1", "Cart badge count is not matching");
		
		searchResultsPage.clickOnCartIcon();
		boolean isProductShowingInCart = searchResultsPage.isProductInCart(cartProductName);
		Assert.assertTrue(isProductShowingInCart, "Product is not showing in the cart");
		
		searchResultsPage.clickOnViewAndEditCartButton();
		
		boolean isCartPageTitleDisplayed = cartPage.cartPageTitleIsDisplayed();
		Assert.assertTrue(isCartPageTitleDisplayed, "Cart page title is not displayed");
		
		
		boolean isProductDetailsInCartPage = cartPage.verifyFirstProductSizeAndColor(productDetails);
		Assert.assertTrue(isProductDetailsInCartPage, "Product details are not matching");
		
	}
	

}
