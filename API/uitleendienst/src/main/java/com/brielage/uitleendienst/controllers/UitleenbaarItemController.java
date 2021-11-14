package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
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
@RequestMapping (value = "/uitleenbaarItem")
public class UitleenbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;

    @PostMapping ("/add")
    public String add (@RequestBody UitleenbaarItem uitleenbaarItem)
            throws
            JsonProcessingException {
        APILogger.logRequest(uitleenbaarItem.toString());
        Map fouten = new LinkedHashMap();

        if (uitleenbaarItem.getNaam()
                           .isEmpty()) fouten.put("naam_leeg", "");
        if (uitleenbaarItem.getCategorie() == null) fouten.put("categorie_leeg", "");
        else {
            Categorie c = uitleenbaarItem.getCategorie();
            if (c.getNaam()
                 .isEmpty()) fouten.put("categorie_naam_leeg", "");
            if (c.getOmschrijving()
                 .isEmpty()) fouten.put("categorie_omschrijving_leeg", "");
        }

        if (fouten.isEmpty()) {
            try {
                UitleenbaarItem ui = uitleenbaarItemRepository.save(uitleenbaarItem);
                return APIResponse.respondUitleenbaarItem(ui);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
