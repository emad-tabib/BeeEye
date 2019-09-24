package com.generic.page;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.generic.selector.HomePageSelector;
import com.generic.selector.PlantOverViewSelector;
import com.generic.selector.SignInSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;

public class Dashboard extends SelTestCase {
	// done RC
	public static List<WebElement> getBestFlowCard() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> allPlants = getDriver().findElements(By.cssSelector(SignInSelectors.plantName));
			getCurrentFunctionName(false);
			return allPlants;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done RC
	public static void navigateToPlant(WebElement plant) {
		try {
			getCurrentFunctionName(true);
			logs.debug("Navigating to plant :" + plant.getText());
			plant.click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	public static void navigateToTab(String tabSelector) {
		try {
			getCurrentFunctionName(true);
			WebElement tab = getDriver().findElement(By.cssSelector(tabSelector));
			logs.debug("Navigating to Tab:" + tab.getText());
			tab.click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done RC
	public static List<WebElement> getAllInsights() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			List<WebElement> allInsights = getDriver().findElements(By.cssSelector(PlantOverViewSelector.Insight));
			logs.debug("Found " + allInsights.size() + " Insights");
			getCurrentFunctionName(false);
			return allInsights;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	// done RC
	public static WebElement getPlantHealthIndicators() {

		try {
			getCurrentFunctionName(true);
			WebElement allHealthIndicators = getDriver()
					.findElement(By.cssSelector(HomePageSelector.PlantHealthIndicators));
			logs.debug("Found " + allHealthIndicators.getText() + "Health Indicators ");
			getCurrentFunctionName(false);
			return allHealthIndicators;

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	// done RC
	public static WebElement getPlantHeatMapContaitnerString() {

		try {
			getCurrentFunctionName(true);
			WebElement heatMapContainer = getDriver()
					.findElement(By.cssSelector(HomePageSelector.PlantHeatMap));
			logs.debug("Found " + heatMapContainer.getText() + "Heat Map Numbers ");
			getCurrentFunctionName(false);
			return heatMapContainer;

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// done RC
	public static WebElement getPlantGeneralInfoContainer() {

		try {
			getCurrentFunctionName(true);
			WebElement GeneralInfoContainer = getDriver()
					.findElement(By.cssSelector(PlantOverViewSelector.PlantGeneralInfoContainer));

			getCurrentFunctionName(false);
			return GeneralInfoContainer;

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// done RC
		public static WebElement getInvertersGeneralInfoContainer() {

			try {
				getCurrentFunctionName(true);
				WebElement GeneralInfoContainer = getDriver()
						.findElement(By.cssSelector(PlantOverViewSelector.InvertersGeneralInfoContainer));

				getCurrentFunctionName(false);
				return GeneralInfoContainer;

			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		
		// done RC
		public static WebElement getStringsGeneralInfoContainer() {

			try {
				getCurrentFunctionName(true);
				WebElement GeneralInfoContainer = getDriver()
						.findElement(By.cssSelector(PlantOverViewSelector.StringsGeneralInfoContainer));

				getCurrentFunctionName(false);
				return GeneralInfoContainer;

			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}



	public static boolean isPlantOverViewLoaded() {

		try {
			getCurrentFunctionName(true);
			getCurrentFunctionName(false);
			return getDriver().findElements(By.cssSelector(PlantOverViewSelector.PlantGeneralInfoContainer)).size() > 0;

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	

}
