package com.testvagrant.services.controllers;

import com.testvagrant.services.models.Scenarios;
import com.testvagrant.services.repositories.ScenarioRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ScenarioController {

    private MongoTemplate mongoTemplate;
    private ScenarioRepository scenarioRepository;


    public ScenarioController(MongoTemplate mongoTemplate, ScenarioRepository scenarioRepository) {
        this.mongoTemplate = mongoTemplate;
        this.scenarioRepository = scenarioRepository;
    }

    @RequestMapping("/scenarios/distinct")
    public List<Scenarios> distinctScenarios() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group(Fields.fields("scenarioName","dataRowNumber","status")));
        AggregationResults<Scenarios> scenarios = mongoTemplate.aggregate(aggregation, "scenarios", Scenarios.class);
        return scenarios.getMappedResults();
    }

    @RequestMapping("/scenarios/distinctUdid")
    public List<Scenarios> distinctUdidsOfScenario(@RequestParam("buildId")ObjectId buildId) {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group(Fields.fields("deviceUdid","buildId","activity")));
        AggregationResults<Scenarios> scenarios = mongoTemplate.aggregate(aggregation, "scenarios", Scenarios.class);
        try {
            return scenarios.getMappedResults().stream().filter(scenarios1 -> scenarios1.getBuildId().equals(buildId)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<Scenarios>();
        }
    }

}
