package selendroidapp.testutils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidGestures {
	AndroidDriver driver;  // AndroidDriver		@AndroidFindBy
	
	public AndroidGestures(AndroidDriver driver) {
		this.driver = driver;
	}
	
	public void longClickGestureAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000)); // 2 seconds
	}


	public void scrollGestureWithGoogleEngineByText(String elementText) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));")); 
	}

	public  WebElement waitForVisibilityOf(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

	// Steal reference hatasini geciyor
	public  Boolean waitForCondition(WebElement element, String attribute, String value, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }
	
	public  WebElement waitForVisibilityOfLocatedElement(By locator , int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
	public  WebElement waitForPresenceOfLocatedElement(By locator , int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        wait.pollingEvery( Duration.ofMillis(100));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
