package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/persoon")
public class PersoonController {
    Logger logger =
            LoggerFactory.getLogger(PersoonController.class.getName());

    @Autowired
    private PersoonRepository persoonRepository;

    @PostMapping("/add")
    public Persoon add (@RequestBody Persoon persoon) {
        Persoon p = persoonRepository.save(persoon);
        logger.info(p.getNaam());
        return p;
    }
}
