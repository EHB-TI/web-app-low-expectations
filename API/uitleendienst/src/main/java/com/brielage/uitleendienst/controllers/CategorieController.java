package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping (value = "/categorie")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping ("/addCategorie")
    public Categorie addCategorie (@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @GetMapping ("/findCategorie/{id}")
    public Optional<Categorie> findCategorieById (@PathVariable String id) {
        return categorieRepository.findById(id);
    }
}
