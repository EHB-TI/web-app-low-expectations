package com.brielage.uitleendienst.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum APILogger {
    ;

    private static final Logger logger = LoggerFactory.getLogger(
            APILogger.class.getName());

    public static void logResult (String s) {logger.info("result: " + s);}

    public static void logRequest (
            String s,
            String j) {logger.info("request " + s + ": " + j);}

    public static void logRequest (String s)         {logger.info("request " + s);}

    public static void logException (final String s) {logger.info("exception: " + s);}

    public static void logFail (String s)            {logger.info("fail: " + s);}
}
