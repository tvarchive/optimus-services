package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Intellisense;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "intellisense", path = "intellisense")
public interface IntellisenseRepository extends MongoRepository<Intellisense,String> {

    Long removeByBuildId(@Param("buildId") ObjectId buildId);
}
