package com.brielage.uitleendienst.tools;

import com.brielage.uitleendienst.responses.JsonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum APILogger {
    INSTANCE;

    private static final Logger       logger       = LoggerFactory.getLogger(
            APILogger.class.getName());
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void logResult (String s) {logger.info("result: " + s);}

    public static void logRequest (
            String s,
            String j) {logger.info("request " + s + ": " + j);}

    public static void logRequest (String s) {logger.info("request " + s);}

    public static void logJsonResponse (JsonResponse jsonResponse)
            throws
            JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(jsonResponse));
        logger.info("response: " + jsonNode.toPrettyString());
    }
}
