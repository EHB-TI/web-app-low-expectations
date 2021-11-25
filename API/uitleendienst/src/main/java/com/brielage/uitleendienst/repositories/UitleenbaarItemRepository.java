package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScan
public interface UitleenbaarItemRepository extends CrudRepository<UitleenbaarItem, String> {
    List<UitleenbaarItem> findAll();

    Optional<UitleenbaarItem> findById (String id);

    List<UitleenbaarItem> findAllByNaamIsIn (List<String> naam);

    List<UitleenbaarItem> findAllByPrijs (Float prijs);

    List<UitleenbaarItem> findAllByCategorieId (String id);

    List<UitleenbaarItem> findAllByCategorieIdIsIn (List<String> id);

    Optional<UitleenbaarItem> findByNaamAndCategorieId (String naam, String categorieId);
}
