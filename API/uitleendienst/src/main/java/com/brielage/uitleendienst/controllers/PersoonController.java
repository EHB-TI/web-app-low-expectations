package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/persoon")
public class PersoonController {
    @Autowired
    private PersoonRepository persoonRepository;

    @PostMapping ("/add")
    public Persoon add (@RequestBody Persoon persoon) {
        APILogger.logRequest("persoon.add", persoon.toString());
        Persoon p = persoonRepository.save(persoon);
        APILogger.logResult(p.toString());
        return p;
    }
}
