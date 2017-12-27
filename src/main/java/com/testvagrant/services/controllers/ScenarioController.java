package com.testvagrant.services.controllers;

import com.testvagrant.services.models.Scenarios;
import com.testvagrant.services.repositories.ScenarioRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ScenarioController {

    private MongoTemplate mongoTemplate;
    private ScenarioRepository scenarioRepository;


    public ScenarioController(MongoTemplate mongoTemplate, ScenarioRepository scenarioRepository) {
        this.mongoTemplate = mongoTemplate;
        this.scenarioRepository = scenarioRepository;
    }

    @RequestMapping("/scenarios/distinct")
    public List<Scenarios> distinctScenarios(@RequestParam("fieldName") String fieldName) {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group(Fields.fields("scenarioName","dataRowNumber","status")));
        AggregationResults<Scenarios> scenarios = mongoTemplate.aggregate(aggregation, "scenarios", Scenarios.class);
        return scenarios.getMappedResults();
    }

}
