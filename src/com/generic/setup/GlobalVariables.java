package com.generic.setup;

import java.text.MessageFormat;
import java.util.ArrayList;

import com.generic.setup.SelTestCase;



public class GlobalVariables extends SelTestCase {


	public class browsers {
		public static final String firefox = "Firefox";
		public static final String chrome = "chrome";
		public static final String Nexus = "mobile_Nexus 5";
		public static final String IE = "IE";
		public static final String edge = "edge";
		public static final String iOS = "IOS_grid";
	}
	
	
	/*public String firstName;
    public String lastName;
    public String emailAddress;
    public String confEmailAddress;
    public String zipCode;
    public String country;
    public String password;
    public String confPassword;*/
   
    public ArrayList<String> list;

    public static boolean flag = false;

   
    public GlobalVariables(String sheetName, int row) {
    	getCurrentFunctionName(true);
    	logs.debug(MessageFormat.format(LoggingMsg.READING_DATA_FROM_SHEET, sheetName, row));

       /* firstName = SelTestCase.getCONFIG().getProperty("firstName");
        lastName = SelTestCase.getCONFIG().getProperty("lastName");
        emailAddress = SelTestCase.getCONFIG().getProperty("emailAddress");
        confEmailAddress = SelTestCase.getCONFIG().getProperty("confEmailAddress");
        zipCode = SelTestCase.getCONFIG().getProperty("postalCode");
        password = SelTestCase.getCONFIG().getProperty("password");
        confPassword = SelTestCase.getCONFIG().getProperty("confPassword");
        country = SelTestCase.getCONFIG().getProperty("country");
        */
        SelTestCase.getCurrentFunctionName(false);

    }
     
}
