package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/uitleningItem")
public class UitleningItemController {
    @Autowired
    private UitleningItemRepository uitleningItemRepository;

    @PostMapping ("/add")
    public UitleningItem add (@RequestBody UitleningItem uitleningItem) {
        APILogger.logRequest("uitleningItem.add", uitleningItem.toString());
        UitleningItem ui = uitleningItemRepository.save(uitleningItem);
        APILogger.logResult(ui.toString());
        return ui;
    }
}
