package com.generic.setup;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.generic.setup.GlobalVariables.browsers;
import com.generic.util.ReportUtil;
import com.generic.util.dataProviderUtils;

import io.appium.java_client.ios.IOSDriver;

public class Common extends SelTestCase {

	public static String expected = null;
	public static String actual = null;

	public static class DataSheetConstants {
	}

	@SuppressWarnings("deprecation")
	public static WebDriver initializeBrowser(String browser) throws Exception {
		getCurrentFunctionName(true);
		try {
			logs.debug(MessageFormat.format(LoggingMsg.BROWSER_NAME,browser));
			DesiredCapabilities capabilities = new DesiredCapabilities();
			WebDriver driver;
			if (browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", PagesURLs.getDriversPath(browser));
				return new EdgeDriver(new EdgeOptions());
				
			}else if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",PagesURLs.getDriversPath(browser));
				
				FirefoxOptions fo = new FirefoxOptions();
				driver = new FirefoxDriver(fo);
				driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				return driver;
								
			}else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", PagesURLs.getDriversPath(browser));
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

				return new InternetExplorerDriver(capabilities);

			} else if (browser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", PagesURLs.getDriversPath(browser));
				ChromeOptions co = new ChromeOptions();
				driver = new ChromeDriver(co);
				driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				return driver;

			} else if (browser.equalsIgnoreCase("safari_grid")) {
				SafariOptions options = new SafariOptions();
				// options.setUseCleanSession(true);
				capabilities = DesiredCapabilities.safari();
				capabilities.setCapability(SafariOptions.CAPABILITY, options);
				// capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				// capabilities.setPlatform(Platform.MAC);
				// capabilities.setCapability("ensureCleanSession", true);
				// TODO: change it and setup grid server
				SelTestCase.setDriver(new RemoteWebDriver(new URL("http://10.20.20.54:4444/wd/hub"), capabilities));
				
			} 
			 else if (browser.equalsIgnoreCase("ChromeG")) {
				DesiredCapabilities des=DesiredCapabilities.chrome();
//				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//				des.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
//				des.setPlatform(Platform.WIN10);
				System.setProperty("webdriver.chrome.driver", PagesURLs.getDriversPath(browser));
				//des.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				RemoteWebDriver rdriver ;
				
				//rdriver = new RemoteWebDriver(new URL("http://cv-autogrid04.crossview.inc:4444/wd/hub"),des);
				rdriver = new RemoteWebDriver(new URL("http://10.200.254.33:4444/wd/hub"),des);
				rdriver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
				rdriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				return rdriver;
					
				}else if (browser.contains("mobile")) {
				  /*
				   * https://cs.chromium.org/chromium/src/chrome/test/chromedriver/chrome/mobile_device_list.cc
				   	  iPad
					  Nexus 6
					  Nexus 5
					  Galaxy Note 3
					  Nexus 6P
					  iPhone 8 Plus
					  iPhone 7 Plus
					  Nexus 7
					  iPhone 7
					  Nexus 10
					  iPhone 8
					  iPhone 6
					  Nexus 5X
					  Galaxy Note II
					  iPhone 6 Plus
					  iPhone X
					  Galaxy S5
				   */
				  
				String mobile = browser.split("_")[1];
				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("platform", "WINDOWS");
				capabilities.setBrowserName("chrome");

				Map<String, String> mobileEmulation = new HashMap<String, String>();
				mobileEmulation.put("deviceName", mobile);
				Map<String, Object> chromeOptions = new HashMap<String, Object>();
				chromeOptions.put("mobileEmulation", mobileEmulation);

				ChromeOptions options = new ChromeOptions();

				if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
					options.addArguments("user-data-dir=" + System.getProperty("user.home")
							+ "/AppData/Local/Google/Chrome SxS/User Data/");
					options.addArguments("detach=true");
				}

