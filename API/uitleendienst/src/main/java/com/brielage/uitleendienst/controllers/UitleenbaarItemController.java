package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/uitleenbaarItem")
public class UitleenbaarItemController {
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;

    @PostMapping ("/add")
    public UitleenbaarItem add (@RequestBody UitleenbaarItem uitleenbaarItem) {
        APILogger.logRequest(uitleenbaarItem.toString());
        UitleenbaarItem ui = uitleenbaarItemRepository.save(uitleenbaarItem);
        APILogger.logResult(ui.toString());
        return ui;
    }
}
