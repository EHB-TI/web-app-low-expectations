package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum APIResponse {
    ;
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
        if (success) return output(new JsonResponse(true));
        if (reden.isEmpty()) return output(new JsonResponse(false));

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
        return output(jr);
    }

    public static String respondCategorie (Categorie categorie)
            throws
            JsonProcessingException {
        return output(new JsonCategorieResponse(true, categorie));
    }

    public static String respondCategorie (List categories)
            throws
            JsonProcessingException {
        return output(new JsonCategorieResponse(true, categories));
    }

    public static String respondBeschikbaarItem (BeschikbaarItem beschikbaarItem)
            throws
            JsonProcessingException {
        return output(new JsonBeschikbaarItemResponse(true, beschikbaarItem));
    }

    public static String respondContactHuurder (ContactHuurder contactHuurder)
            throws
            JsonProcessingException {
        return output(new JsonContactHuurderResponse(true, contactHuurder));
    }

    public static String respondContactHuurder (List contactHuurders)
            throws
            JsonProcessingException {
        return output(new JsonContactHuurderResponse(true, contactHuurders));
    }

    public static String respondContactMagazijn (ContactMagazijn contactMagazijn)
            throws
            JsonProcessingException {
        return output(new JsonContactMagazijnResponse(true, contactMagazijn));
    }

    public static String respondContactMagazijn (List contactMagazijns)
            throws
            JsonProcessingException {
        return output(new JsonContactMagazijnResponse(true, contactMagazijns));
    }

    public static String respondMagazijn (Magazijn magazijn)
            throws
            JsonProcessingException {
        return output(new JsonMagazijnResponse(true, magazijn));
    }

    public static String respondMagazijn (List magazijns)
            throws
            JsonProcessingException {
        return output(new JsonMagazijnResponse(true, magazijns));
    }

    public static String respondOrganisatie (Organisatie organisatie)
            throws
            JsonProcessingException {
        return output(new JsonOrganisatieResponse(true, organisatie));
    }

    public static String respondOrganisatie (List organisaties)
            throws
            JsonProcessingException {
        return output(new JsonOrganisatieResponse(true, organisaties));
    }

    private static String output (JsonResponse jsonResponse)
            throws
            JsonProcessingException {
        APILogger.logJsonResponse(jsonResponse);
        return objectMapper.writeValueAsString(jsonResponse);
    }
}
