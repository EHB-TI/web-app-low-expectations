package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.UitleningItem;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScan
public interface UitleningItemRepository extends CrudRepository<UitleningItem, String> {
    List<UitleningItem> findAll();

    Optional<UitleningItem> findById (String id);

    List<UitleningItem> findAllByUitleningId (String uitleningId);

    List<UitleningItem> findAllByUitleenbaarItemId (String uitleenbaarItemId);

    List<UitleningItem> findAllByUitleenbaarItemIdIsIn (List<String> uitleenbaarItemId);

    List<UitleningItem> findAllByUitleningIdIsIn (List<String> uitleningId);

    Optional<UitleningItem> findByUitleenbaarItemIdAndUitleningId (String uitleenbaarItemId, String uitleningId);
}
