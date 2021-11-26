package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Uitlening;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScan
public interface UitleningRepository extends CrudRepository<Uitlening, String> {
    List<Uitlening> findAll();

    Optional<Uitlening> findById (String id);

    List<Uitlening> findAllByOrganisatieId (String id);

    List<Uitlening> findAllByMagazijnId (String id);

    List<Uitlening> findAllByStart (Date start);

    List<Uitlening> findAllByEind (Date eind);

    List<Uitlening> findAllByTeruggebrachtOp (Date teruggebrachtOp);
}
