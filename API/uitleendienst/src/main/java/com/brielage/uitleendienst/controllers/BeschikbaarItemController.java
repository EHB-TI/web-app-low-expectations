package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.repositories.BeschikbaarItemRepository;
import com.brielage.uitleendienst.responses.APIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping (value = "/beschikbaarItem")
public class BeschikbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private BeschikbaarItemRepository beschikbaarItemRepository;

    @PostMapping ("/add")
    public String add (@RequestBody BeschikbaarItem beschikbaarItem)
            throws
            JsonProcessingException {
        APILogger.logRequest("beschikbaarItem.add", beschikbaarItem.toString());
        Map fouten = new LinkedHashMap();

        if (beschikbaarItem.getUitleenbaarItem() == null) fouten.put("geen_uitleenbaarItem", "");
        if (beschikbaarItem.getMagazijn() == null) fouten.put("geen_magazijn", "");
        if (beschikbaarItem.getAantalTotaal() == null || beschikbaarItem.getAantalTotaal() < 0)
            fouten.put("ongeldig_aantalTotaal", String.valueOf(beschikbaarItem.getAantalTotaal()));
        if (beschikbaarItem.getAantalBeschikbaar() == null || beschikbaarItem.getAantalBeschikbaar() < 0)
            fouten.put("ongeldig_aantalBeschikbaar",
                       String.valueOf(beschikbaarItem.getAantalBeschikbaar()));
        if (beschikbaarItem.getAantalGereserveerd() == null || beschikbaarItem.getAantalGereserveerd() < 0)
            fouten.put("ongeldig_aantalGereserveerd",
                       String.valueOf(beschikbaarItem.getAantalGereserveerd()));

        if (fouten.isEmpty()) {
            try {
                BeschikbaarItem bi = beschikbaarItemRepository.save(beschikbaarItem);
                return APIResponse.respondBeschikbaarItem(bi);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
