package com.generic.page;

import java.text.MessageFormat;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.TestUtilities;

public class PlantOverview_PlantInsights extends SelTestCase {

	public static double getInsightTotalDevices(String totalDevicesText) {
		getCurrentFunctionName(true);
		try {
			getCurrentFunctionName(false);
			return Double.parseDouble(totalDevicesText.trim().substring(13, totalDevicesText.length()));
		} catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static double getInsightTotalFaultyDevices(String totalFaultyDevicesText) {
		getCurrentFunctionName(true);

		try {
			getCurrentFunctionName(false);
			return Double.parseDouble(totalFaultyDevicesText.trim().substring(
					totalFaultyDevicesText.trim().indexOf("(") + 1, totalFaultyDevicesText.trim().indexOf(")")));
		}

		catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static double getInsightValue(String valueText) {
		getCurrentFunctionName(true);

		try {
			getCurrentFunctionName(false);
			return Double
					.parseDouble(TestUtilities.roundingFormater.format(TestUtilities.valueParser(valueText.trim())));
		}

		catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static double getInsightPercentage(String percantageText) {
		getCurrentFunctionName(true);

		try {
			getCurrentFunctionName(false);
			if (percantageText.contains("%"))
				return TestUtilities.valueParser(percantageText);
			else
				return 0;
		}

		catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static String getInsightStatus(String statusText) {
		getCurrentFunctionName(true);

		try {
			getCurrentFunctionName(false);
			return statusText.trim().substring(0, statusText.trim().indexOf(" "));
		}

		catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}// getInsigtStatus

	public static void fillInsigtsIntoPlantObj(plant tmpPlant, String insightInfo) {
		getCurrentFunctionName(true);
		try {
			String[] insightInfoLines = insightInfo.replace("arrow_drop_down", "").replace("0:", "\n").split("\n");
			// The following is insightInfoLines
			/*
			 * panel degradation Contact us for a full revamping plan. Faulty (54) $8.63K
			 * 0.05% total devices54 investigate
			 */

			// columns
			int name = 0;
			int status = 2;
			int numberOfNotHealtyDevices = 2;
			int insightValue = 3;
			int insightPercent = 4;
			int totalDevices = insightInfoLines.length - 2; // NegativeIndex to cover both Faulty and OK status

			if (insightInfoLines[name].trim().toLowerCase().contains("Recoverable DC power".toLowerCase())) {
				tmpPlant.Panel_Degradation_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]); 
				tmpPlant.Panel_Degradation_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Panel_Degradation_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Panel_Degradation_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Panel_Degradation_status = getInsightStatus(insightInfoLines[status]);
			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Inverter Efficiency Below Spec".toLowerCase())) {

				tmpPlant.Inverter_Efficiency_Below_Spec_total_devices = getInsightTotalDevices(
						insightInfoLines[totalDevices]);
				tmpPlant.Inverter_Efficiency_Below_Spec_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Inverter_Efficiency_Below_Spec_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Inverter_Efficiency_Below_Spec_percentage = getInsightPercentage(
						insightInfoLines[insightPercent]);
				tmpPlant.Inverter_Efficiency_Below_Spec_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Voltage Deviation".toLowerCase())) {

				tmpPlant.Voltage_Deviation_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Voltage_Deviation_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Voltage_Deviation_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Voltage_Deviation_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Voltage_Deviation_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("MPPT".toLowerCase())) {

				tmpPlant.Mppt_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Mppt_faulty_devices = getInsightTotalFaultyDevices(insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Mppt_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Mppt_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Mppt_status = getInsightStatus(insightInfoLines[status]);

			}

			// need to check with Tariq why this has been added
			else if (insightInfoLines[name].trim().toLowerCase().contains("String Data Integrity".toLowerCase())) {

				tmpPlant.String_Data_integrity_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.String_Data_integrity_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.String_Data_integrity_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.String_Data_integrity_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.String_Data_integrity_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Inverter Relative Efficiency".toLowerCase())) {

				tmpPlant.Inverter_Relative_Efficiency_total_devices = getInsightTotalDevices(
						insightInfoLines[totalDevices]);
				tmpPlant.Inverter_Relative_Efficiency_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Inverter_Relative_Efficiency_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Inverter_Relative_Efficiency_percentage = getInsightPercentage(
						insightInfoLines[insightPercent]);
				tmpPlant.Inverter_Relative_Efficiency_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Inverter Downtime".toLowerCase())) {

				tmpPlant.Inverter_Downtime_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Inverter_Downtime_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Inverter_Downtime_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Inverter_Downtime_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Inverter_Downtime__status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Late Awakening".toLowerCase())) {

				tmpPlant.Late_Awakening_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Late_Awakening_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Late_Awakening_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Late_Awakening_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Late_Awakening_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Clipping".toLowerCase())) {

				tmpPlant.Clipping_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Clipping_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Clipping_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Clipping_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Clipping_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Temperature Alert".toLowerCase())) {

				tmpPlant.Temperature_Alert_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Temperature_Alert_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Temperature_Alert_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Temperature_Alert_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Temperature_Alert_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Frequency Deviation".toLowerCase())) {

				tmpPlant.Frequency_Deviation_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Frequency_Deviation_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Frequency_Deviation_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Frequency_Deviation_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Frequency_Deviation_status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Temperature Coefficient".toLowerCase())) {

				tmpPlant.Temperature_Coefficient_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Temperature_Coefficient_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Temperature_Coefficient_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Temperature_Coefficientpercentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Temperature_Coefficient__status = getInsightStatus(insightInfoLines[status]);

			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Disconnected Strings".toLowerCase())) {

				tmpPlant.Disconnected_Strings_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Disconnected_Strings_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Disconnected_Strings_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Disconnected_Strings_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Disconnected_Strings_status = getInsightStatus(insightInfoLines[status]);
			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Power Factor".toLowerCase())) {

				tmpPlant.Power_Factor_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Power_Factor_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Power_Factor_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Power_Factor_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Power_Factor_status = getInsightStatus(insightInfoLines[status]);
			}

			else if (insightInfoLines[name].trim().toLowerCase().contains("Inverter Data Integrity".toLowerCase())) {

				tmpPlant.Inverter_Data_integrity_total_devices = getInsightTotalDevices(insightInfoLines[totalDevices]);
				tmpPlant.Inverter_Data_integrity_faulty_devices = getInsightTotalFaultyDevices(
						insightInfoLines[numberOfNotHealtyDevices]);
				tmpPlant.Inverter_Data_integrity_value = getInsightValue(insightInfoLines[insightValue]);
				tmpPlant.Inverter_Data_integrity_percentage = getInsightPercentage(insightInfoLines[insightPercent]);
				tmpPlant.Inverter_Data_integrity_status = getInsightStatus(insightInfoLines[status]);
			} else {
				sassert().assertTrue(false, "<font color=red><b>NOT HANDELED INSIGHT</b></font>");
			}

		}

		catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		getCurrentFunctionName(false);

	}// fillInsigtsInfoIntoHeatMap

	public static void getPlantInsights(plant tmpPlant) throws Exception {
		getCurrentFunctionName(true);

		try {
			// get insights count / info
			List<WebElement> availbleInsights = HomePage.getAllInsights();
			// loop to iterate all insights and pick any insight is not healthy
			for (int insightIndex = 0; insightIndex < availbleInsights.size(); insightIndex++) {
				WebElement insight = availbleInsights.get(insightIndex);

				if (!insight.getText().toLowerCase().contains("Healthy".toLowerCase())) {
					String insightInfo = insight.getText();
					fillInsigtsIntoPlantObj(tmpPlant, insightInfo);
				}
			}
		}

		catch (Exception e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}// getPlantInsights

}
