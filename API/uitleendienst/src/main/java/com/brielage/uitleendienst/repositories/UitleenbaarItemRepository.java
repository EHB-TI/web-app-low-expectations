package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface UitleenbaarItemRepository extends CrudRepository<UitleenbaarItem, String> {
    List<UitleenbaarItem> findAll();

    Optional<UitleenbaarItem> findById (String id);
}
