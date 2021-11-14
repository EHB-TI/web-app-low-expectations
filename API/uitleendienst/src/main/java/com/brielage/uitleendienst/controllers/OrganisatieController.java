package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Organisatie;
import com.brielage.uitleendienst.repositories.OrganisatieRepository;
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
@RequestMapping (value = "/organisatie")
public class OrganisatieController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private OrganisatieRepository organisatieRepository;

    @PostMapping ("/add")
    public String add (@RequestBody Organisatie organisatie)
            throws
            JsonProcessingException {
        APILogger.logRequest("organisatie.add", organisatie.toString());
        Map fouten = new LinkedHashMap();

        if (organisatie.getNaam()
                       .isEmpty()) fouten.put("naam_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Organisatie o = organisatieRepository.save(organisatie);
                return APIResponse.respondOrganisatie(o);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
