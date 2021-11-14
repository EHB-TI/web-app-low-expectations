package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Rol;
import com.brielage.uitleendienst.repositories.RolRepository;
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
@RequestMapping (value = "/rol")
public class RolController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private RolRepository rolRepository;

    @PostMapping ("/add")
    public String add (@RequestBody Rol rol)
            throws
            JsonProcessingException {
        APILogger.logRequest("rol.add", rol.toString());
        Map fouten = new LinkedHashMap();

        if (rol.getNaam()
               .isEmpty()) fouten.put("naam_leeg", "");
        if (rol.getOmschrijving()
               .isEmpty()) fouten.put("omschrijving_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Rol r = rolRepository.save(rol);
                return APIResponse.respondRol(r);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
