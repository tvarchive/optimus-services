package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Builds;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "builds", path = "builds")
public interface BuildsRepository extends MongoRepository<Builds,String> {
    List<Builds> findAllBuildsByOrderByBuildStartTimeAsc();

    List<Builds> findAllBuildsByOrderByBuildStartTimeDesc();
}
