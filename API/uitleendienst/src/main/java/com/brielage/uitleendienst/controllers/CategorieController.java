package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import com.brielage.uitleendienst.responses.APIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping (value = "/categorie")
public class CategorieController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping ("/add")
    public String add (@RequestBody Categorie categorie)
            throws
            JsonProcessingException {
        APILogger.logRequest("categorie.add", categorie.toString());
        Map fouten = new LinkedHashMap();

        if (categorie.getNaam()
                     .isEmpty()) fouten.put("naam_leeg", "");
        if (categorie.getOmschrijving()
                     .isEmpty()) fouten.put("omschrijving_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Categorie c = categorieRepository.save(categorie);
                return APIResponse.respondCategorie(c);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }

    @GetMapping ("/findById/{id}")
    public String findById (@PathVariable String id)
            throws
            JsonProcessingException {
        APILogger.logRequest("categorie.findById", id);
        Map fouten = new LinkedHashMap();

        if (id.isEmpty()) fouten.put("id_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Optional<Categorie> c = categorieRepository.findById(id);
                c.ifPresent(categorie -> APILogger.logResult(categorie.toString()));
                //noinspection OptionalGetWithoutIsPresent
                return APIResponse.respondCategorie(c.get());
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }

    @GetMapping ("/findByNaam/{naam}")
    public String findByNaam (@PathVariable String naam)
            throws
            JsonProcessingException {
        APILogger.logRequest("categorie.findByNaam", naam);
        Map fouten = new LinkedHashMap();

        if (naam.isEmpty()) fouten.put("naam_leeg", "");

        if (fouten.isEmpty()) {
            try {
                Optional<Categorie> c = categorieRepository.findByNaam(naam);
                c.ifPresent(categorie -> APILogger.logResult(categorie.toString()));
                //noinspection OptionalGetWithoutIsPresent
                return APIResponse.respondCategorie(c.get());
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }

    @GetMapping ("/findAll")
    public String findAll ()
            throws
            JsonProcessingException {
        APILogger.logRequest("categorie.findAll");
        List<Categorie> c = categorieRepository.findAll();
        for (Categorie categorie : c) APILogger.logResult(categorie.toString());
        return APIResponse.respondCategorie(c);
    }
}
