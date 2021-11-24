package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Rol;
import com.brielage.uitleendienst.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/rol")
public class RolController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private RolRepository rolRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Rol> findAll() {
        APILogger.logRequest("rol.findAll");
        return rolRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("rol.findById", id);
        Optional<Rol> r = rolRepository.findById(id);

        if (r.isPresent())
            return ResponseEntity.ok().body(r.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody Rol rol) {
        APILogger.logRequest("rol.create", rol.toString());
        try {
            if (!validateRol(rol))
                return ResponseEntity.badRequest().build();

            Rol r = rolRepository.save(rol);

            return new ResponseEntity(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody Rol rol) {
        APILogger.logRequest("rol.put", id);
        try {
            if (!validateRolId(rol))
                return ResponseEntity.badRequest().build();

            Optional<Rol> r = rolRepository.findById(id);

            if (r.isEmpty())
                return ResponseEntity.notFound().build();

            rol.setId(r.get().getId());
            Rol result = rolRepository.save(rol);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("rol.delete", id);
        try {
            Optional<Rol> r = rolRepository.findById(id);

            if (r.isEmpty())
                return ResponseEntity.badRequest().build();

            rolRepository.delete(r.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateRolId(Rol r) {
        if (r.getId().isEmpty())
            return false;
        return validateRol(r);
    }

    private boolean validateRol(Rol r) {
        return !r.getNaam().isEmpty();
    }
}
