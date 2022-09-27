# Selendroidapp Appium Framework
![Overview-of-Selendroid](https://user-images.githubusercontent.com/25937998/192161044-9b881c63-bce1-4c98-9fa0-1e36d87c8fdf.png)

![appium Version](https://img.shields.io/badge/appium-2.0-green.svg)
![testng Version](https://img.shields.io/badge/testng-7.6.1-red.svg)
![selenium Version](https://img.shields.io/badge/selenium-4.1.4-blue.svg)
![apache poi Version](https://img.shields.io/badge/apache%20poi-5.2.2-yellow.svg)
![maven Version](https://img.shields.io/badge/maven-4.0.0-green.svg)
![xml version](https://img.shields.io/badge/xml-1.0.0-orange.svg)
![java version](https://img.shields.io/badge/java-11-informational.svg)
![extentreports Version](https://img.shields.io/badge/extentreports-5.0.9-yellowgreen.svg)
![npm version](https://img.shields.io/badge/npm-8.13.2-blue.svg)
![dependencies version](https://img.shields.io/badge/dependencies-recent-yellow.svg)



This is a Java Appium Framework which is using Testng, Maven, Extent reports, Selenium, Java, etc to achieve different tasks. TDD approach is applied.

## Device Properties

Ideally "selendroid app" supports API levels 10-19. Appium server 2.0 supports UIAutomator2 and also UIAutomator2 is only supported Android 5.0 (Lollipop) and over. Android 5.0 works on API 21. So below device is used in the framework :

- Pixel 2 XL, API Level 21, Android 5.0, x86_64 Lolipop

**For this reason, some compatibility problems occurred. So the test cases were selected from the normal running parts of the application.**

![appium 2 0 architectur](https://user-images.githubusercontent.com/25937998/192452396-474601f3-03ef-4c8f-9226-f26b2d27179b.png)

## _Project Features_
- Supports hybrid mobile automation structure.  
- Architecture is made for Android, IOS, and mobile web automation.
- Generates report after each run for all test cases. If any test fails, the framework takes a screenshot of somewhere that occurs error.
- Supports  test data driven approach using TestNG framework
- Read data from a Properties file, Excel, CSV file and can be added reading JSON file support as an optional.
- Real time reporting using Extent Reports
- Supports TestNG XML Suites files, so can be run any functional tests like smoke, regression..
- Supports auto Appium Server starting
- Supports running tests by using Maven Commands from the command line also user can send parameter from the command line to the framework for custom starting
- Supports auto starting emulator for Android app as coding and also can be started by using command line **
- 

## Tools

* Eclipse IDE Version: 2021-09 (4.21.0)
* Java JDK 11.0
* Android Studio
* NodeJs v16.17.0
* npm 8.13.2
* Appium Server v2.0.0-beta.44
* Appium Inspector 2022.0.1 version
* Maven



## Dependencies

* appium java-client 8.2.0 version
* testng 7.6.1 version
* selenium-support 4.1.4 version
* apache poi 5.2.2 version
* apache poi-ooxml 5.2.2 version
* extentreports 5.0.9 version

## Components of Framework (Desciption of Each Class and Methods)

There are 5 package in the framework:

* **pages.android :** contains page object class in Android app
* **suites 	:** contains testng suites (xmls) for smoke, regression test etc.
* **testdata	:** contains data files like excel and csv files.
* **tests		:** contains test files
* **testutils	:** contains helper class, utils, report class, listeners class etc.

There are 3 folders in the framework :

* **resources	:** contains apk files, configuration.properties, batch file (openemu.bat) etc.
* **driver	:** contains browser drivers
* **reports 	:** contains reports and screenshots


### TESTUTILS PACKAGE
-----------------------------------------

### AndroidBaseTest class
This class is responsible for invokes service/driver for each class. It is prepared for Android apps so all of settings are about Android OS.
* **configureAppium() :** This method is responsible for starting emulator/AppiumServer, configuring UiAutomator2 as setting device and app and also setting up driver object for hybrid apps. 
* **tearDown() :** This method is for quiting driver and stopping server.
* **path() :** This gives path of apks as dynamically

### AppiumUtils Class
This class is general class for Android and IOS apps. So methods here are independent of OS that means general methods are here.
* **startAppiumServer(String ipAddress, int port) :** It is a method that calls from AndroidBaseTest to start Appium Server. Starting of AppiumServer is general process.
* **getDataFromPropertiesFile(String propertyName) :**  This method reads properties from the configuration.properties file. Some often changeable Infos keep here like IP address, port, browser name, and device name. Any non-technical person can change this Infos
* **getExcelData(String excelFilePath) :** It reads all rows of the Excel file and returns data for tests. These data are used by TestNG DataProvider in tests.
* **getCsvData(String csvFilePath, String splitCharacter) :** This method makes same things for csv files like above method. 
* **getScreenshotandPath(String testcaseName, AppiumDriver driver) :** This method works if any test fails. At that moment, it takes screenshots, this screenshot is used for the test report.

### AndroidGestures Class 
This class has Android gestures that mean used actions on Android device.
* **longClickGestureAction(WebElement element) :** This method is used for long clicked gesture on Android app
* **scrollGestureWithGoogleEngineByText(String elementText) :**  This method is used scroll to element by using Google Engine.
* **waitForVisibilityOf(WebElement element, int timeToWaitInSec)**
* **waitForCondition(WebElement element, String attribute, String value, int timeToWaitInSec)** 
* **waitForPresenceOfLocatedElement(By locator , int timeToWaitInSec)** 

### ExtentReporterNG Class
This is real time test report class.
* **getReporterObject :** This method contains Extent reports configuration

### Listeners Class
It come us from TestNG and also has events contains spesific states like as :
* **onTestStart()**
* **onTestSuccess()**
* **onTestFailure()**
* **onFinish()**

## Pages (Page Object Model Classes)

Pages of Tests are below :

![pages](https://user-images.githubusercontent.com/25937998/192369106-e639d282-13fc-4a1d-8da3-f4f861ddf221.jpg)

## Testcases

TestNG framework is used based on TDD approach and also used encapsulation and multilayer inheritance.

1. **'Display and focus on Layout Button'** should be functional
2. **'EN Button' should be functional,** negative scenario
3. **'EN Button' should be functional**, positive scenario
4. A new users should be able to add. - DataProvider is used with Excel and csv files -


![tests](https://user-images.githubusercontent.com/25937998/192369575-4b605fe2-ef3f-4788-80c8-9de1361344be.JPG)


## Mandatory 

Change the path of service invoke file according to you in **startAppiumServer()** method that is in **AppiumUtils** class 


## The Page Object Model


The Page Object Model is a design pattern. Design patterns in the programming help us solve common problems in common ways following similar approaches that have worked for similar problems that others have faced.

The Page Object Model is super straightforward; one class typically represents one page in the application under test. This is known as the Page Object.

Page Objects encapsulate actions and elements. Elements are private. And actions are public. This allows callers to see all of the actions that they can take on a page, without having to be exposed to the complexity "under the hood".

## Maven Commands To Run Tests

* To send the parameter to tests on runtime when maven commands execute

``` mvn test -PSmoke -DipAddress="127.0.0.1" ```

* To run smoke tests

``` mvn test -PSmoke ```

* To run regression tests

``` mvn test -PRegression ```

* To open Emulator

``` cd /D C:\Users\{your usersname}\AppData\Local\Android\Sdk\tools ``` 

``` emulator -avd px2Api21Andr5lolipop ``` 

* To position to project directory and execute maven commands

``` cd /D projectDirectory ```

``` mvn test -PSmoke ```

![maven](https://user-images.githubusercontent.com/25937998/192370914-c5ddc673-01ce-4c7f-9023-1e0e081be9dd.JPG)

## For Questions

* [Contact](elektroyazilim@gmail.com) : elektroyazilim@gmail.com
