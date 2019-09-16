package com.generic.tests.DailyReport;

import java.text.MessageFormat;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;
import com.generic.util.loggerUtils;

public class clearSheet extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.loginSheet;
	private static ThreadLocal<loggerUtils> Testlogs = new ThreadLocal<loggerUtils>();
	private static XmlTest testObject;

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new loggerUtils(test.getName() + test.getIndex()));
		testObject = test;
	}


	@Test
	public void ClearSheet() {

		Testlogs.set(new loggerUtils("DailyReport " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName(SheetVariables.DailyReportTestCaseId);
		logCaseDetailds("clearing sheet");
		try {
			// clean the datasheet from last data
			sassert().assertTrue(TestUtilities.cleanReportsheet(), "Clearing the Sheet FAILED");

			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" );
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test

}
