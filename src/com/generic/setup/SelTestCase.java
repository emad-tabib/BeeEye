package com.generic.setup;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


//import org.apache.log4j.Logger;

import com.generic.util.loggerUtils;
import com.generic.util.SendMail;

import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlTest;

import com.generic.setup.GlobalVariables.browsers;
import com.generic.util.ReportAnalyzer;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;
import com.generic.util.XlsUtils;
import com.generic.util.reportBuilder;

public class SelTestCase {
	
	public static String time_date_format = "hh:mm:ss";
	public static String time_date_formatScreenshot = "hhmmss-SSSaaa";
	public static String reportFolderDateStampFormat = "MM-dd-yyyy";
	public static String reportFolderTimeStampFormat = "HHmmss";
	public static String testCaseRunDateStamp = "dd.MMMMM.yyyy";

    public static boolean runReportSetup = true;
    public static String mainReportDir = null;
    public static String reportDirectory = null;
    
    public static loggerUtils logs = new loggerUtils("Default");
    
    //protected SoftAssert softAssert = new SoftAssert();
    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<SoftAssert>();
    public static ThreadLocal<XmlTest> testObj = new ThreadLocal<XmlTest>();
    
    //private static ThreadLocal<String> testName= new ThreadLocal<String>(); 
    public static int counter ;
    private static Properties CONFIG = null;
    
    //private WebDriver driver = null;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    
    private static ThreadLocal<loggerUtils>TestLog = new ThreadLocal<loggerUtils>();
    
    private static int waitTime;
    private static XlsUtils datatable = null;
    protected static String testCaseReportName = "";
    private static String startTime = null;
    private static String testStatus = null;
    private static String screenShotName = null;
    private static String browserName = null;
    private static int testCaseRowNum;
    private static Throwable Error = null;
    
    
    public static String logDir= null;
    
    public static String testCaseRepotId = null;
    public static String testCaseDescription = null;
    
    public static int caseIndex;
    
    public static String rUNDATE = ReportUtil.now(time_date_format).toString();
    public static String suiteName;
    
    public static LinkedHashMap<String, Object> users =null ;
    public static LinkedHashMap<String, Object> addresses = null; 
    public static LinkedHashMap<String, Object> invintory = null;
    public static LinkedHashMap<String, Object> paymentCards = null;
    
    
    //Database conf 
    public static String TableName = "RayDailyData"; 
    public static String DatabaseName = "RayBase.db";

    public static String getBrowserName() {
        //return browserName;
        return testObj.get().getParameter("browserName");
    }

    public static void setBrowserName(String browserName) {
        //SelTestCase.browserName = browserName;
    }

    public static String getScreenShotName() {
        return screenShotName;
    }


    public static void setScreenShotName(String screenShotName) {
        SelTestCase.screenShotName = screenShotName;
    }


    public static String getTestStatus() {
        return testStatus;
    }


    public static void setTestStatus(String testStatus) {
        SelTestCase.testStatus = testStatus;
    }

    public static String getStartTime() {
        return startTime;
    }


    public static void setStartTime(String startTime) {
        SelTestCase.startTime = startTime;
    }

    public static XlsUtils getDatatable() {
        return datatable;
    }


    public static void setDatatable(XlsUtils datatable) {
        SelTestCase.datatable = datatable;
    }
    
    public static String getTestCaseDescription() {
        return testCaseDescription;
    }


    public static void setTestCaseDescription(String testDescription) {
        testCaseDescription = testDescription;
    }

    public static int getWaitTime() {
        return waitTime;
    }

    public static void setWaitTime(int time) {
        waitTime = time;
    }

    public static Properties getCONFIG() {
        return CONFIG;
    }

    public static void setCONFIG(Properties cONFIG) {
        CONFIG = cONFIG;
    }


    public static WebDriver getDriver() {
    	return webDriver.get();
    }

    public static void setDriver(WebDriver driver) {
    	webDriver.set(driver);
    }

    public static int getTestCaseRowNum() {
        return testCaseRowNum;
    }


    public static void setTestCaseRowNum(int testCaseRowNum) {
        SelTestCase.testCaseRowNum = testCaseRowNum;
    }
    
    public static String getTestCaseReportName() {
    	if ("".equals(testCaseReportName))
    		return "Automation_Case";
    	return testCaseReportName;
    }

    public static void setTestCaseReportName(String testId) {
        testCaseReportName = testId;
    }

    public static void logCaseDetailds(String msg)
    {
    	logs.debug("Case started: "+Thread.currentThread().getStackTrace()[2]);
    	logs.debug("Case Description: <br>"+msg);
    }
    
