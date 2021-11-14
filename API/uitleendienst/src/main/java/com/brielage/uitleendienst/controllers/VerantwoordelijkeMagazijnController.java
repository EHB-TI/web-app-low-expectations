package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import com.brielage.uitleendienst.repositories.VerantwoordelijkeMagazijnRepository;
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
@RequestMapping (value = "verantwoordelijkeMagazijn")
public class VerantwoordelijkeMagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private VerantwoordelijkeMagazijnRepository verantwoordelijkeMagazijnRepository;

    @PostMapping ("/add")
    public String add (@RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn)
            throws
            JsonProcessingException {
        APILogger.logRequest("verantwoordelijkeMagazijn.add", verantwoordelijkeMagazijn.toString());
        Map fouten = new LinkedHashMap();

        if (verantwoordelijkeMagazijn.getMagazijn()
                                     .getNaam()
                                     .isEmpty())
            fouten.put("magazijn_naam_leeg", "");
        if (verantwoordelijkeMagazijn.getPersoon() == null)
            fouten.put("persoon_leeg", "");
        else {
            Persoon p = verantwoordelijkeMagazijn.getPersoon();
            if (p.getVoornaam()
                 .isEmpty()) fouten.put("persoon_voornaam_leeg", "");
            if (p.getFamilienaam()
                 .isEmpty()) fouten.put("persoon_familienaam_leeg", "");
            if (p.getEmail()
                 .isEmpty()) fouten.put("persoon_email_leeg", "");
        }

        if (fouten.isEmpty()) {
            try {
                VerantwoordelijkeMagazijn vm = verantwoordelijkeMagazijnRepository.save(
                        verantwoordelijkeMagazijn);
                return APIResponse.respondVerantwoordelijkeMagazijn(vm);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
