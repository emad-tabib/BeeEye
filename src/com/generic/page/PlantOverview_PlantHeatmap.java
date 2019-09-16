package com.generic.page;

import java.text.MessageFormat;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;


public class PlantOverview_PlantHeatmap extends SelTestCase {

	public static void getPlantHeatMapNumbers(plant tmpPlant) {

		getCurrentFunctionName(true);
		try {
			String[] HeatMapContainerStrings = HomePage.getPlantHeatMapContaitnerString().getText().trim().split("\n");
			//Array's data looks like this
			/*
			 * Healthy 
			 * 0 
			 * Inverters 
			 * 69 
			 * strings 
			 * OK 
			 * 7 
			 * Inverters 
			 * 0 
			 * strings 
			 * Faulty 
			 * 6 
			 * Inverters 
			 * 55
			 * strings 
			 * Missing data 
			 * 0 
			 * Inverters 
			 * 4 
			 * strings
			 */
			int healthyInvertersIndex = 1;
			int healthyStringsIndex = 3;
			int faultyInvertersIndex = 11;
			int faultyStringsIndex = 13;
			int okInvertersIndex = 6;
			int okStringsIndex = 8;
			int messingDataInvertersIndex = 16;
			int messingDataStringsIndex = 18;

			tmpPlant.Healty_Inverters = Double.parseDouble(HeatMapContainerStrings[healthyInvertersIndex]);
			tmpPlant.Healty_Strings = Double.parseDouble(HeatMapContainerStrings[healthyStringsIndex]);
			tmpPlant.Faulty_Inverters = Double.parseDouble(HeatMapContainerStrings[faultyInvertersIndex]);
			tmpPlant.Faulty_Strings = Double.parseDouble(HeatMapContainerStrings[faultyStringsIndex]);
			tmpPlant.OK_Inverters = Double.parseDouble(HeatMapContainerStrings[okInvertersIndex]);
			tmpPlant.OK_Strings = Double.parseDouble(HeatMapContainerStrings[okStringsIndex]);
			tmpPlant.Messing_data_inverters = Double.parseDouble(HeatMapContainerStrings[messingDataInvertersIndex]);
			tmpPlant.Messing_data_Strings = Double.parseDouble(HeatMapContainerStrings[messingDataStringsIndex]);

			getCurrentFunctionName(false);
		} catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
		}

	}
}
