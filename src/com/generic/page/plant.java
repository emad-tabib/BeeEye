package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;

public class plant extends SelTestCase {

	public boolean valid = true;
	
	public String user = "NA";
	public String plant = "NA";
	public String login = "FAIL";

	// general
	public double PEI_value = 0;
	public double PEI_percentage = 0;
	public double PERF_value = 0;
	public double PERF_percentage = 0;
	public double Avilability_value = 0;
	public double Avilability_percentage = 0;
	
	// Performance 
	public double Inverters_Performance_Percent  = 0;
	public double Strings_Performance_Percent = 0;

	// aggregation
	public double inverters___Performance = 0;
	public double inverters_Mppt = 0;
	public double inverters_relative_efficiency = 0;
	public double inverters_efficiency_below_spec = 0;
	public double inverters___availability = 0;
	public double inverters___downtime = 0;
	public double strings___performance = 0;
	public double strings___panel_degradation = 0;
	public double strings___disconnected_strings = 0;
	public double strings___availability = 0;

	// Health
	public double Health_PRpercentage = 0;
	public double Health_PR_STCpercentage = 0;
	public double Health_Data_integrity_percentage = 0;
	public double Health_plantAvilability_percentage = 0;
	public double Health_Power_Factor_percentage = 0;

	// Heat map
	public double Healty_Inverters = 0;
	public double Healty_Strings = 0;
	public double OK_Inverters = 0;
	public double OK_Strings = 0;
	public double Faulty_Inverters = 0;
	public double Faulty_Strings = 0;
	public double Messing_data_inverters = 0;
	public double Messing_data_Strings = 0;

	// insight
	public double Disconnected_Strings_total_devices = 0;
	public double Disconnected_Strings_faulty_devices = 0;
	public double Disconnected_Strings_value = 0;
	public double Disconnected_Strings_percentage = 0;
	public String Disconnected_Strings_status = "Healthy";

	//same as Recoverable DC power
	public double Panel_Degradation_total_devices = 0;
	public double Panel_Degradation_faulty_devices = 0;
	public double Panel_Degradation_value = 0;
	public double Panel_Degradation_percentage = 0;
	public String Panel_Degradation_status = "Healthy";

	public double String_Data_integrity_total_devices = 0;
	public double String_Data_integrity_faulty_devices = 0;
	public double String_Data_integrity_value = 0;
	public double String_Data_integrity_percentage = 0;
	public String String_Data_integrity_status = "Healthy";

	public double Temperature_Coefficient_total_devices = 0;
	public double Temperature_Coefficient_faulty_devices = 0;
	public double Temperature_Coefficient_value = 0;
	public double Temperature_Coefficientpercentage = 0;
	public String Temperature_Coefficient__status = "Healthy";

	public double Inverter_Downtime_total_devices = 0;
	public double Inverter_Downtime_faulty_devices = 0;
	public double Inverter_Downtime_value = 0;
	public double Inverter_Downtime_percentage = 0;
	public String Inverter_Downtime__status = "Healthy";

	public double Inverter_Data_integrity_total_devices = 0;
	public double Inverter_Data_integrity_faulty_devices = 0;
	public double Inverter_Data_integrity_value = 0;
	public double Inverter_Data_integrity_percentage = 0;
	public String Inverter_Data_integrity_status = "Healthy";

	public double Inverter_Efficiency_Below_Spec_total_devices = 0;
	public double Inverter_Efficiency_Below_Spec_faulty_devices = 0;
	public double Inverter_Efficiency_Below_Spec_value = 0;
	public double Inverter_Efficiency_Below_Spec_percentage = 0;
	public String Inverter_Efficiency_Below_Spec_status = "Healthy";

	public double Late_Awakening_total_devices = 0;
	public double Late_Awakening_faulty_devices = 0;
	public double Late_Awakening_value = 0;
	public double Late_Awakening_percentage = 0;
	public String Late_Awakening_status = "Healthy";

	public double Mppt_total_devices = 0;
	public double Mppt_faulty_devices = 0;
	public double Mppt_value = 0;
	public double Mppt_percentage = 0;
	public String Mppt_status = "Healthy";

	public double Inverter_Relative_Efficiency_total_devices = 0;
	public double Inverter_Relative_Efficiency_faulty_devices = 0;
	public double Inverter_Relative_Efficiency_value = 0;
	public double Inverter_Relative_Efficiency_percentage = 0;
	public String Inverter_Relative_Efficiency_status = "Healthy";

	public double Clipping_total_devices = 0;
	public double Clipping_faulty_devices = 0;
	public double Clipping_value = 0;
	public double Clipping_percentage = 0;
	public String Clipping_status = "Healthy";

	public double Power_Factor_total_devices = 0;
	public double Power_Factor_faulty_devices = 0;
	public double Power_Factor_value = 0;
	public double Power_Factor_percentage = 0;
	public String Power_Factor_status = "Healthy";

	public double Temperature_Alert_total_devices = 0;
	public double Temperature_Alert_faulty_devices = 0;
	public double Temperature_Alert_value = 0;
	public double Temperature_Alert_percentage = 0;
	public String Temperature_Alert_status = "Healthy";

	public double Frequency_Deviation_total_devices = 0;
	public double Frequency_Deviation_faulty_devices = 0;
	public double Frequency_Deviation_value = 0;
	public double Frequency_Deviation_percentage = 0;
	public String Frequency_Deviation_status = "Healthy";

	public double Voltage_Deviation_total_devices = 0;
	public double Voltage_Deviation_faulty_devices = 0;
	public double Voltage_Deviation_value = 0;
	public double Voltage_Deviation_percentage = 0;
	public String Voltage_Deviation_status = "Healthy";
	
	private static String isRed ="Red";
	private static String isBlue ="Blue";
	
