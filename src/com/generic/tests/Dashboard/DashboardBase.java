package com.generic.tests.Dashboard;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.page.HomePage;
import com.generic.page.PlantOverview_General;
import com.generic.page.PlantOverview_PlantHealthIndicators;
import com.generic.page.PlantOverview_PlantHeatmap;
import com.generic.page.PlantOverview_PlantInsights;
import com.generic.page.PlantOverview_PlantInverters;
import com.generic.page.PlantOverview_PlantStrings;
import com.generic.page.SignIn;
import com.generic.page.plant;
import com.generic.selector.PlantOverViewSelector;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.loggerUtils;
import com.generic.util.sqLiteUtils;

public class DashboardBase extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.DashboardSheet;
	private static XmlTest testObject;

	private static ThreadLocal<loggerUtils> Testlogs = new ThreadLocal<loggerUtils>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new loggerUtils(test.getName() + test.getIndex()));
		testObject = test;
	}

	@DataProvider(name = "Login", parallel = false)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Login")
	public void LoginRegressionTest(String caseId, String runTest, String desc, String proprties, String userNames) {
		String userName = "beeadmin";
		String CaseDescription  = MessageFormat.format(LoggingMsg.TEST_CASE_DESC,
				testDataSheet + ".<font color='fuchsia'>" + caseId + "</font>", this.getClass().getCanonicalName(),
				desc, proprties.replace("\n", "<br>- ")) + "<b>User:</b> <font color='fuchsia'>" + userName
				+ "</font>" ;
		Testlogs.set(new loggerUtils("Login " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName(SheetVariables.DashboardCaseId);
		
		LinkedHashMap<String, String> userdetails = null;
		if (!userName.equals("")) {
			userdetails = (LinkedHashMap<String, String>) users.get(userName);
			Testlogs.get().debug("User will be used is : " + userdetails);
		}

//		List<String> webPlants = new ArrayList<String>();

		try {

			// Step 1 do log-in
			Testlogs.get().debug(
					"Login username/password is: " + userName + " " + (String) userdetails.get(SignIn.keys.password));
			SignIn.fillLoginFormAndClickSubmit(userName, (String) userdetails.get(SignIn.keys.password));
			if (!SignIn.checkUserAccount()) {
				sassert().assertTrue(false, LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);

				// marking user as not valid user
				// getDatatable().setUserValid(userName, false);
			} else {
				Testlogs.get().debug("User was loggedin successfully");

				// Marking user as valid user
				// getDatatable().setUserValid(userName, true);

				// Step 2 get available plants from web
			//	List<WebElement> availblePlants = HomePage.getUserPlants();

			} // else logged in successfully
if (proprties.contains("Best Flow(GINI)")) {
	
}
			sassert().assertAll();
			logCaseDetailds(CaseDescription);
			Common.testPass();

		} catch (Throwable t) {
			logCaseDetailds(CaseDescription+ "<br><b><font color='red'>Failure Reason: </font></b>"+ t.getMessage().replace("\n", "").trim());
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test

}
