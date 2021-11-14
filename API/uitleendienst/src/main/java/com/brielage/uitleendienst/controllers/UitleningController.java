package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.repositories.UitleningRepository;
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
@RequestMapping (value = "/uitlening")
public class UitleningController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningRepository uitleningRepository;

    @PostMapping ("/add")
    public String add (@RequestBody Uitlening uitlening)
            throws
            JsonProcessingException {
        APILogger.logRequest("uitlening.add", uitlening.toString());
        Map fouten = new LinkedHashMap();

        if (uitlening.getStart() == null) fouten.put("start_leeg", "");
        if (uitlening.getEind() == null) fouten.put("eind_leeg", "");
        if (uitlening.getStart()
                     .after(uitlening.getEind())) fouten.put(
                "startdatum_later_dan_einddatum",
                uitlening.getStart() + "; " + uitlening.getEind());
        if (uitlening.getTeruggebrachtOp() != null && uitlening.getTeruggebrachtOp()
                                                               .before(uitlening.getStart()))
            fouten.put("teruggebrachtdatum_vroeger_dan_startdatum",
                       uitlening.getTeruggebrachtOp() + "; " + uitlening.getStart());

        if (fouten.isEmpty()) {
            try {
                Uitlening u = uitleningRepository.save(uitlening);
                return APIResponse.respondUitlening(u);
            } catch (Exception e) {APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
