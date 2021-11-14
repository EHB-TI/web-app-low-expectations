package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
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
    @Autowired
    private MagazijnRepository magazijnRepository;

    @PostMapping("/add")
    public Magazijn add (@RequestBody Magazijn magazijn) {
        APILogger.logRequest("magazijn.add", magazijn.toString());
        Magazijn m = magazijnRepository.save(magazijn);
        APILogger.logResult(m.toString());
        return m;
    }
}
