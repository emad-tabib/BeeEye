package com.generic.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.generic.page.plant;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.sql.SqlStatements;

public class sqLiteUtils extends SelTestCase{
	@Test()
	public static void builder() {
		//setTestCaseReportName(SheetVariables.DailyReportTestCaseId);
		 //createNewDatabase(DatabaseName);
		 //createNewTable(TableName, DatabaseName);
		plant test = selectDataForTheDayBefore("demo","Italy Milan",TableName, DatabaseName);
		plant.printPlant(test);
		logs.debug("done");
	}

	// Create a new database if it doesn't exist, this methods requires the name of
	// the database
	public static void createNewDatabase(String DatabaseName) {
		getCurrentFunctionName(true);
		// Path to the database file
		String url = "jdbc:sqlite:"+EnvironmentFiles.getDatabasePath()+"/" + DatabaseName;
		
		// Make a connection to the specified path, if not exist make a new DB
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				logs.debug("The driver name is " + meta.getDriverName());
				logs.debug("A new database has been created.");
			}
			
			closeConnection(conn);

		} catch (SQLException e) {
			logs.debug(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
		getCurrentFunctionName(false);
	}//create NewDatabase

	public static void closeConnection(Connection conn) {
		getCurrentFunctionName(true);
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logs.debug("connection closed");
				Assert.assertTrue(false, e.getMessage());
			}
		}
		getCurrentFunctionName(false);
	}//close connection 

	// Creates a new table in a specified database, this method takes the query and
	// the name of the database
	public static void createNewTable(String TableName, String DatabaseName) {
		getCurrentFunctionName(true);
		String url = "jdbc:sqlite:"+EnvironmentFiles.getDatabasePath()+"/" + DatabaseName;
		String querey = SqlStatements.CreateTableStatement.replace("TableName", TableName);
		
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(querey);
			closeConnection(conn);
		} catch (SQLException e) {
			logs.debug("Table is not created" + e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
		getCurrentFunctionName(false);
	}//createNewTable

	// Inserts data into a specific table, this method requires the data and the
	// name of the database
	public static void insertData(plant plantInfo, String TableName, String DatabaseName) {
		getCurrentFunctionName(true);
		String url = "jdbc:sqlite:" + EnvironmentFiles.getDatabasePath() + "/" + DatabaseName;

		try (Connection conn = DriverManager.getConnection(url)) {
			// Best way to pass the data to the database by passing it as injections
			String Statement = SqlStatements.insertStetment.replace("TableName", TableName)
					+ " VALUES("+
					"'"+plantInfo.user +"',"+
					"'"+plantInfo.plant +"',"+
					plantInfo.PEI_value +","+
					plantInfo.PEI_percentage +","+
					plantInfo.PERF_value +","+
					plantInfo.PERF_percentage +","+
					plantInfo.Avilability_value +","+
					plantInfo.Avilability_percentage +","+
					plantInfo.Inverters_Performance_Percent +","+
					plantInfo.Strings_Performance_Percent +","+
					plantInfo.inverters___Performance +","+
					plantInfo.inverters_Mppt +","+
					plantInfo.inverters_relative_efficiency +","+
					plantInfo.inverters_efficiency_below_spec +","+
					plantInfo.inverters___availability +","+
					plantInfo.inverters___downtime +","+
					plantInfo.strings___performance +","+
					plantInfo.strings___panel_degradation +","+
					plantInfo.strings___disconnected_strings +","+
					plantInfo.strings___availability +","+
					plantInfo.Health_PRpercentage +","+
					plantInfo.Health_PR_STCpercentage +","+
					plantInfo.Health_Data_integrity_percentage +","+
					plantInfo.Health_plantAvilability_percentage +","+
					plantInfo.Health_Power_Factor_percentage +","+
					plantInfo.Healty_Inverters +","+
					plantInfo.Healty_Strings +","+
					plantInfo.OK_Inverters +","+
					plantInfo.OK_Strings +","+
					plantInfo.Faulty_Inverters +","+
					plantInfo.Faulty_Strings +","+
					plantInfo.Messing_data_inverters +","+
					plantInfo.Messing_data_Strings +","+
					plantInfo.Disconnected_Strings_total_devices +","+
					plantInfo.Disconnected_Strings_faulty_devices +","+
					plantInfo.Disconnected_Strings_value +","+
					plantInfo.Disconnected_Strings_percentage +","+
					"'"+plantInfo.Disconnected_Strings_status +"'"+","+
					plantInfo.Panel_Degradation_total_devices +","+
					plantInfo.Panel_Degradation_faulty_devices +","+
					plantInfo.Panel_Degradation_value +","+
					plantInfo.Panel_Degradation_percentage +","+
					"'"+plantInfo.Panel_Degradation_status +"'"+","+
					plantInfo.String_Data_integrity_total_devices +","+
					plantInfo.String_Data_integrity_faulty_devices +","+
					plantInfo.String_Data_integrity_value +","+
					plantInfo.String_Data_integrity_percentage +","+
					"'"+plantInfo.String_Data_integrity_status +"'"+","+
					plantInfo.Temperature_Coefficient_total_devices +","+
					plantInfo.Temperature_Coefficient_faulty_devices +","+
					plantInfo.Temperature_Coefficient_value +","+
					plantInfo.Temperature_Coefficientpercentage +","+
					"'"+plantInfo.Temperature_Coefficient__status +"'"+","+
					plantInfo.Inverter_Downtime_total_devices +","+
					plantInfo.Inverter_Downtime_faulty_devices +","+
					plantInfo.Inverter_Downtime_value +","+
					plantInfo.Inverter_Downtime_percentage +","+
					"'"+plantInfo.Inverter_Downtime__status +"'"+","+
					plantInfo.Inverter_Data_integrity_total_devices +","+
					plantInfo.Inverter_Data_integrity_faulty_devices +","+
					plantInfo.Inverter_Data_integrity_value +","+
					plantInfo.Inverter_Data_integrity_percentage +","+
					"'"+plantInfo.Inverter_Data_integrity_status +"'"+","+
					plantInfo.Inverter_Efficiency_Below_Spec_total_devices +","+
					plantInfo.Inverter_Efficiency_Below_Spec_faulty_devices +","+
					plantInfo.Inverter_Efficiency_Below_Spec_value +","+
					plantInfo.Inverter_Efficiency_Below_Spec_percentage +","+
					"'"+plantInfo.Inverter_Efficiency_Below_Spec_status +"'"+","+
					plantInfo.Late_Awakening_total_devices +","+
					plantInfo.Late_Awakening_faulty_devices +","+
					plantInfo.Late_Awakening_value +","+
					plantInfo.Late_Awakening_percentage +","+
					"'"+plantInfo.Late_Awakening_status +"'"+","+
					plantInfo.Mppt_total_devices +","+
					plantInfo.Mppt_faulty_devices +","+
					plantInfo.Mppt_value +","+
					plantInfo.Mppt_percentage +","+
					"'"+plantInfo.Mppt_status +"'"+","+
					plantInfo.Inverter_Relative_Efficiency_total_devices +","+
					plantInfo.Inverter_Relative_Efficiency_faulty_devices +","+
					plantInfo.Inverter_Relative_Efficiency_value +","+
					plantInfo.Inverter_Relative_Efficiency_percentage +","+
					"'"+plantInfo.Inverter_Relative_Efficiency_status +"'"+","+
					plantInfo.Clipping_total_devices +","+
					plantInfo.Clipping_faulty_devices +","+
					plantInfo.Clipping_value +","+
					plantInfo.Clipping_percentage +","+
					"'"+plantInfo.Clipping_status +"'"+","+
					plantInfo.Power_Factor_total_devices +","+
					plantInfo.Power_Factor_faulty_devices +","+
					plantInfo.Power_Factor_value +","+
					plantInfo.Power_Factor_percentage +","+
					"'"+plantInfo.Power_Factor_status +"'"+","+
					plantInfo.Temperature_Alert_total_devices +","+
					plantInfo.Temperature_Alert_faulty_devices +","+
					plantInfo.Temperature_Alert_value +","+
					plantInfo.Temperature_Alert_percentage +","+
					"'"+plantInfo.Temperature_Alert_status +"'"+","+
					plantInfo.Frequency_Deviation_total_devices +","+
					plantInfo.Frequency_Deviation_faulty_devices +","+
					plantInfo.Frequency_Deviation_value +","+
					plantInfo.Frequency_Deviation_percentage +","+
					"'"+plantInfo.Frequency_Deviation_status +"'"+","+
					plantInfo.Voltage_Deviation_total_devices +","+
					plantInfo.Voltage_Deviation_faulty_devices +","+
					plantInfo.Voltage_Deviation_value +","+
					plantInfo.Voltage_Deviation_percentage +","+
					"'"+plantInfo.Voltage_Deviation_status+"'"+")";
					
			logs.debug(Statement);
			
			PreparedStatement ps = conn.prepareStatement(Statement);
			
			// if it returns less than 0, no rows were inserted
			if (ps.executeUpdate() > 0)
				logs.debug("Inserted sucessfuly");
			else
				logs.debug("Failed to insert");
			closeConnection(conn);
		} catch (SQLException e) {
			logs.debug(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
		getCurrentFunctionName(false);
	}//insrtData

	public static plant selectDataForTheDayBefore(String user, String plant, String TableName, String DatabaseName) {
		return selectDataForTheDayBefore(user, plant, TableName, DatabaseName, 1);
	}
	
	// This method selects a specific record of data in a specific table, based on the date 
	private static plant selectDataForTheDayBefore(String user, String plant, String TableName, String DatabaseName, int negDays) {
		getCurrentFunctionName(true);
		String url = "jdbc:sqlite:"+EnvironmentFiles.getDatabasePath()+"/" + DatabaseName;
		plant results = null; 
		
		try (Connection conn = DriverManager.getConnection(url);) {
			logs.debug(SqlStatements.SelectPreviousDate.replace("?1", user)
					.replace("?2", plant).replace("?3", LocalDate.now().minusDays(negDays) + ""));
			PreparedStatement ps = conn.prepareStatement(SqlStatements.SelectPreviousDate.replace("?1", user)
					.replace("?2", plant).replace("?3", LocalDate.now().minusDays(negDays) + ""));

			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next())
			{
				logs.debug("parsing results");
				results = parsePlantresult(rs);
			}
			else {
				logs.debug("No data on date: " + LocalDate.now().minusDays(negDays) +" : "+ LocalDate.now().minusDays(negDays).getDayOfWeek());
				if(negDays < 30)
				{
					results = selectDataForTheDayBefore(user, plant, TableName, DatabaseName, negDays+1);
				}else
				{
					sassert().assertTrue(false, "Either the plant"+plant+"-"+user+" has no huristical data or it is too old and more than 30 days");
				}
			}
			closeConnection(conn);

		} catch (SQLException e) {
			logs.debug(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
		getCurrentFunctionName(false);
		return results;
	}//selectData
	

	public static plant parsePlantresult(ResultSet rs) throws SQLException {
		getCurrentFunctionName(true);
		
		plant results = new plant();
		results.user = rs.getString("user");
		results.plant = rs.getString("plant");
		results.PEI_value = rs.getDouble("PEI_value");
		results.PEI_percentage = rs.getDouble("PEI_percentage");
		results.PERF_value = rs.getDouble("PERF_value");
		results.PERF_percentage = rs.getDouble("PERF_percentage");
		results.Avilability_value = rs.getDouble("Avilability_value");
		results.Avilability_percentage = rs.getDouble("Avilability_percentage");
		results.Inverters_Performance_Percent = rs.getDouble("Inverters_Performance_Percent");
		results.Strings_Performance_Percent = rs.getDouble("Strings_Performance_Percent");
		results.inverters___Performance = rs.getDouble("inverters___Performance");
		results.inverters_Mppt = rs.getDouble("inverters_Mppt");
		results.inverters_relative_efficiency = rs.getDouble("inverters_relative_efficiency");
		results.inverters_efficiency_below_spec = rs.getDouble("inverters_efficiency_below_spec");
		results.inverters___availability = rs.getDouble("inverters___availability");
		results.inverters___downtime = rs.getDouble("inverters___downtime");
		results.strings___performance = rs.getDouble("strings___performance");
		results.strings___panel_degradation = rs.getDouble("strings___panel_degradation");
		results.strings___disconnected_strings = rs.getDouble("strings___disconnected_strings");
		results.strings___availability = rs.getDouble("strings___availability");
		results.Health_PRpercentage = rs.getDouble("Health_PRpercentage");
		results.Health_PR_STCpercentage = rs.getDouble("Health_PR_STCpercentage");
		results.Health_Data_integrity_percentage = rs.getDouble("Health_Data_integrity_percentage");
		results.Health_plantAvilability_percentage = rs.getDouble("Health_plantAvilability_percentage");
		results.Health_Power_Factor_percentage = rs.getDouble("Health_Power_Factor_percentage");
		results.Healty_Inverters = rs.getDouble("Healty_Inverters");
		results.Healty_Strings = rs.getDouble("Healty_Strings");
		results.OK_Inverters = rs.getDouble("OK_Inverters");
		results.OK_Strings = rs.getDouble("OK_Strings");
		results.Faulty_Inverters = rs.getDouble("Faulty_Inverters");
		results.Faulty_Strings = rs.getDouble("Faulty_Strings");
		results.Messing_data_inverters = rs.getDouble("Messing_data_inverters");
		results.Messing_data_Strings = rs.getDouble("Messing_data_Strings");
		results.Disconnected_Strings_total_devices = rs.getDouble("Disconnected_Strings_total_devices");
		results.Disconnected_Strings_faulty_devices = rs.getDouble("Disconnected_Strings_faulty_devices");
		results.Disconnected_Strings_value = rs.getDouble("Disconnected_Strings_value");
		results.Disconnected_Strings_percentage = rs.getDouble("Disconnected_Strings_percentage");
		results.Disconnected_Strings_status = rs.getString("Disconnected_Strings_status");
		results.Panel_Degradation_total_devices = rs.getDouble("Panel_Degradation_total_devices");
		results.Panel_Degradation_faulty_devices = rs.getDouble("Panel_Degradation_faulty_devices");
		results.Panel_Degradation_value = rs.getDouble("Panel_Degradation_value");
		results.Panel_Degradation_percentage = rs.getDouble("Panel_Degradation_percentage");
		results.Panel_Degradation_status = rs.getString("Panel_Degradation_status");
		results.String_Data_integrity_total_devices = rs.getDouble("String_Data_integrity_total_devices");
		results.String_Data_integrity_faulty_devices = rs.getDouble("String_Data_integrity_faulty_devices");
		results.String_Data_integrity_value = rs.getDouble("String_Data_integrity_value");
		results.String_Data_integrity_percentage = rs.getDouble("String_Data_integrity_percentage");
		results.String_Data_integrity_status = rs.getString("String_Data_integrity_status");
		results.Temperature_Coefficient_total_devices = rs.getDouble("Temperature_Coefficient_total_devices");
		results.Temperature_Coefficient_faulty_devices = rs.getDouble("Temperature_Coefficient_faulty_devices");
		results.Temperature_Coefficient_value = rs.getDouble("Temperature_Coefficient_value");
		results.Temperature_Coefficientpercentage = rs.getDouble("Temperature_Coefficientpercentage");
		results.Temperature_Coefficient__status = rs.getString("Temperature_Coefficient__status");
		results.Inverter_Downtime_total_devices = rs.getDouble("Inverter_Downtime_total_devices");
		results.Inverter_Downtime_faulty_devices = rs.getDouble("Inverter_Downtime_faulty_devices");
		results.Inverter_Downtime_value = rs.getDouble("Inverter_Downtime_value");
		results.Inverter_Downtime_percentage = rs.getDouble("Inverter_Downtime_percentage");
		results.Inverter_Downtime__status = rs.getString("Inverter_Downtime__status");
		results.Inverter_Data_integrity_total_devices = rs.getDouble("Inverter_Data_integrity_total_devices");
		results.Inverter_Data_integrity_faulty_devices = rs.getDouble("Inverter_Data_integrity_faulty_devices");
		results.Inverter_Data_integrity_value = rs.getDouble("Inverter_Data_integrity_value");
		results.Inverter_Data_integrity_percentage = rs.getDouble("Inverter_Data_integrity_percentage");
		results.Inverter_Data_integrity_status = rs.getString("Inverter_Data_integrity_status");
		results.Inverter_Efficiency_Below_Spec_total_devices = rs.getDouble("Inverter_Efficiency_Below_Spec_total_devices");
		results.Inverter_Efficiency_Below_Spec_faulty_devices = rs.getDouble("Inverter_Efficiency_Below_Spec_faulty_devices");
		results.Inverter_Efficiency_Below_Spec_value = rs.getDouble("Inverter_Efficiency_Below_Spec_value");
		results.Inverter_Efficiency_Below_Spec_percentage = rs.getDouble("Inverter_Efficiency_Below_Spec_percentage");
		results.Inverter_Efficiency_Below_Spec_status = rs.getString("Inverter_Efficiency_Below_Spec_status");
		results.Late_Awakening_total_devices = rs.getDouble("Late_Awakening_total_devices");
		results.Late_Awakening_faulty_devices = rs.getDouble("Late_Awakening_faulty_devices");
		results.Late_Awakening_value = rs.getDouble("Late_Awakening_value");
		results.Late_Awakening_percentage = rs.getDouble("Late_Awakening_percentage");
		results.Late_Awakening_status = rs.getString("Late_Awakening_status");
		results.Mppt_total_devices = rs.getDouble("Mppt_total_devices");
		results.Mppt_faulty_devices = rs.getDouble("Mppt_faulty_devices");
		results.Mppt_value = rs.getDouble("Mppt_value");
		results.Mppt_percentage = rs.getDouble("Mppt_percentage");
		results.Mppt_status = rs.getString("Mppt_status");
		results.Inverter_Relative_Efficiency_total_devices = rs.getDouble("Inverter_Relative_Efficiency_total_devices");
		results.Inverter_Relative_Efficiency_faulty_devices = rs.getDouble("Inverter_Relative_Efficiency_faulty_devices");
		results.Inverter_Relative_Efficiency_value = rs.getDouble("Inverter_Relative_Efficiency_value");
		results.Inverter_Relative_Efficiency_percentage = rs.getDouble("Inverter_Relative_Efficiency_percentage");
		results.Inverter_Relative_Efficiency_status = rs.getString("Inverter_Relative_Efficiency_status");
		results.Clipping_total_devices = rs.getDouble("Clipping_total_devices");
		results.Clipping_faulty_devices = rs.getDouble("Clipping_faulty_devices");
		results.Clipping_value = rs.getDouble("Clipping_value");
		results.Clipping_percentage = rs.getDouble("Clipping_percentage");
		results.Clipping_status = rs.getString("Clipping_status");
		results.Power_Factor_total_devices = rs.getDouble("Power_Factor_total_devices");
		results.Power_Factor_faulty_devices = rs.getDouble("Power_Factor_faulty_devices");
		results.Power_Factor_value = rs.getDouble("Power_Factor_value");
		results.Power_Factor_percentage = rs.getDouble("Power_Factor_percentage");
		results.Power_Factor_status = rs.getString("Power_Factor_status");
		results.Temperature_Alert_total_devices = rs.getDouble("Temperature_Alert_total_devices");
		results.Temperature_Alert_faulty_devices = rs.getDouble("Temperature_Alert_faulty_devices");
		results.Temperature_Alert_value = rs.getDouble("Temperature_Alert_value");
		results.Temperature_Alert_percentage = rs.getDouble("Temperature_Alert_percentage");
		results.Temperature_Alert_status = rs.getString("Temperature_Alert_status");
		results.Frequency_Deviation_total_devices = rs.getDouble("Frequency_Deviation_total_devices");
		results.Frequency_Deviation_faulty_devices = rs.getDouble("Frequency_Deviation_faulty_devices");
		results.Frequency_Deviation_value = rs.getDouble("Frequency_Deviation_value");
		results.Frequency_Deviation_percentage = rs.getDouble("Frequency_Deviation_percentage");
		results.Frequency_Deviation_status = rs.getString("Frequency_Deviation_status");
		results.Voltage_Deviation_total_devices = rs.getDouble("Voltage_Deviation_total_devices");
		results.Voltage_Deviation_faulty_devices = rs.getDouble("Voltage_Deviation_faulty_devices");
		results.Voltage_Deviation_value = rs.getDouble("Voltage_Deviation_value");
		results.Voltage_Deviation_percentage = rs.getDouble("Voltage_Deviation_percentage");
		results.Voltage_Deviation_status = rs.getString("Voltage_Deviation_status");
		
		getCurrentFunctionName(false);
		
		return results;
	}//parsePlantResults

}