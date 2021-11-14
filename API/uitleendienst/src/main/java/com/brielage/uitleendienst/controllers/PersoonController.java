package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.responses.APIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping (value = "/findById/{id}")
    public String findById(@PathVariable String id) throws JsonProcessingException {
        APILogger.logRequest("persoon.findById", id);
        Map fouten = new LinkedHashMap();

        if (id.isEmpty()) fouten.put("id_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Optional<Persoon> p = persoonRepository.findById(id);
                if (p.isPresent()) return APIResponse.respondPersoon(p.get());
                return APIResponse.respond(false, "geen_persoon_gevonden");
            } catch (Exception e){
                return APIResponse.respond(false, e.getMessage());
            }
        }

        return APIResponse.respondErrors(fouten);
    }

    @GetMapping ("/findAll")
    public String findAll () throws JsonProcessingException {
        APILogger.logRequest("persoon.findAll");
        List<Persoon> p = persoonRepository.findAll();
        if (p.isEmpty()) return APIResponse.respond(false, "geen_persoon_gevonden");
        return APIResponse.respondPersoon(p);
    }
}
