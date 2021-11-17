package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();

    Optional<User> findById (String id);

    Optional<User> findByUsername (String username);

    Optional<User> findByPersoonId (String id);

    List<User> findAllByRolId (String id);
}
