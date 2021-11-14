package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Rol;
import com.brielage.uitleendienst.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/rol")
public class RolController {
    @Autowired
    private RolRepository rolRepository;

    @PostMapping ("/add")
    public Rol add (@RequestBody Rol rol) {
        APILogger.logRequest("rol.add", rol.toString());
        Rol r = rolRepository.save(rol);
        APILogger.logResult(r.toString());
        return r;
    }
}
