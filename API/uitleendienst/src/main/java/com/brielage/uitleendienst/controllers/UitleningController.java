package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.repositories.UitleningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/uitlening")
public class UitleningController {
    @Autowired
    private UitleningRepository uitleningRepository;

    @PostMapping ("/add")
    public Uitlening add (@RequestBody Uitlening uitlening) {
        APILogger.logRequest("uitlening.add", uitlening.toString());
        Uitlening u = uitleningRepository.save(uitlening);
        APILogger.logResult(u.toString());
        return u;
    }
}
