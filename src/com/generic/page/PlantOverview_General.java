package com.generic.page;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.TestUtilities;

public class PlantOverview_General extends SelTestCase {

	// get all general data
	public static void getGeneralPlantInfo(plant tmpPlant) throws Exception {
		try {
			getCurrentFunctionName(true);
			String[] generalInfoContainerStrings = HomePage.getPlantGeneralInfoContainer().getText().trim().split("\n");
			
			// String in the array looks like this
			/*
			 * Plant yearly potential extra income
			 * $16.46K 
			 * 5.06% 
			 * This percentage is relative to your current plant income(current income = 100%) 
			 * Performance 
			 * Performance yearly potential income 
			 * $16.46K 
			 * 5.06% 
			 * Availability 
			 * Availability daily potential income 
			 * $0.00 
			 * 0.00%
			 */

			// Indexes to use with the array
			int PEI_value_index = 1;
			int PEI_percentage_index = 2;
			int PERF_value_index = 5;
			int PERF_percentage_index = 6;
			int Avilability_value_index = 9;
			int Avilability_percentage_index = 10;

			tmpPlant.PEI_value = TestUtilities.valueParser(generalInfoContainerStrings[PEI_value_index]);
			tmpPlant.PEI_percentage = TestUtilities.valueParser(generalInfoContainerStrings[PEI_percentage_index]);

			tmpPlant.PERF_value = TestUtilities.valueParser(generalInfoContainerStrings[PERF_value_index]);
			tmpPlant.PERF_percentage = TestUtilities.valueParser(generalInfoContainerStrings[PERF_percentage_index]);

			tmpPlant.Avilability_value = TestUtilities
					.valueParser(generalInfoContainerStrings[Avilability_value_index]);
			tmpPlant.Avilability_percentage = TestUtilities
					.valueParser(generalInfoContainerStrings[Avilability_percentage_index]);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Returns the calculated Inverters Availability
	public static double getCalculatedInvertersAvilability(plant tmpPlant) throws Exception {
		try {
			getCurrentFunctionName(true);
			getCurrentFunctionName(false);

			return tmpPlant.inverters___downtime;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Returns the calculated Inverters performance
	public static double getCalcualtedInvertersPerformance(plant tmpPlant) throws Exception {
		try {
			getCurrentFunctionName(true);
			getCurrentFunctionName(false);

			return (tmpPlant.inverters_efficiency_below_spec + tmpPlant.inverters_relative_efficiency
					+ tmpPlant.inverters_Mppt);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Returns the calculated Strings performance
	public static double getCalculatedStringsPerformance(plant tmpPlant) throws Exception {
		try {
			getCurrentFunctionName(true);
			getCurrentFunctionName(false);

			return (tmpPlant.strings___panel_degradation + tmpPlant.strings___disconnected_strings);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Returns the calculated Strings availability
	public static double getCalculatedStringsAvilability(plant tmpPlant) throws Exception {
		try {
			getCurrentFunctionName(true);
			getCurrentFunctionName(false);

			return 0;

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

}
