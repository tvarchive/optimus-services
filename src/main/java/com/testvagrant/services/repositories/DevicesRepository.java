package com.testvagrant.services.repositories;

import com.testvagrant.services.models.Devices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "devices", path = "devices")
public interface DevicesRepository extends MongoRepository<Devices,String> {

}
