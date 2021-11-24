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

    public static void logRequest (String s) {logger.info("request " + s);}
}
