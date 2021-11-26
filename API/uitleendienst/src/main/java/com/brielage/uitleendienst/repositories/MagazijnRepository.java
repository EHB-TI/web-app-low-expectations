package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Magazijn;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface MagazijnRepository
        extends CrudRepository<Magazijn, String> {
    List<Magazijn> findAll ();

    Optional<Magazijn> findById (String id);

    List<Magazijn> findAllByNaamIsIn (List<String> namen);

    List<Magazijn> findAllByEmailIsIn (List<String> email);
}
