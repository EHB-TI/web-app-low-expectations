package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/categorie")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping ("/add")
    public Categorie add (@RequestBody Categorie categorie) {
        APILogger.logRequest("categorie.add", categorie.toString());
        Categorie c = categorieRepository.save(categorie);
        return c;
    }

    @GetMapping ("/findById/{id}")
    public Optional<Categorie> findById (@PathVariable String id) {
        APILogger.logRequest("categorie.findById", id);
        Optional<Categorie> c = categorieRepository.findById(id);
        c.ifPresent(categorie -> APILogger.logResult(categorie.toString()));
        return c;
    }

    @GetMapping ("/findByNaam/{naam}")
    public Optional<Categorie> findByNaam (@PathVariable String naam) {
        APILogger.logRequest("categorie.findByNaam", naam);
        Optional<Categorie> c = categorieRepository.findByNaam(naam);
        c.ifPresent(categorie -> APILogger.logResult(categorie.toString()));
        return c;
    }

    @GetMapping ("/findAll")
    public List<Categorie> findAll () {
        APILogger.logRequest("categorie.findAll");
        List<Categorie> c = categorieRepository.findAll();
        for (Categorie categorie : c) APILogger.logResult(categorie.toString());
        return c;
    }
}
