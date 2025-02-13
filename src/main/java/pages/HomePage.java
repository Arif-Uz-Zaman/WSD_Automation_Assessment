package pages;

import org.openqa.selenium.By;

import elements.Element;
import waits.ExplicitWait;

public class HomePage {
	
	private static final Element HomePageTitle = new Element(By.xpath("//span[@class='base' and contains(text(),'Home Page')]"));	
	private static final Element SearchBar = new Element(By.id("search"));
	private static final Element SearchButton = new Element(By.xpath("(//button[@type='submit'])[1]"));
	
	// check if the home page title is displayed
	public boolean HomePageTitleIsDisplayed() {
		ExplicitWait.visibilityOfElementLocated(HomePageTitle.getLocator(), 10);
		return HomePageTitle.isDisplayed();
	}
	
	// enter the search text in the search bar	
	public void enterSearchText(String searchText) {
		SearchBar.getElement().sendKeys(searchText);
	}
	
	// click on the search button
	public void clickOnSearchButton() {
		ExplicitWait.elementToBeClickable(SearchButton.getLocator(), 5);
		SearchButton.click();
	}

}
