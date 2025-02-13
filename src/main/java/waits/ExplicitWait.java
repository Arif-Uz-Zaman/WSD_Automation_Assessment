package waits;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Browser;


public class ExplicitWait {
	
	// Explicit wait methods to wait for an element to be present
	public static void presenceOfElementLocated(By Locator, int time) {
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}

	// Explicit wait methods to wait for an element to be clickable
	public static void elementToBeClickable(By Locator , int time) {
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
	}
	
	// Explicit wait methods to wait for an element to be visible
	public static void visibilityOfElementLocated(By Locator, int time) {
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}
	
	// Explicit wait methods to wait for an element to be invisible	
	public static void invisibilityOfElementLocated(By Locator, int time) {
		WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
	}
	

}
