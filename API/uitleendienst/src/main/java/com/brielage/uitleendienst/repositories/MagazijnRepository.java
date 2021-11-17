package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Magazijn;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface MagazijnRepository extends CrudRepository<Magazijn, String> {
    List<Magazijn> findAll();

    Optional<Magazijn> findById (String id);

    Optional<Magazijn> findByNaam (String naam);

    Optional<Magazijn> findByAdres (String adres);

    Optional<Magazijn> findByTelefoon (String telefoon);

    Optional<Magazijn> findByEmail (String email);
}
