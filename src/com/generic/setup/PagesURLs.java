package com.generic.setup;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

public class PagesURLs extends SelTestCase {
	public static String HP = "HomePage";
	public static String driversPath = "driversPath";
	public static String personalDetailsPage = "personalDetailsPage";
	public static String emailAddressPage = "emailAddressPage";
	public static String paymentDetailsPage = "paymentDetailsPage";
	public static String passwordPage = "passwordPage";
	public static String orderHistoryPage = "orderHistoryPage";
	public static String addressBookPage = "addressBookPage";
	public static String savedCartsPage = "savedCartsPage";
	public static String consentManagementPage = "consentManagementPage";
	public static String supportTicketsPage = "supportTicketsPage";
	public static String returnHistoryPage = "returnHistoryPage";
	public static String shoppingCartPage = "shoppingCartPage";
	public static String signOutPage = "signOutPage";
	public static String storeFinderPage = "storeFinderPage";
	public static String errorPage = "errorPage";
	public static String PLP = "PLP";
	public static String CLP = "CLP";
	public static String fav = "fav";
	
	public static String getDriversPath(String driverName) {
		try {
			String driverFolder = getCONFIG().getProperty(driversPath);
			String driverFullPath = "";
			if(driverFolder.endsWith("\\")) {
				driverFullPath = driverFolder + driverName + "Driver.exe";
			} else {
				driverFullPath = driverFolder + "\\" + driverName + "Driver.exe";
			}
		return driverFullPath;
		} catch(Throwable t) {
			throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, driversPath));
		}
	}
	public static void setDriversPath(String newDriversPath) {
		try {
			getCONFIG().setProperty(driversPath,newDriversPath);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, driversPath));
			}
	}
	public static String getPersonalDetailsPage() {
		try {
			return getCONFIG().getProperty(personalDetailsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, personalDetailsPage));
			}
	}
	public static void setPersonalDetailsPage(String newPersonalDetailsPage) {
		try {
			getCONFIG().setProperty(personalDetailsPage,newPersonalDetailsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, personalDetailsPage));
			}
	}
	public static String getEmailAddressPage() {
		try {
			return getCONFIG().getProperty(emailAddressPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, emailAddressPage));
			}
	}
	public static void setEmailAddressPage(String newEmailAddressPage) {
		try {
			getCONFIG().setProperty(emailAddressPage,newEmailAddressPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, emailAddressPage));
			}
	}
	public static String getPaymentDetailsPage() {
		try {
			return getCONFIG().getProperty(paymentDetailsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, paymentDetailsPage));
			}
	}
	public static void setPaymentDetailsPage(String newPaymentDetailsPage) {
		try {
			getCONFIG().setProperty(paymentDetailsPage,newPaymentDetailsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, paymentDetailsPage));
			}
	}
	public static String getPasswordPage() {
		try {
			return getCONFIG().getProperty(passwordPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, passwordPage));
			}
	}
	public static void setPasswordPage(String newPasswordPage) {
		try {
			getCONFIG().setProperty(passwordPage,newPasswordPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, passwordPage));
			}
	}
	public static String getOrderHistoryPage() {
		try {
			return getCONFIG().getProperty(orderHistoryPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, orderHistoryPage));
			}
	}
	public static void setOrderHistoryPage(String newOrderHistoryPage) {
		try {
			getCONFIG().setProperty(orderHistoryPage,newOrderHistoryPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, orderHistoryPage));
			}
	}
	public static String getAddressBookPage() {
		try {
			return getCONFIG().getProperty(addressBookPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, addressBookPage));
			}
	}
	public static void setAddressBookPage(String newAddressBookPage) {
		try {
			getCONFIG().setProperty(addressBookPage,newAddressBookPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, addressBookPage));
			}
	}
	public static String getSavedCartsPage() {
		try {
			return getCONFIG().getProperty(savedCartsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, savedCartsPage));
			}
	}
	public static void setSavedCartsPage(String newSavedCartsPage) {
		try {
			getCONFIG().setProperty(savedCartsPage,newSavedCartsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, savedCartsPage));
			}
	}
	public static String getConsentManagementPage() {
		try {
			return getCONFIG().getProperty(consentManagementPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, consentManagementPage));
			}
	}
	public static void setConsentManagementPage(String newConsentManagementPage) {
		try {
			getCONFIG().setProperty(consentManagementPage,newConsentManagementPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, consentManagementPage));
			}
	}
	public static String getSupportTicketsPage() {
		try {
			return getCONFIG().getProperty(supportTicketsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, supportTicketsPage));
			}
	}
	public static void setSupportTicketsPage(String newSupportTicketsPage) {
		try {
			getCONFIG().setProperty(supportTicketsPage,newSupportTicketsPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, supportTicketsPage));
			}
	}
	public static String getReturnHistoryPage() {
		try {
			return getCONFIG().getProperty(returnHistoryPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, returnHistoryPage));
			}
	}
	public static void setReturnHistoryPage(String newReturnHistoryPage) {
		try {
			getCONFIG().setProperty(returnHistoryPage,newReturnHistoryPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, returnHistoryPage));
			}
	}
	public static String getShoppingCartPage() {
		try {
			return getCONFIG().getProperty(shoppingCartPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, shoppingCartPage));
			}
	}
	public static void setShoppingCartPage(String newShoppingCartPage) {
		try {
			getCONFIG().setProperty(shoppingCartPage,newShoppingCartPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, shoppingCartPage));
			}
	}
	public static String getSignOutPage() {
		try {
			return getCONFIG().getProperty(signOutPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, signOutPage));
			}
	}
	public static void setSignOutPage(String newSignOutPage) {
		try {
			getCONFIG().setProperty(signOutPage,newSignOutPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, signOutPage));
			}
	}
	public static String getStoreFinderPage() {
		try {
			return getCONFIG().getProperty(storeFinderPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, storeFinderPage));
			}
	}
	public static void setStoreFinderPage(String newStoreFinderPage) {
		try {
			getCONFIG().setProperty(storeFinderPage,newStoreFinderPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, storeFinderPage));
			}
	}
	public static String getErrorPage() {
		try {
			return getCONFIG().getProperty(errorPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, storeFinderPage));
			}
	}
	public static void setErrorPage(String newErrorPage) {
		try {
			getCONFIG().setProperty(errorPage,newErrorPage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, newErrorPage));
			}
	}
	public static String getPLP() {
		try {
			return getCONFIG().getProperty(PLP);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, storeFinderPage));
			}
	}
	public static void setPLP(String newPLP) {
		try {
			getCONFIG().setProperty(PLP,newPLP);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, PLP));
			}
	}
	public static String getHomePage() {
		try {
			return getCONFIG().getProperty(HP);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, HP));
			}
	}
	public static void setHomePage(String newHomePage) {
		try {
			getCONFIG().setProperty(HP,newHomePage);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, HP));
			}
	}
	public static String getCLP() {
		try {
			return getCONFIG().getProperty(CLP);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, CLP));
			}
	}
	public static void setCLP(String newCLP) {
		try {
			getCONFIG().setProperty(CLP,newCLP);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, CLP));
			}
	}
	public static String getfav() {
		try {
			return getCONFIG().getProperty(fav);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, fav));
			}
	}
	public static void setFav(String newfav) {
		try {
			getCONFIG().setProperty(fav,newfav);
			} catch(Throwable t) {
				throw new NoSuchElementException(MessageFormat.format(LoggingMsg.PROPERTY_ERROR_MSG, fav));
			}
	}

}
