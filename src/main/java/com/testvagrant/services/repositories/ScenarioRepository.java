package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Scenarios;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "scenarios", path = "scenarios")
public interface ScenarioRepository extends MongoRepository<Scenarios,String> {

    Scenarios findByBuildIdAndScenarioName(@Param("buildId")ObjectId buildId, @Param("scenarioName") String scenarioName);

    Long countByBuildIdAndScenarioName(@Param("buildId")ObjectId buildId, @Param("scenarioName") String scenarioName);

    Scenarios findByBuildIdAndScenarioNameAndLocation(@Param("buildId")ObjectId buildId, @Param("scenarioName") String scenarioName, @Param("location")Integer location);
}
