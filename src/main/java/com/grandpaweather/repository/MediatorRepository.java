package com.grandpaweather.repository;

import com.grandpaweather.domain.Mediator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediatorRepository extends MongoRepository<Mediator, String> {

    List<Mediator> getMediatorByIdIn(List<String> ids);
    List<Mediator> getMediatorByMediatorIn(List<String> mediators);
}
