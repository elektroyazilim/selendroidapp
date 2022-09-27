package selendroidapp.pages.android;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import selendroidapp.testutils.AndroidGestures;

public class FormPage extends AndroidGestures {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By username = By.id("io.selendroid.testapp:id/inputUsername");
	By email = By.id("io.selendroid.testapp:id/inputEmail");
	By password = By.id("io.selendroid.testapp:id/inputPassword");

	// clear
	By name = By.id("io.selendroid.testapp:id/inputName");

	By cbbLang = By.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage");
	By cbbJavaOpt = By.xpath("//android.widget.CheckedTextView[@text='Java']");
	By chckBx = By.id("io.selendroid.testapp:id/input_adds");
	By registerBtn = By.id("io.selendroid.testapp:id/btnRegisterUser");

	public String controlName ;
	
	public RegisterInfoPage fillForm(String usernm, String mail, String pass, String fname) throws InterruptedException {
		driver.findElement(username).sendKeys(usernm); // username
		driver.findElement(email).sendKeys(mail); // email
		driver.findElement(password).sendKeys(pass); // password
		driver.findElement(name).clear();
		driver.findElement(name).sendKeys(fname);// name
		driver.findElement(cbbLang).click();
		waitForVisibilityOfLocatedElement(cbbJavaOpt, 10).click();
		Thread.sleep(1000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		waitForPresenceOfLocatedElement(chckBx, 6).click();
		controlName = driver.findElement(name).getAttribute("text");		
		
		driver.findElement(registerBtn).click();
		return new RegisterInfoPage(driver);
	}

	public String getCurrentName(String name) {
		return name;
	}
}
