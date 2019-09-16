package com.generic.tests.DailyReport;

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

public class DailyReportBase extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.loginSheet;
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
	public void LoginRegressionTest(String caseId, String runTest, String desc, String proprties, String userName) {
		String CaseDescription  = MessageFormat.format(LoggingMsg.TEST_CASE_DESC,
				testDataSheet + ".<font color='fuchsia'>" + caseId + "</font>", this.getClass().getCanonicalName(),
				desc, proprties.replace("\n", "<br>- ")) + "<b>User:</b> <font color='fuchsia'>" + userName
				+ "</font>" ;
		Testlogs.set(new loggerUtils("DailyReport " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName(SheetVariables.DailyReportTestCaseId);

		LinkedHashMap<String, String> userdetails = null;
		if (!userName.equals("")) {
			userdetails = (LinkedHashMap<String, String>) users.get(userName);
			Testlogs.get().debug("User will be used is : " + userdetails);
		}

		List<String> webPlants = new ArrayList<String>();

		try {

			// Step 1 do log-in
			Testlogs.get().debug(
					"Login username/password is: " + userName + " " + (String) userdetails.get(SignIn.keys.password));
			SignIn.fillLoginFormAndClickSubmit(userName, (String) userdetails.get(SignIn.keys.password));
			if (!SignIn.checkUserAccount()) {
				sassert().assertTrue(false, LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);

				// marking user as not valid user
				getDatatable().setUserValid(userName, false);
			} else {
				Testlogs.get().debug("User was loggedin successfully");

				// Marking user as valid user
				getDatatable().setUserValid(userName, true);

				// Step 2 get available plants from web
				List<WebElement> availblePlants = HomePage.getUserPlants();
				String accountPlantes = (String) userdetails.get(SignIn.keys.plants);

				Testlogs.get().debug("Found " + availblePlants.size() + " plants");
				for (int plantIndex = 0; plantIndex < availblePlants.size(); plantIndex++) {
					Testlogs.get().debug(availblePlants.get(plantIndex).getText());
				}
				Testlogs.get().debug("Account plants " + accountPlantes.replace("\n", "<br>") + " plants");

				// Step 3 reiterate all plants and validate calculations
				for (int plantIndex = 0; plantIndex < availblePlants.size(); plantIndex++) {
					// get all plants - to avoid element is not attached to the page document
					List<WebElement> currentAvailablePlants = HomePage.getUserPlants();
					WebElement Web_plant = currentAvailablePlants.get(plantIndex);

					// Step 4 Navigate to plant status
					HomePage.navigateToPlant(Web_plant);
					sassert().assertTrue(accountPlantes.contains(Web_plant.getText()),
							"Plant " + Web_plant.getText() + " is not exist in account plants but existed in web");

					// Step 5 get plant status general information
					// Initiating new plant object to be used to store all plant object
					plant tmpPlant = new plant();
					tmpPlant.user = userName;
					tmpPlant.plant = Web_plant.getText();
					webPlants.add(Web_plant.getText());
					tmpPlant.login = "PASS";

					// Step 6 check if the plant information had loaded
					if (HomePage.isPlantOverViewLoaded()) {

						PlantOverview_General.getGeneralPlantInfo(tmpPlant);

						// Step 7 get all plants insights and values for each plant
						PlantOverview_PlantInsights.getPlantInsights(tmpPlant);

						// Step 8 Health indicators
						PlantOverview_PlantHealthIndicators.getPlantHealthIndicators(tmpPlant);

						// Step 9 Heat map
						PlantOverview_PlantHeatmap.getPlantHeatMapNumbers(tmpPlant);

						// Step 10 Inverters tab data
						HomePage.navigateToTab(PlantOverViewSelector.InvertersTab);
						PlantOverview_PlantInverters.getInvertersTabGeneralInfo(tmpPlant);

						// Step 11 Strings tab data
						HomePage.navigateToTab(PlantOverViewSelector.StringsTab);
						PlantOverview_PlantStrings.getStringsTabGeneralInfo(tmpPlant);

					} else {
						logs.debug("the plant didn't loded correctely");
						sassert().assertTrue(false, "Plant " + tmpPlant.plant + " was failed to load");
						tmpPlant.valid = false;
					}

					//plant.printPlant(tmpPlant);

					// Step 12 insert tmpPlant into data base
					sqLiteUtils.insertData(tmpPlant, TableName, DatabaseName);

					// Step 13 get the day before data from database
					plant previousPlantData = sqLiteUtils.selectDataForTheDayBefore(tmpPlant.user, tmpPlant.plant,
							TableName, DatabaseName);

					// Step 14 compare data provide judgment and write data to excel
					if (previousPlantData != null)
						plant.comparPlantsAndwriteResults(tmpPlant, previousPlantData);
					else
						logs.debug("Previous data for plant " + tmpPlant.plant + " is null");

				} // for loop

				String[] webPlantsArray = new String[webPlants.size()];
				webPlantsArray = webPlants.toArray(webPlantsArray);

				for (String accountPlant : accountPlantes.trim().split("\n")) {

					if (TestUtilities.checkIfExist(webPlantsArray, accountPlant)) {
						getDatatable().setPlantValid(accountPlant, true);
					} else {
						getDatatable().setPlantValid(accountPlant, false);
						sassert().assertTrue(false, "plants is missing from web " + accountPlant);
					}
				}

			} // else logged in successfully

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
