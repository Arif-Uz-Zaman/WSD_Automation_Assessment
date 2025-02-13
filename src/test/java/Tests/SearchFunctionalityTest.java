package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultPage;
import utility.DataReader;

public class SearchFunctionalityTest extends TestBase {

	String validProductName = DataReader.getStringValue("validProductName", "TestData");

	@Test(groups="Search Functionality Test")
	public void verifySearchFunctionality() {
		HomePage homePage = new HomePage();
		SearchResultPage searchResultsPage = new SearchResultPage();
		boolean isHomePageTitleDisplayed = homePage.HomePageTitleIsDisplayed();
		Assert.assertTrue(isHomePageTitleDisplayed, "Home Page title is not displayed");
		// Step 1: Search for a product
		homePage.enterSearchText(validProductName);
		homePage.clickOnSearchButton();
		// Step 2: Verify search result page showing relevent products
		boolean isSearchResultPageTitleDisplayed = searchResultsPage.searchResultPageTitleIsDisplayed();
		Assert.assertTrue(isSearchResultPageTitleDisplayed, "Search results page title is not displayed");
		boolean isProductListDisplayed = searchResultsPage.productListIsDisplayed();
		Assert.assertTrue(isProductListDisplayed, "Product list is not displayed");
		boolean isProductListNotEmpty = searchResultsPage.productListIsNotEmpty();
		Assert.assertTrue(isProductListNotEmpty, "Product list is empty");
		boolean isReleventProductsArePresent = searchResultsPage.isRelevantProductShowing();
		Assert.assertTrue(isReleventProductsArePresent, "Relevant products are not present in the search results");
	}
}