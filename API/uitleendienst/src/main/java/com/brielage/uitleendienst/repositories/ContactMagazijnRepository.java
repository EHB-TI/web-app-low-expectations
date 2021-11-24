package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.ContactMagazijn;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface ContactMagazijnRepository
        extends CrudRepository<ContactMagazijn, String> {
    List<ContactMagazijn> findAll ();

    Optional<ContactMagazijn> findById (String id);

    List<ContactMagazijn> findAllByPersoonIdIsIn (List<String> persoonIds);

    List<ContactMagazijn> findAllByPersoonId (String persoonId);

    List<ContactMagazijn> findAllByMagazijnIdIsIn (List<String> magazijnIds);

    List<ContactMagazijn> findAllByMagazijnId (String magazijnId);

    Optional<ContactMagazijn> findByMagazijnIdAndPersoonId (
            String magazijnId,
            String persoonId);
}
