package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/magazijn")
public class MagazijnController {
    Logger logger =
            LoggerFactory.getLogger(MagazijnController.class.getName());

    @Autowired
    private MagazijnRepository magazijnRepository;

    @PostMapping("/add")
    public Magazijn add (@RequestBody Magazijn magazijn) {
        Magazijn m = magazijnRepository.save(magazijn);
        logger.info(m.getNaam());
        return m;
    }
}
