package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
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
@RequestMapping (value = "/magazijn")
public class MagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository magazijnRepository;

    @PostMapping ("/add")
    public String add (@RequestBody Magazijn magazijn)
            throws
            JsonProcessingException {
        APILogger.logRequest("magazijn.add", magazijn.toString());
        Map fouten = new LinkedHashMap();

        if (magazijn.getNaam()
                    .isEmpty()) fouten.put("naam_leeg", "");
        if (magazijn.getAdres()
                    .isEmpty()) fouten.put("adres_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Magazijn m = magazijnRepository.save(magazijn);
                return APIResponse.respondMagazijn(m);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
