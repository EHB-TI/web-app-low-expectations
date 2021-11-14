package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/uitleningItem")
public class UitleningItemController {
    Logger logger =
            LoggerFactory.getLogger(UitleningItemController.class.getName());

    @Autowired
    private UitleningItemRepository uitleningItemRepository;

    @PostMapping("/add")
    public UitleningItem add (@RequestBody UitleningItem uitleningItem) {
        UitleningItem ui = uitleningItemRepository.save(uitleningItem);
        logger.info(ui.getId());
        return ui;
    }
}
