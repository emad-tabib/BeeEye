package com.generic.tests.runners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.loggerUtils;
import com.generic.util.TestUtilities;

public class BuildFullRegression extends SelTestCase {
	private static String[] runners = null;
	private static String[] browsers = null;
	private static ArrayList<String> regressionsPathes = new ArrayList<String>();
	private static int testCaseID;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.RunnersRegressionSheet;
	private static XmlTest testObject;

	private static ThreadLocal<loggerUtils> Testlogs = new ThreadLocal<loggerUtils>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		try {
			Testlogs.set(new loggerUtils(test.getName() + test.getIndex()));
			testObject = test;
			
			try {
			runners = System.getenv("Runners").split(",");
			}
			catch(Exception e)
			{
				logs.debug("The evn var is not set for runners ... pulling data from sheet");
				runners=Common.readRunners();
			}
			
			if (runners[0].equals("all") )
				runners = "addressBookRegression,BuildFullRegression,cartRegression_IE11,cartRegression,checkoutRegression,FavoriteRegression_IE11,FavoriteRegression,FinalRegression,FullRegression,HPRegression,LoginRegression_IE,LoginRegression,MiniCartRegression,OrderHistoryRegression_IE,OrderHistoryRegression,PaymentDetailsRegression,PDPRegression_IE11,PDPRegression,PLPRegression_IE,PLPRegression,RegistrationRegression_IE,RegistrationRegression,restPasswordRegression_IE,restPasswordRegression".split(",");
			
			Testlogs.get().debug("Started in Regression");
			
			try
			{
				browsers = System.getenv("Browsers").split(",");
			}catch(Exception e)
			{
				logs.debug("The evn var is not set for browsers ... pulling data from sheet");
				browsers =Common.readBrowsers();
			}
			
			for (String regressionName : runners) {
				logs.debug("Adding runner path to paths: " + regressionName);
				regressionsPathes.add(TestUtilities.searchSpecificFile(
						new File(System.getProperty("user.dir") + "\\src\\com\\generic\\test_runners"),
						regressionName));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Test
	public void generateFinalRegressionXML() {
		try {
			TestUtilities.writeFinalXMLRegression(regressionsPathes, browsers);
			Common.testPass();
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}

}
