package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/categorie")
public class CategorieController {
    Logger logger =
            LoggerFactory.getLogger(CategorieController.class.getName());

    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping ("/add")
    public Categorie add (@RequestBody Categorie categorie) {
        Categorie c = categorieRepository.save(categorie);
        logger.info(c.getNaam());
        return c;
    }

    @GetMapping ("/findById/{id}")
    public Optional<Categorie> findById (@PathVariable String id) {
        logger.info("\nrequest categorie by id " + id);
        Optional<Categorie> c = categorieRepository.findById(id);
        c.ifPresent(categorie -> logger.info("naam: " + categorie.getNaam()));
        return c;
    }

    @GetMapping ("/findByNaam/{naam}")
    public Optional<Categorie> findByNaam (@PathVariable String naam){
        logger.info("\nrequest categorie by naam " + naam);
        Optional<Categorie> c = categorieRepository.findByNaam(naam);
        c.ifPresent(categorie -> logger.info("naam: " + categorie.getNaam()));
        return c;
    }

    @GetMapping ("/findAll")
    public List<Categorie> findAll(){
        logger.info("\nrequest all categories");
        List<Categorie> c = categorieRepository.findAll();
        for (Categorie categorie : c) {
            logger.info("naam: " + categorie.getNaam());
        }
        return c;
    }
}
