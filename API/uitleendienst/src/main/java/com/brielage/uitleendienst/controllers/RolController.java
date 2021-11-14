package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Rol;
import com.brielage.uitleendienst.repositories.RolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/rol")
public class RolController {
    Logger logger =
            LoggerFactory.getLogger(RolController.class.getName());

    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/add")
    public Rol add (@RequestBody Rol rol) {
        Rol r = rolRepository.save(rol);
        logger.info(r.getNaam());
        return r;
    }
}
