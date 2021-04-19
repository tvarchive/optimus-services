package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Builds;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "builds", path = "builds")
public interface BuildsRepository extends MongoRepository<Builds,String> {
    List<Builds> findAllBuildsByOrderByBuildStartTimeAsc();

    List<Builds> findAllBuildsByOrderByBuildStartTimeDesc();

    Builds findById(@Param("id")ObjectId id);

    List<Builds> findAllBuildsByScenariosCount(@Param("scenariosCount") int scenariosCount);

    List<Builds> findAllBuildsByComplete(@Param("complete") boolean scenariosCount);

    Long removeByScenariosCount(@Param("scenariosCount") int scenariosCount);
}
