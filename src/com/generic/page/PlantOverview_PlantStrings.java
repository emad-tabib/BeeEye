package com.generic.page;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.TestUtilities;

public class PlantOverview_PlantStrings extends SelTestCase {


	public static void getStringsTabGeneralInfo(plant tmpPlant) throws Exception {
		try {
			getCurrentFunctionName(true);
			String[] generalInfoContainerStrings = HomePage.getStringsGeneralInfoContainer().getText().trim().split("\n");
			
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


			tmpPlant.strings___performance = TestUtilities.valueParser(generalInfoContainerStrings[PERF_value_index]);

			tmpPlant.Strings_Performance_Percent = TestUtilities.valueParser(generalInfoContainerStrings[PERF_percentage_index]);

			tmpPlant.strings___availability = TestUtilities.valueParser(generalInfoContainerStrings[Avilability_value_index]);	
			//tmpPlant.Avilability_percentage = TestUtilities.valueParser(generalInfoContainerStrings[Avilability_percentage_index]);
			
			
			tmpPlant.strings___disconnected_strings = tmpPlant.Disconnected_Strings_value;
			tmpPlant.strings___panel_degradation = tmpPlant.Panel_Degradation_value;
			

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

}
