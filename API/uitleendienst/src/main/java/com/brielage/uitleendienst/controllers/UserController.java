package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.User;
import com.brielage.uitleendienst.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<User> findAll() {
        APILogger.logRequest("user.findAll");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("user.findById", id);
        Optional<User> u = userRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok().body(u.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody User user) {
        APILogger.logRequest("user.create",user.toString());
        try {
            if (!validateUser(user))
                return ResponseEntity.badRequest().build();

            User u = userRepository.save(user);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody User user) {
        APILogger.logRequest("user.put", id);
        try {
            if (!validateUserId(user))
                return ResponseEntity.badRequest().build();

            Optional<User> u = userRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound().build();

            user.setId(u.get().getId());
            User result = userRepository.save(user);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("user.delete", id);
        try {
            Optional<User> u = userRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest().build();

            userRepository.delete(u.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateUserId(User u) {
        if (u.getId().isEmpty())
            return false;
        return validateUser(u);
    }

    private boolean validateUser(User u) {
        return !u.getUsername().isEmpty()
                && !u.getPassword().isEmpty()
                && u.getRol() != null
                && u.getPersoon() != null;
    }
}
