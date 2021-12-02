
package com.brielage.uitleendienst.authorization;

import com.brielage.uitleendienst.tools.APILogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public enum JWTChecker {
    ;

    private static final Base64.Decoder decoder      = Base64.getUrlDecoder();
    private static final ObjectMapper   objectMapper = new ObjectMapper();

    public static void log (String token) {
        if (token == null || token.isEmpty()) {
            APILogger.logResult("EMPTY TOKEN");
            return;
        }

        APILogger.logResult(token);

        try {
            String[] chunks = token.split("\\.");

            String header  = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));

            APILogger.logResult(header);
            APILogger.logResult(payload);
            JsonNode jsonPayload = objectMapper.readTree(payload);
            APILogger.logResult(jsonPayload.get("cognito:groups" +
                                                        "")
                                           .toString());
        } catch (Exception e) {APILogger.logException(e.getMessage());}
    }
}
