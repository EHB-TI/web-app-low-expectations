package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Uitlening;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface UitleningRepository extends CrudRepository<Uitlening, String> {
    List<Uitlening> findAll();

    Optional<Uitlening> findById (String id);
}