package com.generic.util;

import java.text.MessageFormat;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;

public class dataProviderUtils {

	private static dataProviderUtils myObj;
	loggerUtils logs = new loggerUtils("DataProviderUtils");

	private dataProviderUtils() {
	}

	public static dataProviderUtils getInstance() {
		if (myObj == null) {
			myObj = new dataProviderUtils();
		}
		return myObj;
	}

	// get the data from xls file
	public Object[][] getData(String testName) throws Exception {
		return getData(testName, 2);
	}

	public Object[][] getData(String testName, int startingRow) throws Exception{
		/*
		 * if the sheet is regression then the sheet name should contains the
		 * "regression/setup" word and in col 2 should have the property runTest (empty not->
		 * run , full-> run) so this function can ignore the test cases before it's been
		 * send to parameterized class
		 */
		// starting row 1 to rows sheets
		logs.debug("Pulling data from sheet: "+ testName );
		XlsUtils dataTable = new XlsUtils(EnvironmentFiles.getDataSheetPath());
		int rows = dataTable.getRowCount(testName) - 1;
		// if empty sheet return empty data
		if (rows <= 0) {
			logs.debug("No data - empty sheet");
			Object[][] testData = new Object[1][0];
			return testData;
		}

		rows = dataTable.getRowCount(testName);
		int cols = dataTable.getColumnCount(testName);

		logs.debug(MessageFormat.format(LoggingMsg.TEST_NAME, testName) + " this sheet contains rows:"+rows+" and cols:"+cols);
		Object data[][] = new Object[rows - (startingRow - 1)][cols];// rows -1 since we dont have to include header

		int ignoredCases = 0;

		for (int rowNum = startingRow; rowNum <= rows; rowNum++) {
			if (testName.contains("Regression") || testName.contains("Setup") ) {
				if (dataTable.getCellData(testName, 1, 1).contains("runTest")
						&& !dataTable.getCellData(testName, 1, rowNum).equals("")) {
					for (int colNum = 0; colNum < cols; colNum++) {
						Thread.sleep(100);
						data[rowNum - startingRow][colNum] = dataTable.getCellData(testName, colNum,
								rowNum);
					}
				} else {
					for (int colNum = 0; colNum < cols; colNum++) {
						data[rowNum - startingRow][colNum] = "";
					}
					ignoredCases++;
				}
			} else {
				for (int colNum = 0; colNum < cols; colNum++) {
					data[rowNum - startingRow][colNum] = dataTable.getCellData(testName, colNum,
							rowNum);
				}
			}
		}
		logs.debug(MessageFormat.format(LoggingMsg.IGNORE_CASES, ignoredCases));

		// remove ignored cases
		Object dataFinal[][] = new Object[rows - (startingRow - 1) - ignoredCases][cols];
		int realRow = 0;
		for (int row = 0; row < data.length; row++) {
			if (!data[row][1].toString().equals("")) {
				for (int colNum = 0; colNum < cols; colNum++) {
					dataFinal[realRow][colNum] = data[row][colNum];
				}
				realRow++;
			}
		}

		if (dataFinal.length==0)
			throw new Exception("No Tests cases enabled in Data sheet");
		
		return dataFinal;
	}

}
