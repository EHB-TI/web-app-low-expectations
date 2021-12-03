package com.brielage.uitleendienst.authorization;

import com.brielage.uitleendienst.tools.APILogger;

import java.util.List;

public class OriginChecker {
    private static List<String> allowedOrigins;

    public OriginChecker (List<String> allowedOrigins) {
        OriginChecker.allowedOrigins = allowedOrigins;
    }

    public static boolean checkOrigin (String origin) {
        APILogger.logRequest("origin", origin);
        return allowedOrigins.contains(origin);
    }
}
