package selendroidapp.pages.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import selendroidapp.testutils.AndroidGestures;

public class HomePage extends AndroidGestures {
	AndroidDriver driver;

	public HomePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By startingOkButton = By.id("android:id/button1");
	By focusBtn = By.id("io.selendroid.testapp:id/topLevelElementTest");
	By focusText = By.id("io.selendroid.testapp:id/focusedText");

	By enBtn = AppiumBy.accessibilityId("buttonTestCD");
	By enNoNoBtn = By.id("android:id/button2");
	By enIagreeBtn = By.id("android:id/button1");
	By enToastMsg = By.xpath("/hierarchy/android.widget.Toast");

	By registerBtn = AppiumBy.accessibilityId("startUserRegistrationCD");

	public void approveOK()// for incompatible devices
	{
		try {
			waitForPresenceOfLocatedElement(startingOkButton, 4);
			driver.findElement(startingOkButton).click();
		} catch (Exception e) {
			System.out.println("Compatible device");
		}
	}

	public void clickFocusBtn() throws InterruptedException {
		WebElement focusButton = driver.findElement(focusBtn);
		if (focusButton.isEnabled()) {
			focusButton.click();
		}
	}

	public void verifyFocusText() {
		WebElement element = waitForPresenceOfLocatedElement(focusText, 15);
		System.out.println(element.getText());
		Assert.assertTrue(element.getText().equalsIgnoreCase("Should only be found once"));

	}

	public void clickENBtn() {
		WebElement enButton = driver.findElement(enBtn);
		if (enButton.isEnabled()) {
			enButton.click();
		}
	}

	public void clickNoNoBtn() {
		WebElement nonoBtn = waitForPresenceOfLocatedElement(enNoNoBtn, 4);
		nonoBtn.click();
	}

	public void verifyToastMessage() {
		// System.out.println(driver.findElement(enToastMsg).getText());
		Assert.assertTrue(driver.findElement(enToastMsg).getText().equals("Activity will continue"));
	}

	public void clickIAggreeBtn() {
		WebElement agreeBtn = waitForPresenceOfLocatedElement(enIagreeBtn, 4);
		agreeBtn.click();
	}

	public void verifyAppIsRunnig() {
		boolean isWorkingApp = driver.getPageSource().contains("selendroidapp");
		Assert.assertFalse(isWorkingApp);
	}

	// explain this
	public FormPage clickAddNewUserBtn() {
		driver.findElement(registerBtn).click();
		return new FormPage(driver);
	}

}
