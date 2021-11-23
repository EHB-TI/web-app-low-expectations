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

    List<Persoon> findAllByVoornaamIsIn (List<String> voornaam);

    List<Persoon> findAllByFamilienaamIsIn (List<String> familienaam);

    List<Persoon> findAllByAdresIsIn (List<String> adres);

    List<Persoon> findAllByTelefoonIsIn (List<String> telefoon);

    List<Persoon> findAllByEmailIsIn (List<String> email);
}
