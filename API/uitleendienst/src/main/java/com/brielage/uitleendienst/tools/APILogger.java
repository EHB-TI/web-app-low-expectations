package com.brielage.uitleendienst.tools;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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

    public static void logSuccess (String s)         {logger.info("success: " + s);}

    public static void logSuccess ()                 {logSuccess("success");}

    @SuppressWarnings ("unchecked")
    public static void logSuccess (Object object) {
        String output;
        if (object instanceof PaginatedScanList p) {
            List<String> strings = new ArrayList<>();
            p.forEach(o -> strings.add(o.toString()));
            output = "\n" + String.join("\n", strings);
        } else {
            output = object.toString();
        }

        logSuccess(output);
    }

}
