package com.generic.util;


import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import com.generic.setup.LoggingMsg;

//Auto-generated Javadoc
/**
 * The Class RandomUtilities.
 */
public final class RandomUtilities {

    /**
     * MY_DAYS constant is being used in wait methods.
     */
    public static final int MY_DAYS = 365;
    public static loggerUtils logs = new loggerUtils("Random");

    /**
     * MY_DAYS constant is being used in wait methods.
     */
    public static final int MY_INT = 730;
    /** The words. */
    private static String[] words = new String[] {
        "abcdd", //$NON-NLS-1$
        "posrt", //$NON-NLS-1$
        "wnsko", //$NON-NLS-1$
        "fnsh", //$NON-NLS-1$
        "ashd", //$NON-NLS-1$
        "cvsg", //$NON-NLS-1$
        "bgaty", //$NON-NLS-1$
        "ngjhy", //$NON-NLS-1$
        "mjued", //$NON-NLS-1$
        "rertg", //$NON-NLS-1$
        "ghwft", //$NON-NLS-1$
        "rtkgh", //$NON-NLS-1$
        "lkyop", //$NON-NLS-1$
        "inpdunt", //$NON-NLS-1$
        "utlr", //$NON-NLS-1$
        "lawb", //$NON-NLS-1$
        "etqg", //$NON-NLS-1$
        "doae", //$NON-NLS-1$
        "majn", //$NON-NLS-1$
        "aiqyam", //$NON-NLS-1$
        "wekrt", //$NON-NLS-1$
        "sehe", //$NON-NLS-1$
        "dmlp", //$NON-NLS-1$
        "voptht", //$NON-NLS-1$
        "atth", //$NON-NLS-1$
        "verws", //$NON-NLS-1$
        "esdt", //$NON-NLS-1$
        "etscp", //$NON-NLS-1$
        "accu", //$NON-NLS-1$
        "etaap", //$NON-NLS-1$
        "jqus", //$NON-NLS-1$
        "dujpi", //$NON-NLS-1$
        "gdol", //$NON-NLS-1$
        "etqpo", //$NON-NLS-1$
        "ealr", //$NON-NLS-1$
        "repbm", //$NON-NLS-1$
        "stus", //$NON-NLS-1$
        "cltl", //$NON-NLS-1$
        "kaspp", //$NON-NLS-1$
        "gunw", //$NON-NLS-1$
        "nogqi", //$NON-NLS-1$
        "seaa", //$NON-NLS-1$
        "takqima", //$NON-NLS-1$
        "sanlct", //$NON-NLS-1$
        "estpg"}; //$NON-NLS-1$


    /**number.
     *
     */
    private static String[] number = {
        "12", "34", "25",
        "78", "90", "10",
        "31", "43", "52",
        "87", "09", "56",
        "16", "26", "76",
        "31", "35", "37",
        "54", "56", "58",
        "69", "94", "23",
        "45", "47", "49",
        "13", "15", "17",
        "19", "89", "86",
        "82", "51", "57",
        "59", "29", "24",
        "42", "48", "53",
        "43", "33", "34",
        "39", "22", "27",
        "87", "94", "91",
        "63", "65", "60",
        "40", "94", "81",
        "11", "73", "71"};

    /**
     * random number producer.
     */
    private static Random random = new Random();

    /**
     * utility class, don't instantiate.
     */
    private RandomUtilities() {
        super();
    }

    /**
     * returns a random word.
     *
     * @return random word
     */
    public static String getRandomWord() {
        return words[random.nextInt(words.length)];
    }

    /**
     * returns a random email.
     *
     * @return random email
     */
    public static String getRandomEmail() {
        final int num = 10000;
        String email = getRandomWord() + random.nextInt(num) + "@" + "random" + ".com"; //$NON-NLS-1$ //$NON-NLS-2$
        logs.debug(MessageFormat.format(LoggingMsg.RANDOM_EMAIL_MSG, email));
        return email.toLowerCase();
    }

    /**getRandomNumber.
     *
     * @return String
     */
    public static String getRandomNumber() {
        return number[random.nextInt(number.length)];
    }

    /**getRandomPhone.
     *
     * @return String
     */
    public static String getRandomPhone() {
        return getRandomNumber() + getRandomNumber() + getRandomNumber()
        + getRandomNumber() + getRandomNumber();
    }

    /**
     * returns a random date.
     *
     * @return random date
     */
    public static Date getRandomDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, MY_DAYS - random.nextInt(MY_INT));
        return calendar.getTime();
    }

    /**getRandomName.
     *
     * @return String
     */
    public static String getRandomName() {
        return getRandomWord() +  getRandomWord();
    }
    
    public static String getRandomPassword(int passwordLength) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return RandomStringUtils.random( passwordLength, characters );
	}
}
