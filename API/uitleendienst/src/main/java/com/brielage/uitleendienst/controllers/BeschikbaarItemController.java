package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.repositories.BeschikbaarItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/beschikbaarItem")
public class BeschikbaarItemController {
    Logger logger =
            LoggerFactory.getLogger(BeschikbaarItemController.class.getName());

    @Autowired
    private BeschikbaarItemRepository beschikbaarItemRepository;

    @PostMapping("/add")
    public BeschikbaarItem add (@RequestBody BeschikbaarItem beschikbaarItem) {
        BeschikbaarItem bi = beschikbaarItemRepository.save(beschikbaarItem);
        logger.info(bi.getId());
        return bi;
    }
}
