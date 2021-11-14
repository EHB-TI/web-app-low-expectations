package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Categorie;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface CategorieRepository
        extends CrudRepository<Categorie, String> {
    List<Categorie> findAll();

    Optional<Categorie> findById (String id);

    Optional<Categorie> findByNaam (String naam);
}