    public static void getBrowserWait(String BrowserName)
    {
    	try {
    		int waitBrowser = RandomUtils.nextInt(0,2000); 
    		logs.debug("waiting test: " + waitBrowser);
    		Thread.sleep(waitBrowser);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    	try {
    		if(browserName == null)
    		{
    			Thread.sleep(RandomUtils.nextInt(900,1200));
    		}
    		else {
    		if (BrowserName.equals(GlobalVariables.browsers.firefox))
				Thread.sleep(1000);
			else if (BrowserName.equals(GlobalVariables.browsers.chrome))
				Thread.sleep(500);
			else
				Thread.sleep(RandomUtils.nextInt(900,1200));
    		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public static void failureHandeler(Throwable t, String info)
    {
    	setTestCaseDescription(getTestCaseDescription());
		logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
		t.printStackTrace();
		String temp = getTestCaseReportName();
		Common.testFail(t, temp);
		logs.debug(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
				" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
				" <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
				" \r\n" + 
				" <button type=\"button\" class=\"btn\" data-toggle=\"collapse\" data-target=\"#demo\">code</button>\r\n" + 
				"  <div id=\"demo\" class=\"collapse\">\r\n" + 
				getDriver().getPageSource()+"\r\n" + 
				"  </div>\r\n" + 
				"<br>");			
		ReportUtil.takeScreenShot(getDriver(), info);
		Assert.assertTrue(false, t.getMessage());
    }
    
    public static void getCurrentFunctionName(Boolean start)
    {
    	if (start){
        	logs.debug(MessageFormat.format(LoggingMsg.STARTING_THREAD, Thread.currentThread().getStackTrace()[2]));
        	}
        	else
        	{
        		logs.debug(MessageFormat.format(LoggingMsg.ENDING_THREAD, Thread.currentThread().getStackTrace()[2]));
        	}
    	
	}
    
    
    
    public static  loggerUtils getlogger()
    {
    	return TestLog.get();
    }
    public static  void setlogger(loggerUtils log)
    {
    	TestLog.set(log);
    }
    
    public static  SoftAssert sassert()
    {
    	return softAssert.get();
    }
    public static  void setAssert()
    {
    	softAssert.set(new SoftAssert());
    }


    
    /**setUp function will be invoked by Junit before execution of every test case.
     * It initializes the property files and html report setup
     * @throws Exception
     */
    @BeforeMethod
    public void setUp(XmlTest test) throws Exception  {
    	getCurrentFunctionName(true);
    	
    	if (test.getParameter("browserName")==null)
		{
    		logs.debug("#WARNING: Running test from class it self running on defult browser");
			Map<String, String> parameters = new LinkedHashMap<>();
			parameters.put("browserName", "chrome");
			test.setParameters(parameters);
		}
		
    	
    	suiteName = test.getSuite().getName();
    	testObj.set(test);
    	setAssert();
        try {
        	if ("".equals(testCaseRepotId))
        		setTestCaseReportName(test.getName());
        	else
        		setTestCaseReportName(testCaseRepotId);
        	
            //Initialize the property file
            TestUtilities.configInitialization();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        setTestCaseDescription(getDatatable().getCellData("Test Cases","Description", getTestCaseRowNum()));
        setDriver(Common.initializeBrowser(test.getParameter("browserName")));
        
        try {
        	Common.launchApplication(test.getParameter("browserName"));

        } catch(Throwable t) {
            Error = t;
            logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t));
            throw new Exception(t);
        }
        logs.debug("Driver Details: "+getDriver());
        getCurrentFunctionName(false);
    }


    /**tearDown function will be invoked by Junit after execution of every test case.
     * It adds the test case status into the html report and close the browser.
     * It reads email id from config file. If they are mentioned then framework will zip the html reports
     * and send it into corresponding email ids.
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws InterruptedException 
     * @throws Exception
     */
    @AfterMethod
    public void tearDown() throws FileNotFoundException, IOException, InterruptedException {
    	getCurrentFunctionName(true);
    	getDatatable().writeExcelFile();
    	WebDriver driver = SelTestCase.getDriver();
        if (driver != null && !getBrowserName().contains(browsers.iOS)) {
            driver.quit();
        }
        getCurrentFunctionName(false);
    }
    
    @BeforeSuite
    public static void excelSheetReader() throws Exception
    {
    	logs.debug("loading data store");
    	users = Common.readUsers();
		
    }
    
    @AfterSuite
    public static void reportMaker() throws IOException
    {
    	getCurrentFunctionName(true);
    	
    	ArrayList<HashMap<String, String>> casesDetails = null;
    	try {
			TestUtilities.reportSetup();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	ReportAnalyzer.splitLogs();
    	ReportAnalyzer.splitCases();
    	ReportAnalyzer.copyScreenShots();
    	try {
			casesDetails = reportBuilder.readLogs(reportDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	int passedNumber = 0, failedNumber = 0, skippedNumber = 0;
    	
    	for (HashMap<String, String> status: casesDetails)
    	{
    		if (status.get("Status").toLowerCase().contains("pass")){
    			passedNumber++;
    		}else if (status.get("Status").toLowerCase().contains("fail")) {
				failedNumber++;
			}
    		logs.debug(Arrays.asList(status)+"");
    		if (!(status.get("TestName").equals("") || status.get("StartTime").equals("") || status.get("EndTime").equals("")))
    			ReportUtil.addTestCase(status.get("TestName"), status.get("Details"),status.get("StartTime"),
    				status.get("EndTime"),status.get("Status"),
    				status.get("LogFileName"), status.get("Browser"));
    	}//for
    	
    	ReportUtil.updateEndTime(ReportUtil.now(time_date_format), getTestStatus());
    	
    	ReportUtil.endSuite(passedNumber, failedNumber, skippedNumber);
    	
    	 if (SelTestCase.getCONFIG().getProperty("EmailReport").equalsIgnoreCase("yes"))
         {
         	SendMail.sendSummeryMail(SelTestCase.logDir + "//" +"index.html");
         }
         else
         {
         	SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, "Ignor sending report"));
         }
    	 ReportUtil.copyReportToC(SelTestCase.logDir,"C://AutoRepo");
    	 getCurrentFunctionName(false);
    }
    
}
