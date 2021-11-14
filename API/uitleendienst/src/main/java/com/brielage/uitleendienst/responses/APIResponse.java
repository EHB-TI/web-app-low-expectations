package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Categorie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public enum APIResponse {
    INSTANCE;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String respond (boolean success)
            throws
            JsonProcessingException {
        return output(new JsonResponse(success));
    }

    public static String respond (
            boolean success,
            String reden)
            throws
            JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (reden.isEmpty()) return INSTANCE.output(new JsonResponse(false));

        Map fouten = new LinkedHashMap();
        fouten.put(reden, true);
        JsonResponse jr = new JsonResponse(false);
        jr.setErrors(fouten);

        return output(jr);
    }

    public static String respondErrors (Map fouten)
            throws
            JsonProcessingException {
        JsonResponse jr = new JsonResponse(false);
        jr.setErrors(fouten);
        return output(jr);}

    public static String respondCategorie (Categorie categorie)
            throws
            JsonProcessingException {
        JsonCategorieResponse jcr = new JsonCategorieResponse(true, categorie);
        APILogger.logResult(objectMapper.writeValueAsString(jcr));
        return output(jcr);
    }

    private static String output (JsonResponse jsonResponse)
            throws
            JsonProcessingException {
        APILogger.logJsonResponse(jsonResponse);
        return objectMapper.writeValueAsString(jsonResponse);
    }
}
