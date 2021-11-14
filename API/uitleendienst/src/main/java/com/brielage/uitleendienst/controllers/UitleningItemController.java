package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
import com.brielage.uitleendienst.responses.APIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping (value = "/uitleningItem")
public class UitleningItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningItemRepository uitleningItemRepository;

    @PostMapping ("/add")
    public String add (@RequestBody UitleningItem uitleningItem)
            throws
            JsonProcessingException {
        APILogger.logRequest("uitleningItem.add", uitleningItem.toString());
        Map fouten = new LinkedHashMap();

        if (uitleningItem.getItem()
                         .getNaam()
                         .isEmpty()) fouten.put("uitleenbaarItem_naam_leeg", "");

        if (fouten.isEmpty()) {
            try {
                UitleningItem ui = uitleningItemRepository.save(uitleningItem);
                return APIResponse.respondUitleningItem(ui);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
