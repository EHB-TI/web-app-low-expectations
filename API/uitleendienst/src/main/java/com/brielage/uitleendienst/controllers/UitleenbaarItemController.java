package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping (value = "/uitleenbaarItem")
public class UitleenbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<UitleenbaarItem> findAll() {
        APILogger.logRequest("uitleenbaarItem.findAll");
        return uitleenbaarItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("uitleenbaarItem.findById", id);
        Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok().body(u.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody UitleenbaarItem uitleenbaarItem) {
        APILogger.logRequest("uitleenbaarItem.create", uitleenbaarItem.toString());
        try {
            if (!validateUitleenbaarItem(uitleenbaarItem))
                return ResponseEntity.badRequest().build();

            UitleenbaarItem u = uitleenbaarItemRepository.save(uitleenbaarItem);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody UitleenbaarItem uitleenbaarItem) {
        APILogger.logRequest("uitleenbaarItem.put", id);
        try {
            if (!validateUitleenbaarItemId(uitleenbaarItem))
                return ResponseEntity.badRequest().build();

            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound().build();

            uitleenbaarItem.setId(u.get().getId());
            UitleenbaarItem result = uitleenbaarItemRepository.save(uitleenbaarItem);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("uitleenbaarItem.delete", id);
        try {
            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest().build();

            uitleenbaarItemRepository.delete(u.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateUitleenbaarItemId(UitleenbaarItem u) {
        if (u.getId().isEmpty())
            return false;
        return validateUitleenbaarItem(u);
    }

    private boolean validateUitleenbaarItem(UitleenbaarItem u) {
        return !u.getNaam().isEmpty()
                && u.getEenheid() < 0
                && u.getPrijs() < 0
                && u.getPeriode() != null
                && u.getCategorie() != null;
    }
}
