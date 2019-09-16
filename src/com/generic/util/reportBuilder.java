package com.generic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.generic.setup.EnvironmentFiles;
import com.generic.setup.SelTestCase;

public class reportBuilder extends SelTestCase {

	public static loggerUtils logs = new loggerUtils("Default");
	private static File PathObj ;

	public static void main(String[] args) throws IOException {
		PathObj = new File ("F:\\WS\\SAS_FW_testNg_justDriver_static\\logs\\reportLogs\\result_"+now("hhmmssaaa"));
		logs.debug(readLogs(PathObj.toString())+"");
	}
	
	 public static String now(String dateFormat) {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	        return sdf.format(cal.getTime());

	    }

	public static ArrayList<HashMap<String, String>> readLogs( String dst) throws IOException {
		boolean dumyTestFound = false;
		File folder = new File(EnvironmentFiles.getLogReportPath());
		//File folder = new File("F:\\WS\\SAS_FW_testNg_justDriver_static\\logs\\reportLogs");
		File[] listOfFiles = folder.listFiles();
		ArrayList<HashMap<String,String>> details = new ArrayList<HashMap<String,String>>();
		
		if(! new File(dst).exists())
			new File(dst).mkdir();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (!dumyTestFound) {
					dumyTestFound = checkDumyText(listOfFiles[i]);
					continue;
				}
				if(listOfFiles[i].getName().endsWith("html")) {
					details.add(extractDetailsFromFile(listOfFiles[i]));
				}
				
				File f = new File(dst + "\\" + listOfFiles[i].getName());
				FileChannel source = new FileInputStream(listOfFiles[i]).getChannel();
				FileChannel destination = new FileOutputStream(f).getChannel();
				destination.transferFrom(source, 0, source.size());
			}
		}
		return details;

	}

	private static HashMap<String,String> extractDetailsFromFile(File file) {
		HashMap<String,String> testCaseDetails = new HashMap<String,String>();
		String content = "";
		try {
			content = new String(Files.readAllBytes(file.toPath())).trim();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String [] lines = content.split("\n");
		testCaseDetails.put("StartTime", getStartTime(content));
		testCaseDetails.put("EndTime", getEndTime(lines[lines.length-1]));
		testCaseDetails.put("TestName",getTestName(content));
		testCaseDetails.put("Details", getTestDetails(content));
		testCaseDetails.put("Browser", getTestBrowser(content));
		testCaseDetails.put("Status", getTestStatus(content));
		testCaseDetails.put("Screenshot", getTestscreenshot(content));
		testCaseDetails.put("LogFileName", file.getName().trim());
		return testCaseDetails;
	}

	private static String getTestscreenshot(String content) {
		Pattern p = Pattern.compile("Case screenshot:<br>.*");
		Matcher m = p.matcher(content);   // get a matcher object
		String status = "";
		if (m.find())
			status = m.group(0).replace("<br>", "");
		return status;
	}

	private static String getTestStatus(String content) {
		Pattern p = Pattern.compile("Test Status:.*");
		Matcher m = p.matcher(content);   // get a matcher object
		String status = "";
		if (m.find())
			status = m.group(0).replace("<br>", "").split(":")[1].trim();
		return status;
	}

	private static String getTestBrowser(String content) {
		Pattern p = Pattern.compile("Case Browser:.*");
		Matcher m = p.matcher(content);   // get a matcher object
		String browser = "";
		if (m.find())
			browser = m.group(0).replace("<br>", "").split(":")[1].trim();
		return browser;
	}

	private static String getEndTime(String line) {
			String endTime  = line.split(" ")[1];
			return endTime;
		
	}

	private static String getStartTime(String content) {
		Pattern p = Pattern.compile(".*: Case started");
		  Matcher m = p.matcher(content);   // get a matcher object
		  String startTime = "";
		  if (m.find())
			  startTime = m.group(0).split(" ")[1].trim();
			
		return startTime;
	}

	private static String getTestDetails(String content) {
		Pattern p = Pattern.compile("Case Description:.*");
		Matcher m = p.matcher(content);   // get a matcher object
		String description = "";
		if (m.find())
			description = m.group(0);
		return description;
	}

	private static String getEnvironmentName(String currentLine, String delimiter) {
		String env = currentLine.split(delimiter)[1];
		env = env.substring(0, env.length()-4);
		return env;
	}

	public static String getTestName(String content) {
	  Pattern p = Pattern.compile("logs init TEST:.*");
	  Matcher m = p.matcher(content);   // get a matcher object
	  String testName = "";
	  if (m.find())
		  testName = m.group(0).split(": ")[1].replace("<br>", "");
		
	return testName;
	}

	private static boolean checkDumyText(File file) {
		String content = "";
		
		try {
			content = new String(Files.readAllBytes(file.toPath())).trim();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Pattern p = Pattern.compile("logs init TEST:.*: Default<br>");
		Matcher m = p.matcher(content);   // get a matcher object
		if (m.find()) {
			return true;
		}
		return false;
		
	}//check dummy test

}
