package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Rol;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface RolRepository
        extends CrudRepository<Rol, String> {
    List<Rol> findAll ();

    Optional<Rol> findById (String id);

    Optional<Rol> findByNaam (String naam);

    List<Rol> findAllByNaamIsIn (List<String> naam);
}
