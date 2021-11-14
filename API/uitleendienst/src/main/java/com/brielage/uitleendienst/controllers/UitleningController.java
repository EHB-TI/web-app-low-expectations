package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.repositories.UitleningRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/uitlening")
public class UitleningController {
    Logger logger =
            LoggerFactory.getLogger(UitleningController.class.getName());

    @Autowired
    private UitleningRepository uitleningRepository;

    @PostMapping("/add")
    public Uitlening add (@RequestBody Uitlening uitlening) {
        Uitlening u = uitleningRepository.save(uitlening);
        logger.info(u.getId());
        return u;
    }
}
