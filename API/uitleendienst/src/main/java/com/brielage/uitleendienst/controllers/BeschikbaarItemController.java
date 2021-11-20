package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.repositories.BeschikbaarItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping (value = "/beschikbaarItem")
public class BeschikbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private BeschikbaarItemRepository beschikbaarItemRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<BeschikbaarItem> findAll() {
        APILogger.logRequest("beschikbaarItem.findAll");
        return beschikbaarItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("beschikbaarItem.findById", id);
        Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

        if (b.isPresent())
            return ResponseEntity.ok().body(b.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody BeschikbaarItem beschikbaarItem) {
        APILogger.logRequest("beschikbaarItem.create", beschikbaarItem.toString());
        try {
            if (!validateBeschikbaarItem(beschikbaarItem))
                return ResponseEntity.badRequest().build();

            BeschikbaarItem b = beschikbaarItemRepository.save(beschikbaarItem);

            return new ResponseEntity(b, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody BeschikbaarItem beschikbaarItem) {
        APILogger.logRequest("beschikbaarItem.put", id);
        try {
            if (!validateBeschikbaarItemId(beschikbaarItem))
                return ResponseEntity.badRequest().build();

            Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

            if (b.isEmpty())
                return ResponseEntity.notFound().build();

            beschikbaarItem.setId(b.get().getId());
            BeschikbaarItem result = beschikbaarItemRepository.save(beschikbaarItem);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("beschikbaarItem.delete", id);
        try {
            Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

            if (b.isEmpty())
                return ResponseEntity.badRequest().build();

            beschikbaarItemRepository.delete(b.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateBeschikbaarItemId(BeschikbaarItem b) {
        if (b.getId().isEmpty())
            return false;
        return validateBeschikbaarItem(b);
    }

    private boolean validateBeschikbaarItem(BeschikbaarItem b) {
        return b.getMagazijn() != null
                && b.getUitleenbaarItem() != null
                && b.getAantalTotaal() != null
                && b.getAantalBeschikbaar() != null
                && b.getAantalGereserveerd() != null;
    }
}
