
package com.brielage.uitleendienst.authorization;

import com.brielage.uitleendienst.tools.APILogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public enum JWTChecker {
    ;

    private static final Base64.Decoder decoder      = Base64.getUrlDecoder();
    private static final ObjectMapper   objectMapper = new ObjectMapper();

    @SuppressWarnings ("unused")
    public static void log (String token) {
        if (!checkTokenForLog(token)) return;

        APILogger.logResult(token);

        try {
            APILogger.logResult(getHeader(token).toString());
            APILogger.logResult(getPayload(token).toString());
        } catch (Exception e) {APILogger.logException(e.getMessage());}
    }

    private static boolean checkTokenForLog (String token) {
        if (token == null || token.isEmpty()) {
            APILogger.logFail("EMPTY TOKEN");
            return false;
        }

        return true;
    }

    @SuppressWarnings ("BooleanMethodIsAlwaysInverted")
    public static boolean checkToken (String token) {
        if (token == null || token.isEmpty()) {
            APILogger.logFail("EMPTY TOKEN");
            return false;
        }

        try {
            // get header but don't do anything with it,
            // just to get Exception if the header is invalid
            getHeader(token);

            String tokenUse = getPayload(token).get("token_use")
                                               .toString()
                                               .replaceAll("\"", "");

            if (tokenUse.equals("access")) {
                APILogger.logSuccess("token checked");
                return true;
            }
        } catch (Exception e) {
            APILogger.logFail(e.getMessage());
            return false;
        }

        APILogger.logFail("token invalid");
        return false;
    }

    @SuppressWarnings ("BooleanMethodIsAlwaysInverted")
    public static boolean checkPermission (
            String token,
            Permission permission) {
        List<String> groups = getGroups(token);

        if (groups == null || groups.isEmpty() || permission == null) return false;

        APILogger.logSuccess("is in group " + permission.group);
        return groups.contains(permission.group);
    }

    public static boolean checkUsername (
            String token,
            String username) {
        return Objects.equals(getUsername(token), username);
    }

    private static JsonNode getHeader (String token)
            throws
            JsonProcessingException {
        return objectMapper.readTree(new String(decoder.decode(token.split("\\.")[0])));
    }

    private static JsonNode getPayload (String token)
            throws
            JsonProcessingException {
        return objectMapper.readTree(new String(decoder.decode(token.split("\\.")[1])));
    }

    private static List<String> getGroups (String token) {
        try {
            List<String> groups    = new ArrayList<>();
            ArrayNode    arrayNode = (ArrayNode) getPayload(token).get("cognito:groups");

            for (final JsonNode jsonNode : arrayNode)
                groups.add(jsonNode.toString()
                                   .replaceAll("\"", ""));

            return groups;
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return null;
        }
    }

    public static String getUsername (String token) {
        try {
            return getPayload(token).get("username")
                                    .toString()
                                    .replaceAll("\"", "");
        } catch (JsonProcessingException e) {
            APILogger.logException(e.getMessage());
            return null;
        }
    }
}
