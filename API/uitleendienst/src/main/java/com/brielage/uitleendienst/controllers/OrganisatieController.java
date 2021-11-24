package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Organisatie;
import com.brielage.uitleendienst.repositories.OrganisatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/organisatie")
public class OrganisatieController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private OrganisatieRepository organisatieRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Organisatie> findAll() {
        APILogger.logRequest("organisatie.findAll");
        return organisatieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("organisatie.findById", id);
        Optional<Organisatie> c = organisatieRepository.findById(id);

        if (c.isPresent())
            return ResponseEntity.ok().body(c.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody Organisatie organisatie) {
        APILogger.logRequest("organisatie.create", organisatie.toString());
        try {
            if (!validateOrganisatie(organisatie))
                return ResponseEntity.badRequest().build();

            Organisatie o = organisatieRepository.save(organisatie);

            return new ResponseEntity(o, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody Organisatie organisatie) {
        APILogger.logRequest("organisatie.put", id);
        try {
            if (!validateOrganisatieId(organisatie))
                return ResponseEntity.badRequest().build();

            Optional<Organisatie> o = organisatieRepository.findById(id);

            if (o.isEmpty())
                return ResponseEntity.notFound().build();

            organisatie.setId(o.get().getId());
            Organisatie result = organisatieRepository.save(organisatie);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("organisatie.delete", id);
        try {
            Optional<Organisatie> o = organisatieRepository.findById(id);

            if (o.isEmpty())
                return ResponseEntity.badRequest().build();

            organisatieRepository.delete(o.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateOrganisatieId(Organisatie o) {
        if (o.getId().isEmpty())
            return false;
        return validateOrganisatie(o);
    }

    private boolean validateOrganisatie(Organisatie o) {
        return !o.getNaam().isEmpty()
                && o.getAdres().isEmpty()
                && o.getEmail().isEmpty()
                && o.getTelefoon().isEmpty();
    }
}
