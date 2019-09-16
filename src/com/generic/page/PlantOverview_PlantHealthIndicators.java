package com.generic.page;

import java.text.MessageFormat;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.TestUtilities;

public class PlantOverview_PlantHealthIndicators extends SelTestCase {

	public static double getGenericHealthValue(String HealthContainerString, String IndicatorName) {
		getCurrentFunctionName(true);

		int startingIndex = HealthContainerString.indexOf(IndicatorName) + IndicatorName.length();
		try {
			double results = 0;
			if (HealthContainerString.contains(IndicatorName))
				results = TestUtilities.valueParser(HealthContainerString.substring(startingIndex, startingIndex + 5));
			getCurrentFunctionName(false);
			return results;
		} catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static double getPRDailyHealthValue(String HealthContainerString) {
		return getGenericHealthValue(HealthContainerString, "PRDaily");
	}

	public static double getPRSTCDailyHealthValue(String HealthContainerString) {
		return getGenericHealthValue(HealthContainerString, "PRSTCDaily");
	}

	public static double getDataIntegrityHealthValue(String HealthContainerString) {
		return getGenericHealthValue(HealthContainerString, "DataIntegrityDaily");
	}

	public static double getAvilabilityHealthValue(String HealthContainerString) {
		return getGenericHealthValue(HealthContainerString, "AvailabilityDaily");
	}

	public static double getPowerFactorValue(String HealthContainerString) {
		return getGenericHealthValue(HealthContainerString, "PowerfactorDaily");
	}

	// Get and store all health indicators of each plant
	public static void getPlantHealthIndicators(plant tmpPlant) {
		getCurrentFunctionName(true);
		try {
			String HealthContainerString = HomePage.getPlantHealthIndicators().getText();
			// HealthContainerString value is looks like the below
			/*
			 * Plant health indicators PR daily 91.3% weekly 0.0% monthly 92.9% PR STC daily
			 * 96.1% weekly 0.0% monthly 97.6% Data Integrity daily 82.4% weekly 0.0%
			 * monthly 79.7% Availability daily 100.0% weekly 0.0% monthly 100.0% Power
			 * factor daily 32.0% weekly 0.0% monthly 48.3%
			 */

			HealthContainerString = HealthContainerString.trim().replace("\n", "").replace("\r", "")
					.replace("arrow_drop_down", "").replace(" ", "");
			tmpPlant.Health_PRpercentage = getPRDailyHealthValue(HealthContainerString);
			tmpPlant.Health_PR_STCpercentage = getPRSTCDailyHealthValue(HealthContainerString);
			tmpPlant.Health_Data_integrity_percentage = getDataIntegrityHealthValue(HealthContainerString);
			tmpPlant.Health_plantAvilability_percentage = getAvilabilityHealthValue(HealthContainerString);
			tmpPlant.Health_Power_Factor_percentage = getPowerFactorValue(HealthContainerString);
			getCurrentFunctionName(false);
		} catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
		}

	}

}
