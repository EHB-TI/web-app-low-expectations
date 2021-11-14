package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/uitleenbaarItem")
public class UitleenbaarItemController {
    Logger logger =
            LoggerFactory.getLogger(UitleenbaarItemController.class.getName());

    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;

    @PostMapping("/add")
    public UitleenbaarItem add (@RequestBody UitleenbaarItem uitleenbaarItem) {
        UitleenbaarItem ui = uitleenbaarItemRepository.save(uitleenbaarItem);
        logger.info(ui.getNaam());
        return ui;
    }
}
