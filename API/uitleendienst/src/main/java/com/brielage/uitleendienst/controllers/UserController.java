package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.User;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.repositories.RolRepository;
import com.brielage.uitleendienst.repositories.UserRepository;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserRepository    userRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private RolRepository     rolRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository persoonRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> username,
            @RequestParam (required = false) List<String> rolId,
            @RequestParam (required = false) List<String> persoonId
                                           ) {
        if ((username == null || username.isEmpty())
                && (rolId == null || rolId.isEmpty())
                && (persoonId == null || persoonId.isEmpty())) {
            List<User> ret = userRepository.findAll();

            if (ret.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            return ResponseEntity.ok()
                                 .body(ret);
        }

        List<User> users = new ArrayList<>();

        if (username != null && !username.isEmpty())
            users.addAll(userRepository.findAllByUsernameIsIn(username));
        if (rolId != null && !rolId.isEmpty())
            users.addAll(userRepository.findAllByRolIdIsIn(rolId));
        if (persoonId != null && !persoonId.isEmpty())
            users.addAll(userRepository.findAllByPersoonIdIsIn(persoonId));

        users = RemoveDuplicates.removeDuplicates(users);

        if (users.isEmpty())
            return ResponseEntity.notFound()
                                 .build();

        return ResponseEntity.ok()
                             .body(users);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (@PathVariable String id) {
        Optional<User> u = userRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok()
                                 .body(u.get());

        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody User user) {
        try {
            if (!validateUser(user))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<User> optionalUser = userRepository.findByPersoonId(user.getPersoonId());

            if (optionalUser.isPresent())
                return ResponseEntity.badRequest()
                                     .build();

            User u = userRepository.save(user);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody User user) {
        try {
            if (!validateUserId(user))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<User> u = userRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            user.setId(u.get()
                        .getId());
            User result = userRepository.save(user);

            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        try {
            Optional<User> u = userRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest()
                                     .build();

            userRepository.delete(u.get());

            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    private boolean validateUserId (User u) {
        if (u.getId()
             .isEmpty())
            return false;
        return validateUser(u);
    }

    private boolean validateUser (User u) {
        return validateUsername(u.getUsername())
                && validateRolId(u.getRolId())
                && validatePersoonId(u.getPersoonId());
    }

    private boolean validateUsername (String username) {
        if (username == null || username.isEmpty()) return false;
        return !username.contains(" ");
    }

    private boolean validateRolId (String rolId) {
        if (rolId == null || rolId.isEmpty()) return false;
        return rolRepository.findById(rolId)
                            .isPresent();
    }

    private boolean validatePersoonId (String persoonId) {
        if (persoonId == null || persoonId.isEmpty()) return false;
        return persoonRepository.findById(persoonId)
                                .isPresent();
    }
}
