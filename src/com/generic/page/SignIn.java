package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import com.generic.selector.SignInSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class SignIn extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
		public static final String id = "id";
		public static final String userName = "userName";
		public static final String name = "name";
		public static final String password = "password";
	}

	// done BI
	public static void logIn(String userName, String Password) throws Exception {
		try {
			getCurrentFunctionName(true);
			fillLoginFormAndClickSubmit(userName, Password);
			Thread.sleep(1000);
			if (!checkUserAccount()) {
				throw new Exception("Login failed");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done RC
	public static void fillLoginFormAndClickSubmit(String userName, String Password) throws Exception {
		try {
			getCurrentFunctionName(true);
			// typePassword(Password + ",pressEnter");
			typeUsername(userName);
			typePassword(Password);
			clickLogin();
			Thread.sleep(5000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done BI
	public static void clickLogin() throws Exception {
		try {
			getCurrentFunctionName(true);
			getDriver().findElement(By.cssSelector(SignInSelectors.loginBtn)).click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done BI
	public static void typePassword(String password) throws Exception {
		try {
			getCurrentFunctionName(true);
			getDriver().findElement(By.id(SignInSelectors.password)).sendKeys(password);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done BI
	public static void typeUsername(String userName) throws Exception {
		try {
			getCurrentFunctionName(true);
			getDriver().findElement(By.id(SignInSelectors.userName)).sendKeys(userName);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done BI
	public static boolean checkUserAccount() throws Exception {
		try {
			getCurrentFunctionName(true);
			String url = getDriver().getCurrentUrl();
			getCurrentFunctionName(false);
			return url.contains("dashboard");
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static String getErrorMessage() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.errorMessage);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			String errorMsg = SelectorUtil.textValue.get();
			logs.debug("Error Message is: " + errorMsg);
			getCurrentFunctionName(false);
			return errorMsg;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

}
