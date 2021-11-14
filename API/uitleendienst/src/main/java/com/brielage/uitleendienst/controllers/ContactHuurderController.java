package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.ContactHuurder;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.ContactHuurderRepository;
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
@RequestMapping (value = "/contactHuurder")
public class ContactHuurderController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ContactHuurderRepository contactHuurderRepository;

    @PostMapping ("/add")
    public String add (@RequestBody ContactHuurder contactHuurder)
            throws
            JsonProcessingException {
        APILogger.logRequest("contactHuurder.add", contactHuurder.toString());
        Map fouten = new LinkedHashMap();

        if (contactHuurder.getPersoon() == null) fouten.put("geen_persoon", "");
        else {
            Persoon p = contactHuurder.getPersoon();
            if (p.getNaam()
                 .isEmpty()) fouten.put("naam_ongeldig", p.getNaam());
            if (p.getEmail()
                 .isEmpty()) fouten.put("email_ongeldig", p.getEmail());
        }

        if (fouten.isEmpty()) {
            try {
                ContactHuurder ch = contactHuurderRepository.save(contactHuurder);
                return APIResponse.respondContactHuurder(ch);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
