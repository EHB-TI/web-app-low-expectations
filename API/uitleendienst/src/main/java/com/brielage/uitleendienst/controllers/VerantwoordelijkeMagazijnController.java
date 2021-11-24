package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import com.brielage.uitleendienst.repositories.VerantwoordelijkeMagazijnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "verantwoordelijkeMagazijn")
public class VerantwoordelijkeMagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private VerantwoordelijkeMagazijnRepository verantwoordelijkeMagazijnRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<VerantwoordelijkeMagazijn> findAll() {
        APILogger.logRequest("verantwoordelijkeMagazijn.findAll");
        return verantwoordelijkeMagazijnRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("verantwoordelijkeMagazijn.findById", id);
        Optional<VerantwoordelijkeMagazijn> vm = verantwoordelijkeMagazijnRepository.findById(id);

        if (vm.isPresent())
            return ResponseEntity.ok().body(vm.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn) {
        APILogger.logRequest("verantwoordelijkeMagazijn.create", verantwoordelijkeMagazijn.toString());
        try {
            if (!validateVerantwoordelijkeMagazijn(verantwoordelijkeMagazijn))
                return ResponseEntity.badRequest().build();

            VerantwoordelijkeMagazijn vm = verantwoordelijkeMagazijnRepository.save(verantwoordelijkeMagazijn);

            return new ResponseEntity(vm, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn) {
        APILogger.logRequest("verantwoordelijkeMagazijn.put", id);
        try {
            if (!validateVerantwoordelijkeMagazijnId(verantwoordelijkeMagazijn))
                return ResponseEntity.badRequest().build();

            Optional<VerantwoordelijkeMagazijn> vm = verantwoordelijkeMagazijnRepository.findById(id);

            if (vm.isEmpty())
                return ResponseEntity.notFound().build();

            verantwoordelijkeMagazijn.setId(vm.get().getId());
            VerantwoordelijkeMagazijn result = verantwoordelijkeMagazijnRepository.save(verantwoordelijkeMagazijn);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("verantwoordelijkeMagazijn.delete", id);
        try {
            Optional<VerantwoordelijkeMagazijn> vm = verantwoordelijkeMagazijnRepository.findById(id);

            if (vm.isEmpty())
                return ResponseEntity.badRequest().build();

            verantwoordelijkeMagazijnRepository.delete(vm.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateVerantwoordelijkeMagazijnId(VerantwoordelijkeMagazijn vm) {
        if (vm.getId().isEmpty())
            return false;
        return validateVerantwoordelijkeMagazijn(vm);
    }

    private boolean validateVerantwoordelijkeMagazijn(VerantwoordelijkeMagazijn vm) {
        return vm.getPersoon() != null
                && vm.getMagazijn() != null;
    }
}