	// RC- ready
	public static void printPlant(plant tmpPlant) {
		getCurrentFunctionName(true);
		try {
			logs.debug("valid:" + tmpPlant.valid);
			logs.debug("user:" + tmpPlant.user);
			logs.debug("plant:" + tmpPlant.plant);
			logs.debug("login:" + tmpPlant.login);
			logs.debug("PEI_value:" + tmpPlant.PEI_value);
			logs.debug("PEI_percentage:" + tmpPlant.PEI_percentage);
			logs.debug("PERF_value:" + tmpPlant.PERF_value);
			logs.debug("PERF_percentage:" + tmpPlant.PERF_percentage);
			logs.debug("Avilability_value:" + tmpPlant.Avilability_value);
			logs.debug("Inverters_Performance_Percent:" + tmpPlant.Inverters_Performance_Percent);
			logs.debug("Strings_Performance_Percent:" + tmpPlant.Strings_Performance_Percent);
			logs.debug("Avilability_percentage:" + tmpPlant.Avilability_percentage);
			logs.debug("inverters___Performance:" + tmpPlant.inverters___Performance);
			logs.debug("inverters_Mppt:" + tmpPlant.inverters_Mppt);
			logs.debug("inverters_relative_efficiency:" + tmpPlant.inverters_relative_efficiency);
			logs.debug("inverters_efficiency_below_spec:" + tmpPlant.inverters_efficiency_below_spec);
			logs.debug("inverters___availability:" + tmpPlant.inverters___availability);
			logs.debug("inverters___downtime:" + tmpPlant.inverters___downtime);
			logs.debug("strings___performance:" + tmpPlant.strings___performance);
			logs.debug("strings___panel_degradation:" + tmpPlant.strings___panel_degradation);
			logs.debug("strings___disconnected_strings:" + tmpPlant.strings___disconnected_strings);
			logs.debug("strings___availability:" + tmpPlant.strings___availability);
			logs.debug("Health_PRpercentage:" + tmpPlant.Health_PRpercentage);
			logs.debug("Health_PR_STCpercentage:" + tmpPlant.Health_PR_STCpercentage);
			logs.debug("Health_Data_integrity_percentage:" + tmpPlant.Health_Data_integrity_percentage);
			logs.debug("Health_plantAvilability_percentage:" + tmpPlant.Health_plantAvilability_percentage);
			logs.debug("Health_Power_Factor_percentage:" + tmpPlant.Health_Power_Factor_percentage);
			logs.debug("Healty_Inverters:" + tmpPlant.Healty_Inverters);
			logs.debug("Healty_Strings:" + tmpPlant.Healty_Strings);
			logs.debug("OK_Inverters:" + tmpPlant.OK_Inverters);
			logs.debug("OK_Strings:" + tmpPlant.OK_Strings);
			logs.debug("Faulty_Inverters:" + tmpPlant.Faulty_Inverters);
			logs.debug("Faulty_Strings:" + tmpPlant.Faulty_Strings);
			logs.debug("Messing_data_inverters:" + tmpPlant.Messing_data_inverters);
			logs.debug("Messing_data_Strings:" + tmpPlant.Messing_data_Strings);
			logs.debug("Disconnected_Strings_total_devices:" + tmpPlant.Disconnected_Strings_total_devices);
			logs.debug("Disconnected_Strings_faulty_devices:" + tmpPlant.Disconnected_Strings_faulty_devices);
			logs.debug("Disconnected_Strings_value:" + tmpPlant.Disconnected_Strings_value);
			logs.debug("Disconnected_Strings_percentage:" + tmpPlant.Disconnected_Strings_percentage);
			logs.debug("Disconnected_Strings_status:" + tmpPlant.Disconnected_Strings_status);
			logs.debug("Panel_Degradation_total_devices:" + tmpPlant.Panel_Degradation_total_devices);
			logs.debug("Panel_Degradation_faulty_devices:" + tmpPlant.Panel_Degradation_faulty_devices);
			logs.debug("Panel_Degradation_value:" + tmpPlant.Panel_Degradation_value);
			logs.debug("Panel_Degradation_percentage:" + tmpPlant.Panel_Degradation_percentage);
			logs.debug("Panel_Degradation_status:" + tmpPlant.Panel_Degradation_status);
			logs.debug("String_Data_integrity_total_devices:" + tmpPlant.String_Data_integrity_total_devices);
			logs.debug("String_Data_integrity_faulty_devices:" + tmpPlant.String_Data_integrity_faulty_devices);
			logs.debug("String_Data_integrity_value:" + tmpPlant.String_Data_integrity_value);
			logs.debug("String_Data_integrity_percentage:" + tmpPlant.String_Data_integrity_percentage);
			logs.debug("String_Data_integrity_status:" + tmpPlant.String_Data_integrity_status);
			logs.debug("Temperature_Coefficient_total_devices:" + tmpPlant.Temperature_Coefficient_total_devices);
			logs.debug("Temperature_Coefficient_faulty_devices:" + tmpPlant.Temperature_Coefficient_faulty_devices);
			logs.debug("Temperature_Coefficient_value:" + tmpPlant.Temperature_Coefficient_value);
			logs.debug("Temperature_Coefficientpercentage:" + tmpPlant.Temperature_Coefficientpercentage);
			logs.debug("Temperature_Coefficient__status:" + tmpPlant.Temperature_Coefficient__status);
			logs.debug("Inverter_Downtime_total_devices:" + tmpPlant.Inverter_Downtime_total_devices);
			logs.debug("Inverter_Downtime_faulty_devices:" + tmpPlant.Inverter_Downtime_faulty_devices);
			logs.debug("Inverter_Downtime_value:" + tmpPlant.Inverter_Downtime_value);
			logs.debug("Inverter_Downtime_percentage:" + tmpPlant.Inverter_Downtime_percentage);
			logs.debug("Inverter_Downtime__status:" + tmpPlant.Inverter_Downtime__status);
			logs.debug("Inverter_Data_integrity_total_devices:" + tmpPlant.Inverter_Data_integrity_total_devices);
			logs.debug("Inverter_Data_integrity_faulty_devices:" + tmpPlant.Inverter_Data_integrity_faulty_devices);
			logs.debug("Inverter_Data_integrity_value:" + tmpPlant.Inverter_Data_integrity_value);
			logs.debug("Inverter_Data_integrity_percentage:" + tmpPlant.Inverter_Data_integrity_percentage);
			logs.debug("Inverter_Data_integrity_status:" + tmpPlant.Inverter_Data_integrity_status);
			logs.debug("Inverter_Efficiency_Below_Spec_total_devices:"
					+ tmpPlant.Inverter_Efficiency_Below_Spec_total_devices);
			logs.debug("Inverter_Efficiency_Below_Spec_faulty_devices:"
					+ tmpPlant.Inverter_Efficiency_Below_Spec_faulty_devices);
			logs.debug("Inverter_Efficiency_Below_Spec_value:" + tmpPlant.Inverter_Efficiency_Below_Spec_value);
			logs.debug(
					"Inverter_Efficiency_Below_Spec_percentage:" + tmpPlant.Inverter_Efficiency_Below_Spec_percentage);
			logs.debug("Inverter_Efficiency_Below_Spec_status:" + tmpPlant.Inverter_Efficiency_Below_Spec_status);
			logs.debug("Late_Awakening_total_devices:" + tmpPlant.Late_Awakening_total_devices);
			logs.debug("Late_Awakening_faulty_devices:" + tmpPlant.Late_Awakening_faulty_devices);
			logs.debug("Late_Awakening_value:" + tmpPlant.Late_Awakening_value);
			logs.debug("Late_Awakening_percentage:" + tmpPlant.Late_Awakening_percentage);
			logs.debug("Late_Awakening_status:" + tmpPlant.Late_Awakening_status);
			logs.debug("Mppt_total_devices:" + tmpPlant.Mppt_total_devices);
			logs.debug("Mppt_faulty_devices:" + tmpPlant.Mppt_faulty_devices);
			logs.debug("Mppt_value:" + tmpPlant.Mppt_value);
			logs.debug("Mppt_percentage:" + tmpPlant.Mppt_percentage);
			logs.debug("Mppt_status:" + tmpPlant.Mppt_status);
			logs.debug("Inverter_Relative_Efficiency_total_devices:"
					+ tmpPlant.Inverter_Relative_Efficiency_total_devices);
			logs.debug("Inverter_Relative_Efficiency_faulty_devices:"
					+ tmpPlant.Inverter_Relative_Efficiency_faulty_devices);
			logs.debug("Inverter_Relative_Efficiency_value:" + tmpPlant.Inverter_Relative_Efficiency_value);
			logs.debug("Inverter_Relative_Efficiency_percentage:" + tmpPlant.Inverter_Relative_Efficiency_percentage);
			logs.debug("Inverter_Relative_Efficiency_status:" + tmpPlant.Inverter_Relative_Efficiency_status);
			logs.debug("Clipping_total_devices:" + tmpPlant.Clipping_total_devices);
			logs.debug("Clipping_faulty_devices:" + tmpPlant.Clipping_faulty_devices);
			logs.debug("Clipping_value:" + tmpPlant.Clipping_value);
			logs.debug("Clipping_percentage:" + tmpPlant.Clipping_percentage);
			logs.debug("Clipping_status:" + tmpPlant.Clipping_status);
			logs.debug("Power_Factor_total_devices:" + tmpPlant.Power_Factor_total_devices);
			logs.debug("Power_Factor_faulty_devices:" + tmpPlant.Power_Factor_faulty_devices);
			logs.debug("Power_Factor_value:" + tmpPlant.Power_Factor_value);
			logs.debug("Power_Factor_percentage:" + tmpPlant.Power_Factor_percentage);
			logs.debug("Power_Factor_status:" + tmpPlant.Power_Factor_status);
			logs.debug("Temperature_Alert_total_devices:" + tmpPlant.Temperature_Alert_total_devices);
			logs.debug("Temperature_Alert_faulty_devices:" + tmpPlant.Temperature_Alert_faulty_devices);
			logs.debug("Temperature_Alert_value:" + tmpPlant.Temperature_Alert_value);
			logs.debug("Temperature_Alert_percentage:" + tmpPlant.Temperature_Alert_percentage);
			logs.debug("Temperature_Alert_status:" + tmpPlant.Temperature_Alert_status);
			logs.debug("Frequency_Deviation_total_devices:" + tmpPlant.Frequency_Deviation_total_devices);
			logs.debug("Frequency_Deviation_faulty_devices:" + tmpPlant.Frequency_Deviation_faulty_devices);
			logs.debug("Frequency_Deviation_value:" + tmpPlant.Frequency_Deviation_value);
			logs.debug("Frequency_Deviation_percentage:" + tmpPlant.Frequency_Deviation_percentage);
			logs.debug("Frequency_Deviation_status:" + tmpPlant.Frequency_Deviation_status);
			logs.debug("Voltage_Deviation_total_devices:" + tmpPlant.Voltage_Deviation_total_devices);
			logs.debug("Voltage_Deviation_faulty_devices:" + tmpPlant.Voltage_Deviation_faulty_devices);
			logs.debug("Voltage_Deviation_value:" + tmpPlant.Voltage_Deviation_value);
			logs.debug("Voltage_Deviation_percentage:" + tmpPlant.Voltage_Deviation_percentage);
			logs.debug("Voltage_Deviation_status:" + tmpPlant.Voltage_Deviation_status);

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);
	}// print

