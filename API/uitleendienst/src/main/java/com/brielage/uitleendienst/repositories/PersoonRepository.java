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

    List<Persoon> findAllByVoornaam (String voornaam);

    List<Persoon> findAllByFamilienaam (String familienaam);

    Optional<Persoon> findByAdres (String adres);

    Optional<Persoon> findByTelefoon (String telefoon);

    Optional<Persoon> findByEmail (String email);
}
