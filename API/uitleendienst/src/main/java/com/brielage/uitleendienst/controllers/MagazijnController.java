package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/magazijn")
public class MagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository magazijnRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Magazijn> findAll() {
        APILogger.logRequest("magazijn.findAll");
        return magazijnRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("magazijn.findById", id);
        Optional<Magazijn> m = magazijnRepository.findById(id);

        if (m.isPresent())
            return ResponseEntity.ok().body(m.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody Magazijn magazijn) {
        APILogger.logRequest("magazijn.create", magazijn.toString());
        try {
            if (!validateMagazijn(magazijn))
                return ResponseEntity.badRequest().build();

            Magazijn m = magazijnRepository.save(magazijn);

            return new ResponseEntity(m, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody Magazijn magazijn) {
        APILogger.logRequest("magazijn.put", id);
        try {
            if (!validateMagazijnId(magazijn))
                return ResponseEntity.badRequest().build();

            Optional<Magazijn> m = magazijnRepository.findById(id);

            if (m.isEmpty())
                return ResponseEntity.notFound().build();

            magazijn.setId(m.get().getId());
            Magazijn result = magazijnRepository.save(magazijn);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("magazijn.delete", id);
        try {
            Optional<Magazijn> m = magazijnRepository.findById(id);

            if (m.isEmpty())
                return ResponseEntity.badRequest().build();

            magazijnRepository.delete(m.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateMagazijnId(Magazijn m) {
        if (m.getId().isEmpty())
            return false;
        return validateMagazijn(m);
    }

    private boolean validateMagazijn(Magazijn m) {
        return !m.getNaam().isEmpty()
                && !m.getAdres().isEmpty()
                && !m.getTelefoon().isEmpty()
                && !m.getEmail().isEmpty();
    }
}
