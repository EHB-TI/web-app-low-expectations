package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface VerantwoordelijkeMagazijnRepository extends CrudRepository<VerantwoordelijkeMagazijn, String> {
    List<VerantwoordelijkeMagazijn> findAll();

    Optional<VerantwoordelijkeMagazijn> findById (String id);

    Optional<VerantwoordelijkeMagazijn> findByPersoonId (String id);

    List<VerantwoordelijkeMagazijn> findAllByMagazijnId (String id);

    List<VerantwoordelijkeMagazijn> findAllByPersoondIdIsIn (List<String> id);

    List<VerantwoordelijkeMagazijn> findAllByMagazijnIdIsIn (List<String> id);

    Optional<VerantwoordelijkeMagazijn> findByMagazijnIdAndPersoonId (String magazijnId, String persoonId);
}
