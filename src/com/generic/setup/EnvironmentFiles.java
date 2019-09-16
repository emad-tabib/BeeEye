package com.generic.setup;

import java.text.MessageFormat;

import org.testng.util.Strings;

public class EnvironmentFiles {
	//config.properties file path,getter and setter
	public static String configFilePath = System.getProperty("user.dir")+"//src//com//generic//config//config.properties";
	//DataSheet.xlsx file path,getter and setter
	public static String dataSheetPath = System.getProperty("user.dir")+"//src//com//generic//config//DataSheet.xlsx";
	//log file path,getter and setter
	public static String logFilePath = null;
	public static String logReportPath = null;
	
	public static String DatabasePath = null;
	
	//log file name,getter and setter
	public static String logFileName = "Application.log";
	//reports folder directory path,getter and setter
	public static String reportsFolderPath = null;
	//Failed Test Case Template HTML File
	public static String failedTestCaseReportTemplate = System.getProperty("user.dir")+"//src//com//generic//reports//reportsTemplates//failedTCTemplate.html";
	//Index Test Cases Template HTML File
	public static String executinResultsIndexFileTemplate = System.getProperty("user.dir")+"//src//com//generic//reports//reportsTemplates//indexTemplate.html";
	public static String visualTestingAssetsPath = System.getProperty("user.dir")+"//VisualTestingAssets";
	
	public static String templateIconPath = System.getProperty("user.dir")+"//src//com//generic//reports//reportsTemplates";
	
	public static String getConfigFilePath() {
		return configFilePath;
	}

	public static void setConfigFilePath(String configFilePath) {
		EnvironmentFiles.configFilePath = configFilePath;
	}

	public static String getDataSheetPath() {
		return dataSheetPath;
	}

	public static void setDataSheetPath(String dataSheetPath) {
		EnvironmentFiles.dataSheetPath = dataSheetPath;
	}
	
	public static String getLogFilePath() {
		if(Strings.isNullOrEmpty(logFilePath)) {
			SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.NULL_FILE_PATH_ERROR_MSG, "Log"));
			return System.getProperty("user.dir") + "/" + SelTestCase.getCONFIG().getProperty("logs");
		} else {
			return logFilePath;
		}
	}
	
	public static String getLogReportPath() {
		if(Strings.isNullOrEmpty(logReportPath)) {
			SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.NULL_FILE_PATH_ERROR_MSG, "reportLogs"));
			return System.getProperty("user.dir") + "/" + SelTestCase.getCONFIG().getProperty("logs")+ "/" + "reportLogs";
		} else {
			return logReportPath;
		}
	}
	
	public static String getDatabasePath() {
		if(Strings.isNullOrEmpty(DatabasePath)) {
			SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.NULL_FILE_PATH_ERROR_MSG, "reportLogs"));
			return System.getProperty("user.dir") + "/" + SelTestCase.getCONFIG().getProperty("database");
		} else {
			return DatabasePath;
		}
	}

	public static void setLogFilePath(String logFilePath) {
		EnvironmentFiles.logFilePath = logFilePath;
	}

	public static String getLogFileName() {
		return logFileName;
	}

	public static String getVisualTestingAssetsPath() {
		return visualTestingAssetsPath;
	}
	
	public static void setLogFileName(String logFileName) {
		if (logFileName.endsWith(".log")) {
		EnvironmentFiles.logFileName = logFileName;
		} else {
			EnvironmentFiles.logFileName = logFileName + ".log";
		}
		System.setProperty("logfilename", "logs/" + EnvironmentFiles.getLogFileName());
		System.out.println("The Value of system file is " + System.getProperty("logfilename"));
	}
	
	public static String getReportsFolderPath() {
		if(Strings.isNullOrEmpty(reportsFolderPath)) {
			SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.NULL_FILE_PATH_ERROR_MSG, "Reports"));
			String reportDir = System.getProperty("user.dir") + "/" + SelTestCase.getCONFIG().getProperty("reportFolderName");
			SelTestCase.logs.debug("the report path is : " + reportDir);
			return reportDir;
		} else {
			return reportsFolderPath;
		}
	}

	public static void setReportsFolderPath(String reportsFolderPath) {
		EnvironmentFiles.reportsFolderPath = reportsFolderPath;
	}
	
	public static String getFailedTestCaseReportTemplate() {
		return failedTestCaseReportTemplate;
	}

	public static void setFailedTestCaseReportTemplate(String failedTestCaseReportTemplate) {
		EnvironmentFiles.failedTestCaseReportTemplate = failedTestCaseReportTemplate;
	}

	/**
	 * @return the executinResultsIndexFileTemplate
	 */
	public static String getExecutinResultsIndexFileTemplate() {
		return executinResultsIndexFileTemplate;
	}

	/**
	 * @param executinResultsIndexFileTemplate
	 */
	public static void setExecutinResultsIndexFileTemplate(String executinResultsIndexFileTemplate) {
		EnvironmentFiles.executinResultsIndexFileTemplate = executinResultsIndexFileTemplate;
	}

	public static String getTemplateDir() {
		return templateIconPath ; 
	}
	
}
