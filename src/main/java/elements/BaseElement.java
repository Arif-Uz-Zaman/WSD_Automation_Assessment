package elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driver.Browser;

// BaseElement class to define the common methods for all the elements
public abstract class BaseElement {
	private By locator;

	public BaseElement(By locator) {
		this.locator = locator;
	}

	public void click() {
		getElement().click();
	}

	public String getText() {
		return getElement().getText();
	}

	protected WebElement getElement() {
		return Browser.getDriver().findElement(locator);
	}

	public boolean isDisplayed() {
		return getElement().isDisplayed();
	}

	public void scrollUntilEelementIsVisiable() {

        new Actions(Browser.getDriver())
                .scrollToElement(getElement())
                .perform();
	}
	
	public List<WebElement> getElements() {
		return Browser.getDriver().findElements(locator);
	}
}
