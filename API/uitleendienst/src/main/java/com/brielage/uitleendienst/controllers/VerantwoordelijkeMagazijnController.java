package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import com.brielage.uitleendienst.repositories.VerantwoordelijkeMagazijnRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "verantwoordelijkeMagazijn")
public class VerantwoordelijkeMagazijnController {
    Logger logger =
            LoggerFactory.getLogger(VerantwoordelijkeMagazijnController.class.getName());

    @Autowired
    private VerantwoordelijkeMagazijnRepository verantwoordelijkeMagazijnRepository;

    @PostMapping("/add")
    public VerantwoordelijkeMagazijn add (@RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn) {
        VerantwoordelijkeMagazijn vm = verantwoordelijkeMagazijnRepository.save(verantwoordelijkeMagazijn);
        logger.info(vm.getId());
        return vm;
    }
}
