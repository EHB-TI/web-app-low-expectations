package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import com.brielage.uitleendienst.repositories.VerantwoordelijkeMagazijnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "verantwoordelijkeMagazijn")
public class VerantwoordelijkeMagazijnController {
    @Autowired
    private VerantwoordelijkeMagazijnRepository verantwoordelijkeMagazijnRepository;

    @PostMapping ("/add")
    public VerantwoordelijkeMagazijn add (@RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn) {
        APILogger.logRequest("verantwoordelijkeMagazijn.add", verantwoordelijkeMagazijn.toString());
        VerantwoordelijkeMagazijn vm = verantwoordelijkeMagazijnRepository.save(verantwoordelijkeMagazijn);
        APILogger.logResult(vm.toString());
        return vm;
    }
}
