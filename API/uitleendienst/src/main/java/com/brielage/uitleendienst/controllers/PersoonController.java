package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.PersoonRepository;
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
@RequestMapping (value = "/persoon")
public class PersoonController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository persoonRepository;

    @PostMapping ("/add")
    public String add (@RequestBody Persoon persoon)
            throws
            JsonProcessingException {
        APILogger.logRequest("persoon.add", persoon.toString());
        Map fouten = new LinkedHashMap();

        if (persoon.getVoornaam()
                   .isEmpty()) fouten.put("voornaam_leeg", "");
        if (persoon.getFamilienaam()
                   .isEmpty()) fouten.put("familienaam_leeg", "");
        if (persoon.getEmail()
                   .isEmpty()) fouten.put("email_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Persoon p = persoonRepository.save(persoon);
                return APIResponse.respondPersoon(p);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
