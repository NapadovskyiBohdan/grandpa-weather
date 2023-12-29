package com.grandpaweather.repository;


import com.grandpaweather.domain.Trigger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TriggerRepository extends MongoRepository<Trigger, String> {

    Optional<Trigger> getTriggerByName(String name);

    Optional<Trigger> getTriggerById(String id);

    List<Trigger> getTriggersByNameIn(List<String> names);

}
