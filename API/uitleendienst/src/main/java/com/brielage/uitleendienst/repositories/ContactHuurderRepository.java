package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.ContactHuurder;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface ContactHuurderRepository extends CrudRepository<ContactHuurder, String> {
    List<ContactHuurder> findAll();

    Optional<ContactHuurder> findById (String id);

    Optional<ContactHuurder> findByPersoonId (String id);

    List<ContactHuurder> findAllByOrganisatieId (String id);
}
