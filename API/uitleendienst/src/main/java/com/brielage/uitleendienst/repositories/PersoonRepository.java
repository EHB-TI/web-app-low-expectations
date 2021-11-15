package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Persoon;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface PersoonRepository
        extends CrudRepository<Persoon, String> {
    List<Persoon> findAll ();

    Optional<Persoon> findById (String id);

    Optional<Persoon> findByVoornaam (String voornaam);

    Optional<Persoon> findByFamilienaam (String familienaam);

}
