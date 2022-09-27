package selendroidapp.tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;
import selendroidapp.pages.android.FormPage;
import selendroidapp.pages.android.HomePage;
import selendroidapp.pages.android.RegisterInfoPage;
import selendroidapp.testutils.AndroidBaseTest;

public class AddNewUserTest extends AndroidBaseTest {
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		// screen to formpage	
		Activity activity = new Activity("io.selendroid.testapp",
				"io.selendroid.testapp.HomeScreenActivity");
		driver.startActivity(activity);
	}

	@Test(dataProvider = "excelDataDriven", description = "New user should be able added.Positive, DataProvider", groups = {
			"smoke" })
	public void addNewUserPositive(Object[] data) throws InterruptedException {
		HomePage hp = new HomePage(driver);
		FormPage formpage = hp.clickAddNewUserBtn();
		RegisterInfoPage rinfopage = formpage.fillForm(data[0].toString(),data[1].toString(),data[2].toString(),data[3].toString());
		System.out.println(formpage.controlName);
		rinfopage.verifyNameIsCorrect(formpage.controlName);

	}

	@DataProvider(name = "excelDataDriven")
	public Object[][] getExcellData() throws IOException {

		String excelFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\selendroidapp\\testdata\\excel.xlsx";
		Object[][] data = getExcelData(excelFilePath);
		return data;

	}

	@DataProvider(name = "excelCSVDataDriven")
	public Object[][] getCSVData() {
		String csvFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\selendroidapp\\testdata\\excel.csv";
		Object[][] objArray = getCsvData(csvFilePath, ",");
		return objArray;

	}
	

}
