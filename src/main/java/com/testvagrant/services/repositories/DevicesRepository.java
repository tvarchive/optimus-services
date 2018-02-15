package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Devices;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "devices", path = "devices")
public interface DevicesRepository extends MongoRepository<Devices,String> {

     Devices findByBuildIdAndUdid(@Param("buildId") ObjectId buildId, @Param("udid") String udid);

     List<Devices> findAllByBuildId(@Param("buildId") ObjectId buildId);

     Long removeByBuildId(@Param("buildId") ObjectId buildId);
}
