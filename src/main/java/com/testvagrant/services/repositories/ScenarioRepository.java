package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Scenarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "scenarios", path = "scenarios")
public interface ScenarioRepository extends MongoRepository<Scenarios,String> {
}
