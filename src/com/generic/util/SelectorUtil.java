package com.generic.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.generic.setup.SelTestCase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;

public class SelectorUtil extends SelTestCase {

	public static Boolean isAnErrorSelector = Boolean.FALSE;
	// public static String textValue;
	public static ThreadLocal<String> textValue = new ThreadLocal<String>();
	public static ThreadLocal<Screenshot> screenShot = new ThreadLocal<Screenshot>();
	// public static int numberOfFoundElements;
	public static ThreadLocal<String> numberOfFoundElements = new ThreadLocal<String>();
	private static By parentBy = null;

	@SuppressWarnings("rawtypes")
	public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo,
			boolean isValidationStep) throws IOException, InterruptedException {
		Thread.sleep(2000);
		try {
			Document doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
			Element htmlDoc = doc.select("html").first();
			initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, htmlDoc);
		} catch (NoSuchElementException e) {
			Thread.sleep(10000);
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.firefox))
				Thread.sleep(5000);
			logs.debug("Second try for getting element");
			Document doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
			Element htmlDoc = doc.select("html").first();
			initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, htmlDoc);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo,
			boolean isValidationStep, Element htmlDoc) throws IOException {
		getCurrentFunctionName(true);
		Elements foundElements = null;
		String selectorType = null;

		for (String subStr : webElementsInfo.keySet()) {
			if (subStr.contains("error") && !isAnErrorSelector && isValidationStep) {
				isAnErrorSelector = Boolean.TRUE;
			}
			foundElements = htmlDoc.select("[id=" + subStr + "]");
			selectorType = (!(foundElements.isEmpty()) ? "id" : selectorType);

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact class"));
				foundElements = htmlDoc.select("[class=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "class" : selectorType);
			}

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "i class"));
				foundElements = htmlDoc.select("[class=" + subStr + " i]");
				selectorType = (!(foundElements.isEmpty()) ? "class" : selectorType);
			}

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact name"));
				foundElements = htmlDoc.select("[name=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "name" : selectorType);
			}

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*id"));
				// use regular expression (register.)?firstName for example
				foundElements = htmlDoc.select("[id~=(register.)?" + subStr + "$]");
				selectorType = (!(foundElements.isEmpty()) ? "id" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug("in *id");
				foundElements = htmlDoc.select("[id*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "id" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// use * to mean contains
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*class"));
				foundElements = htmlDoc.select("[class*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "class" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*name"));
				foundElements = htmlDoc.select("[name*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "name" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*title"));
				foundElements = htmlDoc.select("[title*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "title" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "xpath"));
				foundElements = htmlDoc.select("*:containsOwn(" + subStr + ")");
				selectorType = (!(foundElements.isEmpty()) ? subStr : selectorType);
			}

			if (foundElements.isEmpty() && subStr.contains("css,")) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "CSS"));
				foundElements = htmlDoc.select(subStr.replace("css,", ""));
				selectorType = (!(foundElements.isEmpty()) ? subStr : selectorType);
			}

			if (foundElements.isEmpty() && subStr.contains(":eq")) { 
				// TODO: check the if (eq) case
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE,"xpath"));
				foundElements = htmlDoc.select(subStr);
				// nth-child() is the Selenium equivalent to JSoup eq()
				String subStrTemp = subStr;
				if (subStr.contains(":eq")) {
					int startIndex = subStr.indexOf("(") + 1;
					int endIndex = subStr.indexOf(")");
					String nthIndex = subStr.substring(startIndex, endIndex);
					// eq() is zero-base but nth-child() is one-base
					int nthIndexVal = Integer.parseInt(nthIndex) + 1;
					subStrTemp = subStr.replace(nthIndex, "" + nthIndexVal);
					subStrTemp = subStrTemp.replace(":eq", ":nth-child");
				}
				String selType = "css," + subStrTemp;
				selectorType = (!(foundElements.isEmpty()) ? selType : selectorType);
			}

			// The following if is used to get the parent element from which we do a
			// recursive call to get the child on which is our target
			if (webElementsInfo.get(subStr) != null
					&& (webElementsInfo.get(subStr).get("value")).toString().contains("child")
					&& !foundElements.isEmpty()) {
				String tempValue = (webElementsInfo.get(subStr).get("value")).toString();
				Element e = foundElements.first();
				String childSelStr = tempValue.split(",")[1];
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(childSelStr);
				valuesArr.add("");
				LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
				webElementInfo.put("value", "");
				webElementInfo.put("selector", "");
				webElementInfo.put("action", "");
				webElementInfo.put("SelType", "");

				webElementsInfo.remove(childSelStr);
				webElementsInfo.remove(subStr);
				webElementsInfo.put(childSelStr, webElementInfo);
				// Keeping this for Debugging purposes.
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
				// parentBy is essential for driver getElement function used in
				// doAppropriateAction() function to get the same element found by JSoup
				parentBy = getBySelectorForElements(foundElements, selectorType);
				// parent element found should be set to null as we don't have to do the if
				// following this
				foundElements = null;
				SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, e);
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));

			}

			if (foundElements != null && !foundElements.isEmpty()) {
				Map<String, Object> webElementInfo = webElementsInfo.get(subStr);
				webElementInfo.put("selector", getStringSelectorForElements(foundElements, selectorType));
				webElementInfo.put("SelType", selectorType);
				webElementInfo.put("by", getBySelectorForElements(foundElements, selectorType));
				webElementInfo.put("action", getActiontype(foundElements));
				webElementInfo.put("parentBy", parentBy);
				parentBy = null;
				numberOfFoundElements.set(foundElements.size() + "");
				SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.VALID_SEL_MSG, Arrays.asList(webElementInfo)));

			} else {
				logs.debug(LoggingMsg.NO_VALID_SEL_ERROR_MSG);
				throw new NoSuchElementException();
			}
		}
		getCurrentFunctionName(false);

	}

	private static String getActiontype(Elements foundElements) {
		String ActionType = "";
		// TODO fix multiple elements
		for (org.jsoup.nodes.Element e : foundElements) {
			// logs.debug(e.toString());//Debugging purposes
			// logs.debug("DEBUG POINT ------------->" + e.attr("type") );
			if (e.tagName().equals("input") && (e.attr("type").equals("number") || e.attr("type").equals("text")
					|| e.attr("type").equals("password") || e.attr("type").equals("") || e.attr("type").equals("tel")
					|| e.attr("type").equals("email"))) {
				return "type";
			} else if (e.tagName().equals("select")) {
				return "selectByText";
			} else if (e.tagName().equals("input") && e.attr("type").equals("checkbox")) {
				return "check";
			} else if (e.tagName().equals("button") || e.tagName().equals("img") || e.tagName().equals("a")
					|| e.tagName().equals("li") || e.tagName().equals("form") || e.tagName().equals("label")
					|| (e.tagName().equals("input") && (e.attr("type").equals("radio")))) {
				return "click";
			} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
				return "click";
			} else if (e.tagName().equals("input") && e.attr("type").equals("radio")) {
				return "click";
			} else if (e.tagName().equals("p") || e.tagName().equals("tr") || e.tagName().equals("body")
					|| e.tagName().equals("td") || e.tagName().contains("h") || e.tagName().contains("ul")
					|| e.tagName().contains("dd")) {
				return "gettext";
			} else if (e.tagName().equals("div") || e.tagName().equals("span")) {
				return "click,gettext";
			} else {
				logs.debug(LoggingMsg.DEFAULT_ACTION_MSG);
				logs.debug("element type is: " + e.tagName());
				return "Validate";
			}
		}
		return ActionType;
	}

	public static By getBySelectorForElements(Elements foundElements, String selType) {
		By selector = null;
		for (org.jsoup.nodes.Element e : foundElements) {
			selector = null;
			if (selType.equals("id")) {
				selector = By.id(e.id());
			} else if (selType.equals("class")) {
				String[] tempClassArr = e.className().split(" ");
				StringBuilder className = new StringBuilder();
				for (String s : tempClassArr) {
					className.append(".");
					className.append(s);
				}
				selector = By.cssSelector(className.toString());
			} else if (selType.equals("name")) {
				selector = By.name(e.attr("name"));
			} else if (selType.equals("title")) {
				selector = By.xpath("//*[contains(@" + selType + ",'" + e.attr(selType) + "')]");
			} else {

				if (selType.contains("css")) {
					String selTemp = selType.split(",")[1];
					selector = By.cssSelector(selTemp);
				} else {
					selector = By.xpath("//*[contains(text(),'" + selType + "')]");
				}
			}

		}
		return selector;

	}

	public static String getStringSelectorForElements(Elements foundElements, String selType) {
		getCurrentFunctionName(true);

		logs.debug(MessageFormat.format(LoggingMsg.SEL_TYPE, selType));

		String selector = "";
		for (org.jsoup.nodes.Element e : foundElements) {
			selector = null;
			switch (selType) {
			case "id":
				selector = e.id();
				break;
			case "class":
				String[] tempClassArr = e.className().split(" ");
				StringBuilder className = new StringBuilder();
				for (String s : tempClassArr) {
					className.append(".");
					className.append(s);
				}
				selector = className.toString();
				break;
			case "name":
				selector = e.attr("name");
				break;
			case "title":
				selector = "//*[contains(@" + selType + ",'" + e.attr(selType) + "')]";
				break;

			default:
				if (selType.contains("css")) {
					String selTemp = selType.split(",")[1];
					selector = selTemp;
				} else {
					selector = "//*[contains(@text,'" + selType + "')]";
				}
				break;
			}
		}
		getCurrentFunctionName(false);
		return selector;
	}

	public static void doAppropriateAction(Map<String, Object> webElementInfo) throws Exception {
		getCurrentFunctionName(true);
		textValue.set("");

		String browser = SelTestCase.getBrowserName();

		try {
			String selector = (String) webElementInfo.get("selector");
			String action = (String) webElementInfo.get("action");
			String value = (String) webElementInfo.get("value");
			By byAction = (By) webElementInfo.get("by");
			By parentBy = (By) webElementInfo.get("parentBy");
			WebElement parent = null;
			WebElement field = null;
			// get element using parent-child relationship OR DOM-element relationship
			if (byAction != null) {
				if (parentBy != null) {
					parent = getDriver().findElement(parentBy);
					field = parent.findElement(byAction);
				} else {
					// used to get the element at specific index when there are multiple elements of
					// the same selector
					if (value.contains("index")) {
						int elementIndex = Integer.parseInt(value.split(",")[1]);
						field = getDriver().findElements(byAction).get(elementIndex);
					} else {
						field = getDriver().findElement(byAction);
					}
				}
			}
			if (!selector.equals("")) {
				if (!SelectorUtil.isAnErrorSelector) {
					if (value.contains("ForceAction")) {
						action = value.split(",")[1];
					}

					if (action.equals("hover")) {

						Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
								.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
								.ignoring(NoSuchElementException.class);
						// TODO: move it to general function

						logs.debug("Hovering: " + byAction.toString());

						JavascriptExecutor jse = (JavascriptExecutor) getDriver();
						jse.executeScript("arguments[0].scrollIntoView(false)", field);
						Thread.sleep(200);
						WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElement(byAction);
							}
						});

						Actions HoverAction = new Actions(getDriver());
						HoverAction.moveToElement(field2).click().build().perform();
					}

					if (action.equals("type")) {
						if (value.contains("getCurrentValue")) {
							logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt", byAction.toString()));
							JavascriptExecutor jse = (JavascriptExecutor) getDriver();
							jse.executeScript("arguments[0].scrollIntoView(false)", field);
							// I used the value attr instead of getText() as the input has the text as a
							// value
							textValue.set(field.getAttribute("value"));
							logs.debug("the text value is: " + SelectorUtil.textValue.get());
						} else {

							logs.debug(MessageFormat.format(LoggingMsg.WRITING_TO_SEL, "", value, byAction.toString()));
							JavascriptExecutor jse = (JavascriptExecutor) getDriver();
							jse.executeScript("arguments[0].scrollIntoView(false)", field);
							field.clear();
							String tempVal = value;
							if (value.contains("pressEnter")) {
								tempVal = value.split(",")[0];
							}
							field.sendKeys(tempVal);
							if (!tempVal.equals(value)) {
								field.sendKeys(Keys.ENTER);
								if (browser.contains("edge")) {
									field.sendKeys(Keys.ENTER);
								}
							}
						}
					} else if (value.contains("VisualTesting")) {
						Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
								.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
								.ignoring(NoSuchElementException.class);
						// TODO: move it to general function

						logs.debug("Visual testing for: " + field.toString());
						JavascriptExecutor jse = (JavascriptExecutor) getDriver();
						jse.executeScript("arguments[0].scrollIntoView(false)", field);

						Thread.sleep(500);

						WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElement(byAction);
							}
						});
						
						if (browser.contains("mobile")) {
							screenShot.set(new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
									.shootingStrategy(ShootingStrategies.scaling((float) 3.0))
									.takeScreenshot(SelTestCase.getDriver(), field2));
						} else
							screenShot.set(new AShot().takeScreenshot(SelTestCase.getDriver(), field2));
						
					} else if (action.equals("click")) {
						Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
								.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
								.ignoring(NoSuchElementException.class);
						// TODO: move it to general function
						if (!value.contains("noClick")) {

							logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
							JavascriptExecutor jse = (JavascriptExecutor) getDriver();
							jse.executeScript("arguments[0].scrollIntoView(false)", field);
							Thread.sleep(200);
							WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
								public WebElement apply(WebDriver driver) {
									return driver.findElement(byAction);
								}
							});
							logs.debug("browser..." + browser);
							if (browser.contains(GlobalVariables.browsers.firefox)) {
								logs.debug("clicking..." + SelTestCase.getBrowserName());
								field2.click();
							} else
								((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
										field2);

						} else
							logs.debug("NO clicking..." + SelTestCase.getBrowserName());
					} else if (action.equals("check")) {
						logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, byAction.toString(), value));
						if (value.contains("true")) {
							if (!field.isSelected()) {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", "not "));
								Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
										.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
										.ignoring(NoSuchElementException.class);

								// TODO: move it to general function
								logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
								JavascriptExecutor jse = (JavascriptExecutor) getDriver();
								jse.executeScript("arguments[0].scrollIntoView(false)", field);
								Thread.sleep(200);
								WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
									public WebElement apply(WebDriver driver) {
										return driver.findElement(byAction);
									}
								});
								logs.debug("browser..." + browser);
								if (browser.contains(GlobalVariables.browsers.firefox)) {
									logs.debug("clicking..." + browser);
									field2.click();
								} else
									((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
											field2);
							} else {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							}
						} else {
							if (field.isSelected()) {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "un", ""));
								Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
										.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
										.ignoring(NoSuchElementException.class);

								// TODO: move this to function
								logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
								JavascriptExecutor jse = (JavascriptExecutor) getDriver();
								jse.executeScript("arguments[0].scrollIntoView(false)", field);
								Thread.sleep(200);
								WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
									public WebElement apply(WebDriver driver) {
										return driver.findElement(byAction);
									}
								});
								logs.debug("browser..." + browser);
								if (browser.contains(GlobalVariables.browsers.firefox)) {
									logs.debug("clicking..." + browser);
									field2.click();
								} else
									((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
											field2);
							} else {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							}
						}
					} else if (action.equals("gettext")) {
						logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt", byAction.toString()));
						textValue.set(field.getText());
						logs.debug("text is :" + textValue.get());
					} else if (action.equals("click,gettext")) {
						logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt, click", byAction.toString()));

						JavascriptExecutor jse = (JavascriptExecutor) getDriver();
						jse.executeScript("arguments[0].scrollIntoView(false)", field);

						String textVal = "";
						try {
							textVal = field.getText();
							textValue.set(textVal);
							logs.debug("text is :" + textValue.get());
						} catch (Exception e) {
							logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));
							throw new NoSuchElementException(
									MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));

						}
						try {
							if (value.isEmpty()) {
								Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
										.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
										.ignoring(NoSuchElementException.class);
								// TODO: move this to function

								logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
								JavascriptExecutor jse1 = (JavascriptExecutor) getDriver();
								jse1.executeScript("arguments[0].scrollIntoView(false)", field);
								Thread.sleep(200);
								WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
									public WebElement apply(WebDriver driver) {
										return driver.findElement(byAction);
									}
								});
								logs.debug("browser..." + browser);
								if (browser.contains(GlobalVariables.browsers.firefox)) {
									logs.debug("clicking..." + browser);
									field2.click();
								} else
									((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
											field2);
								// field.click();
							}
						} catch (Exception e) {
							logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
							throw new NoSuchElementException(
									MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
						}

					} else if (action.equals("selectByText")) {
						logs.debug(
								MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "value", byAction.toString()));
						Select select = new Select(field);

						// //Keep the block below for debugging purposes
						// List<WebElement> options = select.getOptions();
						// logs.debug(options.toString());
						// for(int i=1; i<options.size(); i++) {
						// logs.debug(options.get(i).getText());
						// }
						String textVal = "";
						try {
							if (!value.isEmpty() && !value.contains("FFF")) {
								List<WebElement> options = select.getOptions();
								for (int i = 0; i < options.size(); i++) {
									// logs.debug(options.get(i).getText().trim());
									if (options.get(i).getText().toLowerCase().trim().contains(value.toLowerCase())
											&& !value.equals("")) {
										logs.debug(MessageFormat.format(LoggingMsg.SELECTED_INDEX, i));
										select.selectByIndex(i);
										break;
									}
								}
							} else if (value.contains("FFF")) {
								String index = value.split("FFF")[1];
								logs.debug(
										"Direct selection " + MessageFormat.format(LoggingMsg.SELECTED_INDEX, index));
								select.selectByIndex(Integer.parseInt(index));
							} else {
								textVal = select.getFirstSelectedOption().getText();
							}
						} catch (Exception e) {
							logs.debug(LoggingMsg.TRY_ALT_WAY_MSG);
							if (!value.isEmpty()) {
								select.selectByVisibleText(value);
							} else {
								textVal = select.getFirstSelectedOption().getText();
							}
						}
						textValue.set(textVal);
					}
				} else if (action.equals("Validate") && SelectorUtil.isAnErrorSelector) {
					if (!value.isEmpty()) {
						Assert.assertEquals("The " + selector + " has incorrect error msg", field.getText(), value);
						logs.debug(MessageFormat.format(LoggingMsg.ERROR_VERIFICATION_SEL_MSG, selector));
					}
				}
			} else {
				throw new Exception(ExceptionMsg.noValidSelector);
			}
		} catch (Exception e) {
			throw e;
		}
		getCurrentFunctionName(false);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isDisplayed(List<String> subStrArr) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);

		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));
		if (items.size() == 0)
			return false;

		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(false)", items.get(0));
		boolean isDisplayed = true;
		if (!items.get(0).isDisplayed())
			isDisplayed = false;
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isDisplayed(List<String> subStrArr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));

		boolean isDisplayed = true;
		if (!items.get(index).isDisplayed())
			isDisplayed = false;
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public static boolean isNotDisplayed(List<String> subStrArr) throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed = false;

		try {
			List<String> valuesArr = new ArrayList<String>();
			valuesArr.add("");
			LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
					new ArrayList<String>(subStrArr), valuesArr, false);
			return isNotDisplayed;

		} catch (NoSuchElementException e) {
			isNotDisplayed = true;
			return isNotDisplayed;
		}
	}

	@SuppressWarnings("rawtypes")
	public static String getAttr(List<String> subStrArr, String attr) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));
		String attrValue = items.get(0).getAttribute(attr);
		getCurrentFunctionName(false);
		return attrValue;
	}

	@SuppressWarnings("rawtypes")
	public static String getAttr(List<String> subStrArr, String attr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));
		String attrValue = items.get(index).getAttribute(attr);
		getCurrentFunctionName(false);
		return attrValue;
	}

	@SuppressWarnings("rawtypes")
	public static WebElement getNthElement(List<String> subStrArr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));

		JavascriptExecutor jse1 = (JavascriptExecutor) getDriver();
		jse1.executeScript("arguments[0].scrollIntoView(false)", items.get(index));

		getCurrentFunctionName(false);
		return items.get(index);
	}

	@SuppressWarnings("rawtypes")
	public static List<WebElement> getAllElements(List<String> subStrArr) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));

		getCurrentFunctionName(false);
		return items;
	}

	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(List<String> subStrArr,
			List<String> valuesArr) throws Exception {
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, true);
	}

	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(List<String> subStrArr)
			throws Exception {
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, true);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(String selector) throws Exception {
		
		List<String> subStrArrList = new ArrayList<String>();
		List<String> valuesArrList = new ArrayList<String>();
		subStrArrList.add(selector);
		valuesArrList.add("");

		return initializeSelectorsAndDoActions(subStrArrList,valuesArrList); 
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(String selector,
			String selValue) throws Exception {
		
		List<String> subStrArrList = new ArrayList<String>();
		List<String> valuesArrList = new ArrayList<String>();
		subStrArrList.add(selector);
		valuesArrList.add(selValue);

		return initializeSelectorsAndDoActions(subStrArrList,valuesArrList); 
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(List<String> subStrArr,
			List<String> valuesArr, boolean action) throws Exception {
		LinkedHashMap<String, LinkedHashMap> webElementsInfo = new LinkedHashMap<String, LinkedHashMap>();

		int index = 0;
		boolean isValidationStep = false;
		for (String key : subStrArr) {
			// logs.debug(key);
			LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
			webElementInfo.put("value", valuesArr.get(index));
			webElementInfo.put("selector", "");
			webElementInfo.put("action", "");
			webElementInfo.put("SelType", "");
			index++;

			webElementsInfo.remove(key);
			webElementsInfo.put(key, webElementInfo);
		}
		try {
			// Keeping this for Debugging purposes.
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
			SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep);
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));

			if (action) {
				for (String key : webElementsInfo.keySet()) {
					LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
					SelectorUtil.doAppropriateAction(webElementInfo);
				}

				Thread.sleep(1000);
			}
		} catch (Exception e) {
			logs.debug(MessageFormat.format(LoggingMsg.FORMATTED_ERROR_MSG, e.getMessage()));
			throw new NoSuchElementException("No such element: " + Arrays.asList(webElementsInfo));

		} finally {
			valuesArr.clear();
			subStrArr.clear();
		}

		logs.debug(MessageFormat.format(LoggingMsg.FINISHED_ACTION_ON_ELEMENTS_MSG, Arrays.asList(webElementsInfo)));
		return webElementsInfo;
		
	}
}
