package selendroidapp.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils { // common for AndroidBaseTest and IOSBaseTest

	/*
	 * AppiumDriver driver;
	 * 
	 * public AppiumUtils(AppiumDriver driver) {//be able to take Android or IOS
	 * driver
	 * 
	 * this.driver = driver; }
	 */
	public AppiumDriverLocalService service;

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\exe\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")) // change this location from yours
				.withIPAddress(ipAddress).usingPort(port).build(); // ipAddress : "127.0.0.1" port : 4723

		
		service.start();
		return service;

	}

	public String getDataFromPropertiesFile(String propertyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\resources\\configure.properties");
		prop.load(fis);
		String propertyValue = prop.getProperty(propertyName);
		return propertyValue;
	}


	public Object[][] getExcelData(String excelFilePath) throws IOException {

		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		// get rows number
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		// to find column number :
		int colCount = row.getLastCellNum();
		System.out.println(colCount);
		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			row = sheet.getRow(i);

			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);
				data[i - 1][j] = formatter.formatCellValue(cell);

			}
		}
		for (Object[] dat : data) {
			System.out.println(Arrays.toString(dat));
		}

		return data;
	}


	public Object[][] getCsvData(String csvFilePath, String splitCharacter) {
		String excelFilePath = csvFilePath;
		String line = "";
		String splitBy = splitCharacter;
		ArrayList<ArrayList<String>> datas = new ArrayList<>();
		ArrayList<String> data;
		String[] temp = null;
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(excelFilePath));
			br.readLine(); // dummy reading to header (columns name)
			int countLine = 0;

			while ((line = br.readLine()) != null) // returns a Boolean value
			{

				data = new ArrayList<String>();
				temp = line.split(splitBy); // use comma as separator

				for (String item : temp) {
					data.add(item);
				}

				datas.add(countLine, data);
				countLine++;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Object[][] objArray = new Object[datas.size()][temp.length];

		for (int i = 0; i < objArray.length; i++) {
			objArray[i] = datas.get(i).toArray(); 
		}

		return objArray;
	}

	public String getScreenshotandPath(String testcaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		
	}
	


}
