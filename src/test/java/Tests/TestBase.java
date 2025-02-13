package Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;

import driver.Browser;
import utility.DataReader;


// TestBase class to define the setup and teardown methods
@Listeners(ChainTestListener.class)
public abstract class TestBase {
	
	protected WebDriver driver;		
    private int implicitWaitTimeInSeconds = DataReader.getIntValue("implicitWaitTimeInSeconds", "config");
	
    // Setup method to initialize the driver and open the browser
	@BeforeMethod
	public void setUp() {
		driver = Browser.initialize();
		driver.get(DataReader.getStringValue("url", "Config"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeInSeconds));
	}
	
	
	// Teardown method to quit the driver
	@AfterMethod
	public void tearDown() {
		Browser.quitDriver();
	}

}
