package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Organisatie;
import com.brielage.uitleendienst.repositories.OrganisatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/organisatie")
public class OrganisatieController {
    @Autowired
    private OrganisatieRepository organisatieRepository;

    @PostMapping ("/add")
    public Organisatie add (@RequestBody Organisatie organisatie) {
        APILogger.logRequest("organisatie.add", organisatie.toString());
        Organisatie o = organisatieRepository.save(organisatie);
        APILogger.logResult(o.toString());
        return o;
    }
}