	public static void comparPlantsAndwriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			compareGeneralDataAndWriteResults(tmpPlant, previousPlantData);
			comparePerformanceDataAndWriteResults(tmpPlant, previousPlantData);
			compareAggregationDataAndWriteResults(tmpPlant, previousPlantData);
			compareHealthDataAndWriteResults(tmpPlant, previousPlantData);
			compareHeatMapDataAndWriteResults(tmpPlant, previousPlantData);
			compareInsightsDataAndWriteResults(tmpPlant, previousPlantData);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);

	}// comparPlantsAndwriteResults

	private static void compareGeneralDataAndWriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			ArrayList<String> generalData = new ArrayList<String>();
			double Delta = 0;
			String Deltas = "";

			generalData.add(tmpPlant.login);

			Delta = previousPlantData.PEI_value - tmpPlant.PEI_value;
			generalData.add(previousPlantData.PEI_value + "");
			generalData.add(tmpPlant.PEI_value + "");		
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.PEI_percentage - tmpPlant.PEI_percentage;
			generalData.add(previousPlantData.PEI_percentage + "%");
			generalData.add(tmpPlant.PEI_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");
			
			Delta = previousPlantData.PERF_value - tmpPlant.PERF_value;
			generalData.add(previousPlantData.PERF_value + "");
			generalData.add(tmpPlant.PERF_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.PERF_percentage - tmpPlant.PERF_percentage;
			generalData.add(previousPlantData.PERF_percentage + "%");
			generalData.add(tmpPlant.PERF_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta > 0.5 || Delta < -0.5 ) ? Deltas+isRed+"%" : Deltas);

			Delta = previousPlantData.Avilability_value - tmpPlant.Avilability_value;
			generalData.add(previousPlantData.Avilability_value + "");
			generalData.add(tmpPlant.Avilability_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Avilability_percentage - tmpPlant.Avilability_percentage;
			generalData.add(previousPlantData.Avilability_percentage + "%");
			generalData.add(tmpPlant.Avilability_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta > 0.5 || Delta < -0.5 ) ? Deltas+isRed+"%" : Deltas);
			
			Boolean writing_passed = true; 
			for (int dataIndex = 0; dataIndex < generalData.size(); dataIndex++) {
				writing_passed = writing_passed & getDatatable().setCellData(SheetVariables.GeneralTab, dataIndex + 4, tmpPlant.plant,
						generalData.get(dataIndex), tmpPlant.valid);
			}
			sassert().assertTrue(writing_passed,"writing data for plant "+tmpPlant.plant+" from user "+tmpPlant.user+" is failed");

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);

	}// compareGeneralDataAndWriteResults
	
	
	private static void comparePerformanceDataAndWriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			ArrayList<String> generalData = new ArrayList<String>();
			double Delta = 0;
			String Deltas = "";


			Delta = previousPlantData.Inverters_Performance_Percent - tmpPlant.Inverters_Performance_Percent;
			generalData.add(previousPlantData.Inverters_Performance_Percent + "");
			generalData.add(tmpPlant.Inverters_Performance_Percent + "");		
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta > 0.5 || Delta < -0.5 ) ? Deltas+isRed+"%" : Deltas);

			Delta = previousPlantData.Strings_Performance_Percent - tmpPlant.Strings_Performance_Percent;
			generalData.add(previousPlantData.Strings_Performance_Percent + "");
			generalData.add(tmpPlant.Strings_Performance_Percent + "");		
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta > 0.5 || Delta < -0.5 ) ? Deltas+isRed+"%" : Deltas);
			
			Boolean writing_passed = true; 
			for (int dataIndex = 0; dataIndex < generalData.size(); dataIndex++) {
				writing_passed = writing_passed & getDatatable().setCellData(SheetVariables.PerformanceTab, dataIndex + 4, tmpPlant.plant,
						generalData.get(dataIndex), tmpPlant.valid);
			}
			sassert().assertTrue(writing_passed,"writing data for plant "+tmpPlant.plant+" from user "+tmpPlant.user+" is failed");

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);

	}// comparePerformanceDataAndWriteResults

	private static void compareAggregationDataAndWriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			ArrayList<String> generalData = new ArrayList<String>();
			
			// Inverters performance
			generalData.add(tmpPlant.inverters___Performance + "");
			if (PlantOverview_General.getCalcualtedInvertersPerformance(tmpPlant)
					- tmpPlant.inverters___Performance != 0)
				generalData.add(TestUtilities.roundingFormater
						.format(PlantOverview_General.getCalcualtedInvertersPerformance(tmpPlant)) + isRed);
			else
				generalData.add(TestUtilities.roundingFormater
						.format(PlantOverview_General.getCalcualtedInvertersPerformance(tmpPlant)) + "");
			generalData.add(tmpPlant.inverters_Mppt + "");
			generalData.add(tmpPlant.inverters_relative_efficiency + "");
			generalData.add(tmpPlant.inverters_efficiency_below_spec + "");

			
			// Inverters availability
			generalData.add(tmpPlant.inverters___availability + "");
			if (tmpPlant.inverters___availability - tmpPlant.inverters___downtime != 0)
				generalData.add(tmpPlant.inverters___downtime + isRed);
			else
				generalData.add(tmpPlant.inverters___downtime + "");

			
			// Strings performance
			generalData.add(tmpPlant.strings___performance + "");
			if (tmpPlant.strings___performance - PlantOverview_General.getCalculatedStringsPerformance(tmpPlant) != 0)
				generalData.add(PlantOverview_General.getCalculatedStringsPerformance(tmpPlant) + isRed);
			else
			generalData.add(TestUtilities.roundingFormater
					.format(PlantOverview_General.getCalculatedStringsPerformance(tmpPlant)) + "");
			generalData.add(tmpPlant.strings___panel_degradation + "");
			generalData.add(tmpPlant.strings___disconnected_strings + "");

			
			
			// Strings availability
			generalData.add(tmpPlant.strings___availability + "");
			generalData.add("0");

			Boolean writing_passed = true; 
			for (int dataIndex = 0; dataIndex < generalData.size(); dataIndex++) {
				writing_passed = writing_passed & getDatatable().setCellData(SheetVariables.Aggregation, dataIndex + 4, tmpPlant.plant,
						generalData.get(dataIndex), tmpPlant.valid);
			}
			sassert().assertTrue(writing_passed,"writing data for plant "+tmpPlant.plant+" from user "+tmpPlant.user+" is failed");
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			
			throw e;
		} catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
		}
		getCurrentFunctionName(false);

	}// compareAggregationDataAndWriteResults

	private static void compareHealthDataAndWriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			ArrayList<String> generalData = new ArrayList<String>();

			double Delta = 0;
			String Deltas = "";

			Delta = previousPlantData.Health_PRpercentage - tmpPlant.Health_PRpercentage;
			generalData.add(previousPlantData.Health_PRpercentage + "%");
			generalData.add(tmpPlant.Health_PRpercentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			Delta = previousPlantData.Health_PR_STCpercentage - tmpPlant.Health_PR_STCpercentage;
			generalData.add(previousPlantData.Health_PR_STCpercentage + "%");
			generalData.add(tmpPlant.Health_PR_STCpercentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			Delta = previousPlantData.Health_Data_integrity_percentage - tmpPlant.Health_Data_integrity_percentage;
			generalData.add(previousPlantData.Health_Data_integrity_percentage + "%");
			generalData.add(tmpPlant.Health_Data_integrity_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			Delta = previousPlantData.Health_plantAvilability_percentage - tmpPlant.Health_plantAvilability_percentage;
			generalData.add(previousPlantData.Health_plantAvilability_percentage + "%");
			generalData.add(tmpPlant.Health_plantAvilability_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			Delta = previousPlantData.Health_Power_Factor_percentage - tmpPlant.Health_Power_Factor_percentage;
			generalData.add(previousPlantData.Health_Power_Factor_percentage + "%");
			generalData.add(tmpPlant.Health_Power_Factor_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			Boolean writing_passed = true; 
			for (int dataIndex = 0; dataIndex < generalData.size(); dataIndex++) {
				writing_passed = writing_passed & getDatatable().setCellData(SheetVariables.HealthTab, dataIndex + 4, tmpPlant.plant,
						generalData.get(dataIndex), tmpPlant.valid);
			}
			sassert().assertTrue(writing_passed,"writing data for plant "+tmpPlant.plant+" from user "+tmpPlant.user+" is failed");
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);

	}// compareHealthDataAndWriteResults

	private static void compareHeatMapDataAndWriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			ArrayList<String> generalData = new ArrayList<String>();

			double Delta = 0;
			String Deltas = "";
			
			Delta = previousPlantData.Healty_Inverters - tmpPlant.Healty_Inverters;
			generalData.add(previousPlantData.Healty_Inverters + "");
			generalData.add(tmpPlant.Healty_Inverters + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Healty_Strings - tmpPlant.Healty_Strings;
			generalData.add(previousPlantData.Healty_Strings + "");
			generalData.add(tmpPlant.Healty_Strings + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.OK_Inverters - tmpPlant.OK_Inverters;
			generalData.add(previousPlantData.OK_Inverters + "");
			generalData.add(tmpPlant.OK_Inverters + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.OK_Strings - tmpPlant.OK_Strings;
			generalData.add(previousPlantData.OK_Strings + "");
			generalData.add(tmpPlant.OK_Strings + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Faulty_Inverters - tmpPlant.Faulty_Inverters;
			generalData.add(previousPlantData.Faulty_Inverters + "");
			generalData.add(tmpPlant.Faulty_Inverters + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Faulty_Strings - tmpPlant.Faulty_Strings;
			generalData.add(previousPlantData.Faulty_Strings + "");
			generalData.add(tmpPlant.Faulty_Strings + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Messing_data_inverters - tmpPlant.Messing_data_inverters;
			generalData.add(previousPlantData.Messing_data_inverters + "");
			generalData.add(tmpPlant.Messing_data_inverters + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Messing_data_Strings - tmpPlant.Messing_data_Strings;
			generalData.add(previousPlantData.Messing_data_Strings + "");
			generalData.add(tmpPlant.Messing_data_Strings + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Boolean writing_passed = true; 
			for (int dataIndex = 0; dataIndex < generalData.size(); dataIndex++) {
				writing_passed = writing_passed & getDatatable().setCellData(SheetVariables.HeatMapTab, dataIndex + 4, tmpPlant.plant,
						generalData.get(dataIndex), tmpPlant.valid);
			}
			sassert().assertTrue(writing_passed,"writing data for plant "+tmpPlant.plant+" from user "+tmpPlant.user+" is failed");
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);
	}// compareHeatMapDataAndWriteResults

	private static void compareInsightsDataAndWriteResults(plant tmpPlant, plant previousPlantData) {
		getCurrentFunctionName(true);
		try {
			ArrayList<String> generalData = new ArrayList<String>();

			double Delta = 0;
			boolean DeltaString = false;
			String Deltas = "";

			
			Delta = previousPlantData.Disconnected_Strings_total_devices - tmpPlant.Disconnected_Strings_total_devices;
			generalData.add(previousPlantData.Disconnected_Strings_total_devices + "");
			generalData.add(tmpPlant.Disconnected_Strings_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Disconnected_Strings_faulty_devices
					- tmpPlant.Disconnected_Strings_faulty_devices;
			generalData.add(previousPlantData.Disconnected_Strings_faulty_devices + "");
			generalData.add(tmpPlant.Disconnected_Strings_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Disconnected_Strings_value - tmpPlant.Disconnected_Strings_value;
			generalData.add(previousPlantData.Disconnected_Strings_value + "");
			generalData.add(tmpPlant.Disconnected_Strings_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Disconnected_Strings_percentage - tmpPlant.Disconnected_Strings_percentage;
			generalData.add(previousPlantData.Disconnected_Strings_percentage + "%");
			generalData.add(tmpPlant.Disconnected_Strings_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Disconnected_Strings_status.trim()
					.equals(tmpPlant.Disconnected_Strings_status.trim());
			generalData.add(previousPlantData.Disconnected_Strings_status + "");
			generalData.add(tmpPlant.Disconnected_Strings_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Panel_Degradation_total_devices - tmpPlant.Panel_Degradation_total_devices;
			generalData.add(previousPlantData.Panel_Degradation_total_devices + "");
			generalData.add(tmpPlant.Panel_Degradation_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);
			
			Delta = previousPlantData.Panel_Degradation_faulty_devices - tmpPlant.Panel_Degradation_faulty_devices;
			generalData.add(previousPlantData.Panel_Degradation_faulty_devices + "");
			generalData.add(tmpPlant.Panel_Degradation_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Panel_Degradation_value - tmpPlant.Panel_Degradation_value;
			generalData.add(previousPlantData.Panel_Degradation_value + "");
			generalData.add(tmpPlant.Panel_Degradation_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Panel_Degradation_percentage - tmpPlant.Panel_Degradation_percentage;
			generalData.add(previousPlantData.Panel_Degradation_percentage + "%");
			generalData.add(tmpPlant.Panel_Degradation_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Panel_Degradation_status.trim()
					.equals(tmpPlant.Panel_Degradation_status.trim());
			generalData.add(previousPlantData.Panel_Degradation_status + "");
			generalData.add(tmpPlant.Panel_Degradation_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.String_Data_integrity_total_devices
					- tmpPlant.String_Data_integrity_total_devices;
			generalData.add(previousPlantData.String_Data_integrity_total_devices + "");
			generalData.add(tmpPlant.String_Data_integrity_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.String_Data_integrity_faulty_devices
					- tmpPlant.String_Data_integrity_faulty_devices;
			generalData.add(previousPlantData.String_Data_integrity_faulty_devices + "");
			generalData.add(tmpPlant.String_Data_integrity_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);
			
			Delta = previousPlantData.String_Data_integrity_value - tmpPlant.String_Data_integrity_value;
			generalData.add(previousPlantData.String_Data_integrity_value + "");
			generalData.add(tmpPlant.String_Data_integrity_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.String_Data_integrity_percentage - tmpPlant.String_Data_integrity_percentage;
			generalData.add(previousPlantData.String_Data_integrity_percentage + "%");
			generalData.add(tmpPlant.String_Data_integrity_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.String_Data_integrity_status.trim()
					.equals(tmpPlant.String_Data_integrity_status.trim());
			generalData.add(previousPlantData.String_Data_integrity_status + "");
			generalData.add(tmpPlant.String_Data_integrity_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Temperature_Coefficient_total_devices
					- tmpPlant.Temperature_Coefficient_total_devices;
			generalData.add(previousPlantData.Temperature_Coefficient_total_devices + "");
			generalData.add(tmpPlant.Temperature_Coefficient_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Temperature_Coefficient_faulty_devices
					- tmpPlant.Temperature_Coefficient_faulty_devices;
			generalData.add(previousPlantData.Temperature_Coefficient_faulty_devices + "");
			generalData.add(tmpPlant.Temperature_Coefficient_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Temperature_Coefficient_value - tmpPlant.Temperature_Coefficient_value;
			generalData.add(previousPlantData.Temperature_Coefficient_value + "");
			generalData.add(tmpPlant.Temperature_Coefficient_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Temperature_Coefficientpercentage - tmpPlant.Temperature_Coefficientpercentage;
			generalData.add(previousPlantData.Temperature_Coefficientpercentage + "%");
			generalData.add(tmpPlant.Temperature_Coefficientpercentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Temperature_Coefficient__status.trim()
					.equals(tmpPlant.Temperature_Coefficient__status.trim());
			generalData.add(previousPlantData.Temperature_Coefficient__status + "");
			generalData.add(tmpPlant.Temperature_Coefficient__status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Inverter_Downtime_total_devices - tmpPlant.Inverter_Downtime_total_devices;
			generalData.add(previousPlantData.Inverter_Downtime_total_devices + "");
			generalData.add(tmpPlant.Inverter_Downtime_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Inverter_Downtime_faulty_devices - tmpPlant.Inverter_Downtime_faulty_devices;
			generalData.add(previousPlantData.Inverter_Downtime_faulty_devices + "");
			generalData.add(tmpPlant.Inverter_Downtime_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Inverter_Downtime_value - tmpPlant.Inverter_Downtime_value;
			generalData.add(previousPlantData.Inverter_Downtime_value + "");
			generalData.add(tmpPlant.Inverter_Downtime_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Inverter_Downtime_percentage - tmpPlant.Inverter_Downtime_percentage;
			generalData.add(previousPlantData.Inverter_Downtime_percentage + "%");
			generalData.add(tmpPlant.Inverter_Downtime_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Inverter_Downtime__status.trim()
					.equals(tmpPlant.Inverter_Downtime__status.trim());
			generalData.add(previousPlantData.Inverter_Downtime__status + "");
			generalData.add(tmpPlant.Inverter_Downtime__status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Inverter_Data_integrity_total_devices
					- tmpPlant.Inverter_Data_integrity_total_devices;
			generalData.add(previousPlantData.Inverter_Data_integrity_total_devices + "");
			generalData.add(tmpPlant.Inverter_Data_integrity_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Inverter_Data_integrity_faulty_devices
					- tmpPlant.Inverter_Data_integrity_faulty_devices;
			generalData.add(previousPlantData.Inverter_Data_integrity_faulty_devices + "");
			generalData.add(tmpPlant.Inverter_Data_integrity_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Inverter_Data_integrity_value - tmpPlant.Inverter_Data_integrity_value;
			generalData.add(previousPlantData.Inverter_Data_integrity_value + "");
			generalData.add(tmpPlant.Inverter_Data_integrity_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Inverter_Data_integrity_percentage - tmpPlant.Inverter_Data_integrity_percentage;
			generalData.add(previousPlantData.Inverter_Data_integrity_percentage + "%");
			generalData.add(tmpPlant.Inverter_Data_integrity_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");


			DeltaString = previousPlantData.Inverter_Data_integrity_status.trim()
					.equals(tmpPlant.Inverter_Data_integrity_status.trim());
			generalData.add(previousPlantData.Inverter_Data_integrity_status + "");
			generalData.add(tmpPlant.Inverter_Data_integrity_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Inverter_Efficiency_Below_Spec_total_devices
					- tmpPlant.Inverter_Efficiency_Below_Spec_total_devices;
			generalData.add(previousPlantData.Inverter_Efficiency_Below_Spec_total_devices + "");
			generalData.add(tmpPlant.Inverter_Efficiency_Below_Spec_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Inverter_Efficiency_Below_Spec_faulty_devices
					- tmpPlant.Inverter_Efficiency_Below_Spec_faulty_devices;
			generalData.add(previousPlantData.Inverter_Efficiency_Below_Spec_faulty_devices + "");
			generalData.add(tmpPlant.Inverter_Efficiency_Below_Spec_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Inverter_Efficiency_Below_Spec_value
					- tmpPlant.Inverter_Efficiency_Below_Spec_value;
			generalData.add(previousPlantData.Inverter_Efficiency_Below_Spec_value + "");
			generalData.add(tmpPlant.Inverter_Efficiency_Below_Spec_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Inverter_Efficiency_Below_Spec_percentage
					- tmpPlant.Inverter_Efficiency_Below_Spec_percentage;
			generalData.add(previousPlantData.Inverter_Efficiency_Below_Spec_percentage + "%");
			generalData.add(tmpPlant.Inverter_Efficiency_Below_Spec_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");


			DeltaString = previousPlantData.Inverter_Efficiency_Below_Spec_status.trim()
					.equals(tmpPlant.Inverter_Efficiency_Below_Spec_status.trim());
			generalData.add(previousPlantData.Inverter_Efficiency_Below_Spec_status + "");
			generalData.add(tmpPlant.Inverter_Efficiency_Below_Spec_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Late_Awakening_total_devices - tmpPlant.Late_Awakening_total_devices;
			generalData.add(previousPlantData.Late_Awakening_total_devices + "");
			generalData.add(tmpPlant.Late_Awakening_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Late_Awakening_faulty_devices - tmpPlant.Late_Awakening_faulty_devices;
			generalData.add(previousPlantData.Late_Awakening_faulty_devices + "");
			generalData.add(tmpPlant.Late_Awakening_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Late_Awakening_value - tmpPlant.Late_Awakening_value;
			generalData.add(previousPlantData.Late_Awakening_value + "");
			generalData.add(tmpPlant.Late_Awakening_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);


			Delta = previousPlantData.Late_Awakening_percentage - tmpPlant.Late_Awakening_percentage;
			generalData.add(previousPlantData.Late_Awakening_percentage + "%");
			generalData.add(tmpPlant.Late_Awakening_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Late_Awakening_status.trim().equals(tmpPlant.Late_Awakening_status.trim());
			generalData.add(previousPlantData.Late_Awakening_status + "");
			generalData.add(tmpPlant.Late_Awakening_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Mppt_total_devices - tmpPlant.Mppt_total_devices;
			generalData.add(previousPlantData.Mppt_total_devices + "");
			generalData.add(tmpPlant.Mppt_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Mppt_faulty_devices - tmpPlant.Mppt_faulty_devices;
			generalData.add(previousPlantData.Mppt_faulty_devices + "");
			generalData.add(tmpPlant.Mppt_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Mppt_value - tmpPlant.Mppt_value;
			generalData.add(previousPlantData.Mppt_value + "");
			generalData.add(tmpPlant.Mppt_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Mppt_percentage - tmpPlant.Mppt_percentage;
			generalData.add(previousPlantData.Mppt_percentage + "%");
			generalData.add(tmpPlant.Mppt_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Mppt_status.trim().equals(tmpPlant.Mppt_status.trim());
			generalData.add(previousPlantData.Mppt_status + "");
			generalData.add(tmpPlant.Mppt_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Inverter_Relative_Efficiency_total_devices
					- tmpPlant.Inverter_Relative_Efficiency_total_devices;
			generalData.add(previousPlantData.Inverter_Relative_Efficiency_total_devices + "");
			generalData.add(tmpPlant.Inverter_Relative_Efficiency_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Inverter_Relative_Efficiency_faulty_devices
					- tmpPlant.Inverter_Relative_Efficiency_faulty_devices;
			generalData.add(previousPlantData.Inverter_Relative_Efficiency_faulty_devices + "");
			generalData.add(tmpPlant.Inverter_Relative_Efficiency_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Inverter_Relative_Efficiency_value - tmpPlant.Inverter_Relative_Efficiency_value;
			generalData.add(previousPlantData.Inverter_Relative_Efficiency_value + "");
			generalData.add(tmpPlant.Inverter_Relative_Efficiency_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Inverter_Relative_Efficiency_percentage
					- tmpPlant.Inverter_Relative_Efficiency_percentage;
			generalData.add(previousPlantData.Inverter_Relative_Efficiency_percentage + "%");
			generalData.add(tmpPlant.Inverter_Relative_Efficiency_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");
			
			DeltaString = previousPlantData.Inverter_Relative_Efficiency_status.trim()
					.equals(tmpPlant.Inverter_Relative_Efficiency_status.trim());
			generalData.add(previousPlantData.Inverter_Relative_Efficiency_status + "");
			generalData.add(tmpPlant.Inverter_Relative_Efficiency_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Clipping_total_devices - tmpPlant.Clipping_total_devices;
			generalData.add(previousPlantData.Clipping_total_devices + "");
			generalData.add(tmpPlant.Clipping_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Clipping_faulty_devices - tmpPlant.Clipping_faulty_devices;
			generalData.add(previousPlantData.Clipping_faulty_devices + "");
			generalData.add(tmpPlant.Clipping_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Clipping_value - tmpPlant.Clipping_value;
			generalData.add(previousPlantData.Clipping_value + "");
			generalData.add(tmpPlant.Clipping_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Clipping_percentage - tmpPlant.Clipping_percentage;
			generalData.add(previousPlantData.Clipping_percentage + "%");
			generalData.add(tmpPlant.Clipping_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Clipping_status.trim().equals(tmpPlant.Clipping_status.trim());
			generalData.add(previousPlantData.Clipping_status + "");
			generalData.add(tmpPlant.Clipping_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Power_Factor_total_devices - tmpPlant.Power_Factor_total_devices;
			generalData.add(previousPlantData.Power_Factor_total_devices + "");
			generalData.add(tmpPlant.Power_Factor_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Power_Factor_faulty_devices - tmpPlant.Power_Factor_faulty_devices;
			generalData.add(previousPlantData.Power_Factor_faulty_devices + "");
			generalData.add(tmpPlant.Power_Factor_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Power_Factor_value - tmpPlant.Power_Factor_value;
			generalData.add(previousPlantData.Power_Factor_value + "");
			generalData.add(tmpPlant.Power_Factor_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Power_Factor_percentage - tmpPlant.Power_Factor_percentage;
			generalData.add(previousPlantData.Power_Factor_percentage + "%");
			generalData.add(tmpPlant.Power_Factor_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Power_Factor_status.trim().equals(tmpPlant.Power_Factor_status.trim());
			generalData.add(previousPlantData.Power_Factor_status + "");
			generalData.add(tmpPlant.Power_Factor_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Temperature_Alert_total_devices - tmpPlant.Temperature_Alert_total_devices;
			generalData.add(previousPlantData.Temperature_Alert_total_devices + "");
			generalData.add(tmpPlant.Temperature_Alert_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Temperature_Alert_faulty_devices - tmpPlant.Temperature_Alert_faulty_devices;
			generalData.add(previousPlantData.Temperature_Alert_faulty_devices + "");
			generalData.add(tmpPlant.Temperature_Alert_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Temperature_Alert_value - tmpPlant.Temperature_Alert_value;
			generalData.add(previousPlantData.Temperature_Alert_value + "");
			generalData.add(tmpPlant.Temperature_Alert_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Temperature_Alert_percentage - tmpPlant.Temperature_Alert_percentage;
			generalData.add(previousPlantData.Temperature_Alert_percentage + "%");
			generalData.add(tmpPlant.Temperature_Alert_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Temperature_Alert_status.trim()
					.equals(tmpPlant.Temperature_Alert_status.trim());
			generalData.add(previousPlantData.Temperature_Alert_status + "");
			generalData.add(tmpPlant.Temperature_Alert_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Frequency_Deviation_total_devices - tmpPlant.Frequency_Deviation_total_devices;
			generalData.add(previousPlantData.Frequency_Deviation_total_devices + "");
			generalData.add(tmpPlant.Frequency_Deviation_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Frequency_Deviation_faulty_devices - tmpPlant.Frequency_Deviation_faulty_devices;
			generalData.add(previousPlantData.Frequency_Deviation_faulty_devices + "");
			generalData.add(tmpPlant.Frequency_Deviation_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Frequency_Deviation_value - tmpPlant.Frequency_Deviation_value;
			generalData.add(previousPlantData.Frequency_Deviation_value + "");
			generalData.add(tmpPlant.Frequency_Deviation_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Frequency_Deviation_percentage - tmpPlant.Frequency_Deviation_percentage;
			generalData.add(previousPlantData.Frequency_Deviation_percentage + "%");
			generalData.add(tmpPlant.Frequency_Deviation_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Frequency_Deviation_status.trim()
					.equals(tmpPlant.Frequency_Deviation_status.trim());
			generalData.add(previousPlantData.Frequency_Deviation_status + "");
			generalData.add(tmpPlant.Frequency_Deviation_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Delta = previousPlantData.Voltage_Deviation_total_devices - tmpPlant.Voltage_Deviation_total_devices;
			generalData.add(previousPlantData.Voltage_Deviation_total_devices + "");
			generalData.add(tmpPlant.Voltage_Deviation_total_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Voltage_Deviation_faulty_devices - tmpPlant.Voltage_Deviation_faulty_devices;
			generalData.add(previousPlantData.Voltage_Deviation_faulty_devices + "");
			generalData.add(tmpPlant.Voltage_Deviation_faulty_devices + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Voltage_Deviation_value - tmpPlant.Voltage_Deviation_value;
			generalData.add(previousPlantData.Voltage_Deviation_value + "");
			generalData.add(tmpPlant.Voltage_Deviation_value + "");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isBlue);

			Delta = previousPlantData.Voltage_Deviation_percentage - tmpPlant.Voltage_Deviation_percentage;
			generalData.add(previousPlantData.Voltage_Deviation_percentage + "%");
			generalData.add(tmpPlant.Voltage_Deviation_percentage + "%");
			Deltas = TestUtilities.roundingFormater.format(Delta);
			generalData.add((Delta == 0) ? Deltas : Deltas+isRed+"%");

			DeltaString = previousPlantData.Voltage_Deviation_status.trim()
					.equals(tmpPlant.Voltage_Deviation_status.trim());
			generalData.add(previousPlantData.Voltage_Deviation_status + "");
			generalData.add(tmpPlant.Voltage_Deviation_status + "");
			generalData.add((DeltaString == true) ? DeltaString+"" : DeltaString+isRed);

			Boolean writing_passed = true; 
			for (int dataIndex = 0; dataIndex < generalData.size(); dataIndex++) {
				writing_passed = writing_passed & getDatatable().setCellData(SheetVariables.InsightsTabs, dataIndex + 4, tmpPlant.plant,
						generalData.get(dataIndex), tmpPlant.valid);
			}
			sassert().assertTrue(writing_passed,"writing data for plant "+tmpPlant.plant+" from user "+tmpPlant.user+" is failed");
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);
	}// compareInsightsDataAndWriteResults

}