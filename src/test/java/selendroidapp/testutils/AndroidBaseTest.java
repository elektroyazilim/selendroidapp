package selendroidapp.testutils;


import static io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption.AUTO_GRANT_PERMISSIONS_OPTION;
import static io.appium.java_client.remote.options.SupportsFullResetOption.FULL_RESET_OPTION;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import io.appium.java_client.remote.options.SupportsNoResetOption;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws IOException, InterruptedException { // for hybrid app
		
		// start emulator
		/*
		Process process = Runtime.getRuntime().exec(
		        "cmd /c start openemu.bat",
		        null,
		        new File(System.getProperty("user.dir")+ "\\src\\test\\java\\resources\\"));
		
		Thread.sleep(10000);
		*/
		
		// *********************
		
		// Taking parameter from maven commands :
		String ipAddress = System.getProperty("ipAddress")!= null ? System.getProperty("ipAddress") : getDataFromPropertiesFile("ipAddress");
		// ****************************		

		int port = Integer.parseInt(getDataFromPropertiesFile("port"));
		String androidDeviceName = getDataFromPropertiesFile("AndroidDeviceName");

		service = startAppiumServer(ipAddress,port);

		// capabilities :
		UiAutomator2Options options = new UiAutomator2Options();		
		options.setDeviceName(androidDeviceName);//options.setDeviceName("p2xrphone"); // this device name is from Android Studio Emulator - pxlqphone - p2xrphone

		// options.setCapability("appium:noReset", "false");
		options.setCapability(SupportsNoResetOption.NO_RESET_OPTION, false);
		options.setCapability(FULL_RESET_OPTION, true);
		options.setCapability(AUTO_GRANT_PERMISSIONS_OPTION , true);
		
		
		String fileName = "selendroid-test-app-0.17.0.apk";
		options.setApp(path() + fileName);
		
		// for hybrid apps to invoke browser
		String driverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
		options.setChromedriverExecutable(driverPath);

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // timeout settings

	}

	private static String path() // resources path as dynamically
	{
		String projectPath = System.getProperty("user.dir"); // project dizinini verir.
		String apksPath = projectPath + "\\resources\\apks\\";
		return apksPath;
	}
	
	

	@AfterClass(alwaysRun = true)
	public void tearDown() {

		driver.quit();
		service.stop();
	}

}
