package com.testvagrant.services.controllers;

import com.testvagrant.services.models.Devices;
import com.testvagrant.services.repositories.DevicesRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {

    private MongoTemplate mongoTemplate;
    private DevicesRepository devicesRepository;

    public DeviceController(MongoTemplate mongoTemplate, DevicesRepository devicesRepository) {
        this.mongoTemplate = mongoTemplate;
        this.devicesRepository = devicesRepository;
    }

    @PostMapping("/devices/findMatchingDevice")
    public Devices findMatchingDevice(@RequestParam("buildId")ObjectId buildId, @RequestBody Devices devices) {
        Query query = getDeviceQuery(devices);
        query.addCriteria(Criteria.where("buildId").is(buildId).and("status").is("Available"));
        return mongoTemplate.findAndModify(query, Update.update("status","Engaged"),Devices.class);
    }

    private Query getDeviceQuery(Devices devices) {
        Query query = new Query();
        if(!StringUtils.isEmpty(devices.getUdid())) {
            query.addCriteria(Criteria.where("udid").is(devices.getUdid()));
            return query;
        }
        if(!StringUtils.isEmpty(devices.getPlatform())) {
            query.addCriteria(Criteria.where("platform").is(devices.getPlatform()));
        }
        if(!StringUtils.isEmpty(devices.getDeviceName())) {
            query.addCriteria(Criteria.where("deviceName").is(devices.getDeviceName()));
        }

        if(!StringUtils.isEmpty(devices.getRunsOn()) && !devices.getRunsOn().equalsIgnoreCase("any")) {
            query.addCriteria(Criteria.where("runsOn").is(devices.getRunsOn()));
        }

        if(!StringUtils.isEmpty(devices.getPlatformVersion())) {
            query.addCriteria(Criteria.where("platformVersion").is(devices.getPlatformVersion()));
        }
        return query;
    }
}