				options.setExperimentalOption("mobileEmulation", mobileEmulation);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				System.setProperty("webdriver.chrome.driver", PagesURLs.getDriversPath("chrome"));
				return new ChromeDriver(capabilities);

			} else if (browser.equalsIgnoreCase("IOS_grid")) {
				capabilities = DesiredCapabilities.iphone();
				capabilities.setCapability("browserName", "Safari");
				capabilities.setCapability("deviceName", "iPhone");
				capabilities.setCapability("udid", "021319a68bdf5c78436027f183c4ec14b54df13e");// from xcode
				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("automationName", "XCUITest");
				capabilities.setCapability("startIWDP", true);
				capabilities.setCapability("webkitResponseTimeout", 15000);
				capabilities.setCapability("nativeWebTap", true);
				capabilities.setCapability("autoWebview", true);
				capabilities.setCapability("platformVersion", "11.4"); // from xcode
				capabilities.setCapability("platformName", "iOS");

				return new IOSDriver(new URL("http://172.28.8.69:4723/wd/hub"), capabilities);

			}else {
				logs.debug(LoggingMsg.INVALID_BROWSER_ERROR_MSG);
				throw new Exception(LoggingMsg.INVALID_BROWSER_ERROR_MSG);
			}

		} catch (Throwable t) {
			t.printStackTrace();
			SelTestCase.setTestStatus("Fail: " + t.getMessage());
			SelTestCase.setStartTime(ReportUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));
			ReportUtil.addError(SelTestCase.getTestStatus(), null);
			throw new Exception(t);
		}
		getCurrentFunctionName(false);
		return null;
	}

	/**
	 * Reads URL from config.properties file
	 * 
	 * @throws Exception
	 *
	 *
	 */
	public static void launchApplication(String Browser) throws Exception {
		getCurrentFunctionName(true);

		logs.debug(MessageFormat.format(LoggingMsg.TEST_ENVIRONMENT_NAME,
				SelTestCase.getCONFIG().getProperty("testEnvironment")));

		if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
			// TODO: please enable it later with correct url in case Chrome Cached
			logs.debug(LoggingMsg.ENABLE_BLOCK_FOR_FUTURE);
			logs.debug("signing out from all users");
			logs.debug(PagesURLs.getSignOutPage());
			getDriver().get(PagesURLs.getSignOutPage());
		}

		getDriver().get(getCONFIG().getProperty("testEnvironment"));
		if (!Browser.contains(browsers.iOS))
			getDriver().manage().window().maximize();

		if (Browser.contains("IE") || Browser.contains("edge")) {
			Thread.sleep(2000);
			getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
		}
		getCurrentFunctionName(false);
	}

	/**
	 * Explicit wait
	 *
	 *
	 * @param waitTime
	 */
	public static void wait(int waitTime) {

		try {
			logs.debug(MessageFormat.format(LoggingMsg.WAIT_FOR_TIME, waitTime));
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set test case status that will appear in the Automation Report
	 *
	 */
	public static void testPass() {
		logs.debug("Test Status: PASS");
		setTestStatus("Pass");

	}

	public static void testIgnored() {
		logs.debug("Test Status: Ignored");
		setTestStatus("Ignore");

	}

	/**
	 * Set test case status that will appear in the Automation Report
	 *
	 *
	 */
	public static void testFail(Throwable t, String screenShotName) {
		logs.debug("Test Status: Failed");
		setTestStatus("Fail: " + t.getMessage());
		setScreenShotName(screenShotName + "_" + counter + ".jpg");
		ReportUtil.addError(getTestStatus(), getScreenShotName());
		logs.debug(MessageFormat.format(LoggingMsg.CURRENT_URL, SelTestCase.getDriver().getCurrentUrl()));
	}

	public static void testFailTemp(List<Exception> t, String screenShotName) {
		String temp = "";
		for (int i = 0; i < t.size(); i++) {

			temp = temp + "Error " + i + 1 + "----" + t.get(i).getMessage();
		}
		setTestStatus("Fail: " + temp);
		setScreenShotName(screenShotName + ".jpg");
		ReportUtil.addError(getTestStatus(), getScreenShotName());
	}

	/**
	 * Closes the opened browsers by selenium.
	 *
	 */
	public static void closeApplication() {
		if (getCONFIG().getProperty("debug").equalsIgnoreCase("no")) {
			ActionDriver.closeBrowser();
		}
	}

	public static void refreshBrowser() {

		ActionDriver.refreshBrowser();
	}


	public static void killDriverServerIfRunning() throws Exception {
		String line;
		String pidInfo = "";
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
			pidInfo += line;
		}
		input.close();
		if (getCONFIG().getProperty("browser").equalsIgnoreCase("chrome")) {
			if (pidInfo.contains("chromedriver.exe")) {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				logs.debug(MessageFormat.format(LoggingMsg.KILLING_PROCESS, "chromeDriver.exe"));
			} else {
				logs.debug(
						MessageFormat.format(LoggingMsg.NOT_RUNNING_PROCESS_ERROR_MSG, "chromeDriver.exe", "chrome"));
			}
		}

	}

	public static void captureScreen(String screenShotName) {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			Rectangle screenRect = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRect);
			ImageIO.write(image, "png", new File(screenShotName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LinkedHashMap<String, Object> readTestparams(String testSheet, int caseIndex) throws Exception {
		/*
		 * [
			  {
			    desc=loggedinuserwithsavedmaistropaymentandshippingaddress,
			    proprties=loggedin,
			    products=P2,
			    shippingMethod=STANDARDDELIVERY,
			    payment=maistro,
			    shippingAddress=A1,
			    billingAddress=A2,
			    coupon=,
			    email=ibatta@dbi.com,
			    orderId=,
			    orderTotal=,
			    orderSubtotal=,
			    orderTax=,
			    orderShipping=
			  }
			]
		 */
		LinkedHashMap<String, Object> tests = new LinkedHashMap<>();
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testSheet, 1);

		// data map
		int header = 0;
		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, String> params = new LinkedHashMap<>();
			for (int col = 1; col < data[0].length; col++) {
				params.put((String) data[header][col], (String) data[row][col]);
			} // for col
			tests.put(Integer.toString(row), params);
		} // for rows
		return tests;
	}// read test param

	public static LinkedHashMap<String, Object> readUsers() throws Exception {
		/*
		[
		  {
		    ibatta@dbi.com={
		      name=U1,
		      title=MR.,
		      username=ibatta,
		      firstName=Accept,
		      lastName=Tester,
		      password=1234567,
		      mail=ibatta@dbi.com
		    }
		  }
		]
		 */
		LinkedHashMap<String, Object> users = new LinkedHashMap<>();
		
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(SheetVariables.users, 1);

		// data map
		int header = 0;
		int id = 0;
		int userName = 1;
		int name = 2;
		int password = 3;
		int plants = 4;

		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, Object> user = new LinkedHashMap<>();
			user.put((String) data[header][id], data[row][id]);
			user.put((String) data[header][userName], data[row][userName]);
			user.put((String) data[header][name], data[row][name]);
			user.put((String) data[header][password], data[row][password]);
			user.put((String) data[header][plants], data[row][plants]);
			
			users.put((String) data[row][userName], user);
		}
		logs.debug(Arrays.asList(users)+"");
		return users;
	}//read users
	
	public static String[] readRunners() throws Exception {
		ArrayList<String> runners = new ArrayList<String>();
		
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(SheetVariables.RunnersRegressionSheet);
		
		// data map
		int name = 0;
		
		logs.debug((String) data[0][name]+"");
		for (int row = 0; row < data.length; row++) {
			runners.add((String) data[row][name]);
		}
		return runners.toArray(new String[runners.size()]); 
	}//read runners
	
	public static String[] readBrowsers() throws Exception {

		ArrayList<String> browsers = new ArrayList<String>();
		
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(SheetVariables.BrowsersListingSheet);

		// data map
		int name = 0;

		for (int row = 0; row < data.length; row++) {
				browsers.add((String) data[row][name]);
		}
		return browsers.toArray(new String[browsers.size()]);
	}//read browsers

	public static void takeScreenShot2() {
		ReportUtil.takeScreenShot(getDriver(), "common");
	}

}// class
