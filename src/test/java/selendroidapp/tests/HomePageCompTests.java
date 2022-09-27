package selendroidapp.tests;

import org.testng.annotations.Test;
import selendroidapp.pages.android.HomePage;
import selendroidapp.testutils.AndroidBaseTest;

public class HomePageCompTests extends AndroidBaseTest {
	HomePage homepage;

	@Test(description = "'Display and focus on Layout Button' should be functional", priority = 0)
	public void focusBtnIsFunctional() throws InterruptedException {
		homepage = new HomePage(driver);
		homepage.approveOK();
		homepage.clickFocusBtn();
		homepage.verifyFocusText();

	}

	@Test(description = "EN Button should be functional, negative scenario", priority = 1)
	public void enButtonIsFuctionalNegative() {
		homepage = new HomePage(driver);
		homepage.clickENBtn();
		homepage.clickNoNoBtn();
		homepage.verifyToastMessage();
	}

	@Test(description = "EN Button should be functional, positive scenario", priority = 2)
	public void enButtonIsFunctionalPositive() {
		homepage = new HomePage(driver);
		homepage.clickENBtn();
		homepage.clickIAggreeBtn();
		homepage.verifyAppIsRunnig();
	}

}
