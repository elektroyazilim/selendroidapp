package selendroidapp.pages.android;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import selendroidapp.testutils.AndroidGestures;

public class RegisterInfoPage extends AndroidGestures {

	AndroidDriver driver;

	public RegisterInfoPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By lblName = By.id("io.selendroid.testapp:id/label_name_data");
	
	public void verifyNameIsCorrect(String name)
	{
		Assert.assertTrue(name.equalsIgnoreCase(driver.findElement(lblName).getText()));
	}
}
