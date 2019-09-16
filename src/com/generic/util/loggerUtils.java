package com.generic.util;

import org.apache.log4j.Logger;


//import org.apache.log4j.Logger;

public class loggerUtils {
	//private static ThreadLocal<Logger> loggers = new ThreadLocal<Logger>();
	private String testName = "";
	private Logger logs22;

	public void debug(String msg) {
		// System.out.println(msg);
		logs22.debug(testName +" : "+ Thread.currentThread().getId() +" : " + msg);
		Thread.currentThread().getThreadGroup();
		//Reporter.log(msg, true );
	}
	
	public loggerUtils(String testNameincome) {
		testName = testNameincome;
		logs22 = Logger.getLogger("logs");
		debug(">>logs init TEST:" +Thread.currentThread().getId()+" : " +testNameincome);
	}
}
