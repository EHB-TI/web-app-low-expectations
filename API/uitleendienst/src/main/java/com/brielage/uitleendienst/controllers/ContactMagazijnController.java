package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.ContactMagazijn;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.ContactMagazijnRepository;
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
@RequestMapping (value = "/contactMagazijn")
public class ContactMagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ContactMagazijnRepository contactMagazijnRepository;

    @PostMapping ("/add")
    public String add (@RequestBody ContactMagazijn contactMagazijn)
            throws
            JsonProcessingException {
        APILogger.logRequest("contactMagazijn.add", contactMagazijn.toString());
        Map fouten = new LinkedHashMap();

        if (contactMagazijn.getPersoon() == null) fouten.put("geen_persoon", "");
        else {
            Persoon p = contactMagazijn.getPersoon();
            if (p.getVoornaam()
                 .isEmpty()) fouten.put("voornaam_ongeldig", p.getVoornaam());
            if (p.getFamilienaam()
                    .isEmpty()) fouten.put("familienaam_ongeldig", p.getFamilienaam());
            if (p.getEmail()
                 .isEmpty()) fouten.put("email_ongeldig", p.getEmail());
        }
        if (contactMagazijn.getMagazijn() == null) fouten.put("geen_magazijn", "");

        if (fouten.isEmpty()) {
            try {
                ContactMagazijn cm = contactMagazijnRepository.save(contactMagazijn);
                return APIResponse.respondContactMagazijn(cm);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
