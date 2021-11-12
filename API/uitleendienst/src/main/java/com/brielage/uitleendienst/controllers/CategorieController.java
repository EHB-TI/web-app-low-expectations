package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping (value = "/categorie")
public class CategorieController {
    Logger logger =
            LoggerFactory.getLogger(CategorieController.class.getName());

    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping ("/addCategorie")
    public Categorie addCategorie (@RequestBody Categorie categorie) {
        Categorie c = categorieRepository.save(categorie);
        logger.info(c.getNaam());
        return c;
    }

    @GetMapping ("/findCategorie/{id}")
    public Optional<Categorie> findCategorieById (@PathVariable String id) {
        logger.info("\nrequest categorie by id " + id);
        Optional<Categorie> c = categorieRepository.findById(id);
        c.ifPresent(categorie -> logger.info("naam: " + categorie.getNaam()));
        return c;
    }
}
