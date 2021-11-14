package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.repositories.BeschikbaarItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/beschikbaarItem")
public class BeschikbaarItemController {
    @Autowired
    private BeschikbaarItemRepository beschikbaarItemRepository;

    @PostMapping ("/add")
    public BeschikbaarItem add (@RequestBody BeschikbaarItem beschikbaarItem) {
        APILogger.logRequest("beschikbaarItem.add", beschikbaarItem.toString());
        BeschikbaarItem bi = beschikbaarItemRepository.save(beschikbaarItem);
        APILogger.logResult(bi.toString());
        return bi;
    }
}
