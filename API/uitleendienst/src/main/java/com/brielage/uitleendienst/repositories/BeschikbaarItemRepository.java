package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.BeschikbaarItem;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface BeschikbaarItemRepository extends CrudRepository<BeschikbaarItem, String> {
    List<BeschikbaarItem> findAll();

    Optional<BeschikbaarItem> findById (String id);
}
