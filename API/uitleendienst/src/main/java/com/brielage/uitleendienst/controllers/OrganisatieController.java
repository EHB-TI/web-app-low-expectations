package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Organisatie;
import com.brielage.uitleendienst.repositories.OrganisatieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/organisatie")
public class OrganisatieController {
    Logger logger =
            LoggerFactory.getLogger(OrganisatieController.class.getName());

    @Autowired
    private OrganisatieRepository organisatieRepository;

    @PostMapping("/add")
    public Organisatie add (@RequestBody Organisatie organisatie) {
        Organisatie o = organisatieRepository.save(organisatie);
        logger.info(o.getId());
        return o;
    }
}
