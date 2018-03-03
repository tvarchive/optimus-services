package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Scenarios;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "scenarios", path = "scenarios")
public interface ScenarioRepository extends MongoRepository<Scenarios, String> {

    Scenarios findByBuildIdAndScenarioName(@Param("buildId") ObjectId buildId, @Param("scenarioName") String scenarioName);

    Long countByBuildIdAndScenarioName(@Param("buildId") ObjectId buildId, @Param("scenarioName") String scenarioName);

    Scenarios findByBuildIdAndScenarioNameAndLocationAndDeviceId(@Param("buildId") ObjectId buildId, @Param("scenarioName") String scenarioName, @Param("location") Integer location, @Param("deviceId") ObjectId deviceId);

    Long countByBuildId(@Param("buildId") ObjectId buildId);

    Long countByBuildIdAndStatus(@Param("buildId") ObjectId buildId, @Param("status") String status);

    List<Scenarios> findByBuildIdAndDeviceIdAndActivity(@Param("buildId") ObjectId buildId, @Param("deviceId") ObjectId deviceId, @Param("activity") String activity);
}
