package com.testvagrant.services.controllers;

import com.mongodb.client.AggregateIterable;
import com.testvagrant.services.models.DistinctScenarios;
import com.testvagrant.services.models.Scenarios;
import com.testvagrant.services.repositories.ScenarioRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Projections.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


@RestController
public class ScenarioController {

    private MongoTemplate mongoTemplate;
    private ScenarioRepository scenarioRepository;


    public ScenarioController(MongoTemplate mongoTemplate, ScenarioRepository scenarioRepository) {
        this.mongoTemplate = mongoTemplate;
        this.scenarioRepository = scenarioRepository;
    }


    @PostMapping("/scenarios/insert")
    public Scenarios insertScenario(@RequestBody Scenarios scenarios) {
    System.out.println(scenarios);
        if(scenarios.getBuildId() ==null && scenarios.getDeviceId() == null ) {
            return scenarios;
        }
        return scenarioRepository.insert(scenarios);
    }

    @PostMapping("/scenarios/insert/bulk")
    public List<Scenarios> insertScenario(@RequestBody List<Scenarios> scenarios) {
        return scenarioRepository.insert(scenarios);
    }

    @RequestMapping("/scenarios/distinct")
    public List<Scenarios> distinctScenarios() {
        List<Bson> bsons = Arrays.asList(
                project(fields(include("scenarioName", "dataRowNumber","status"), excludeId()))

        );
        AggregateIterable<Document> scenarios1 = mongoTemplate.getCollection("scenarios").aggregate(bsons);
        List<Scenarios> documents = new ArrayList<>();
        documents = StreamSupport.stream(scenarios1.spliterator(), true).map(document -> {
            Scenarios scenarios = new Scenarios();
            scenarios.setScenarioName(document.getString("scenarioName"));
            scenarios.setDataRowNumber(document.getInteger("dataRowNumber"));
            scenarios.setStatus(document.getString("status"));
            scenarios.setCreated_date(document.getDate("created_date"));
            return scenarios;
        }).collect(Collectors.toList());
        ArrayList<Scenarios> filteredDocuments = documents
                .stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(Scenarios::getScenarioName))),
                ArrayList::new));
        return filteredDocuments;
    }

    @RequestMapping("/scenarios/distinct/count")
    public DistinctScenarios distinctScenariosCount() {
        List<Scenarios> filteredDocuments = distinctScenarios();
        DistinctScenarios distinctScenarios = new DistinctScenarios();
        distinctScenarios.setDistinctScenariosCount(filteredDocuments.size());
        int passedScenariosCount = filteredDocuments.stream().filter(scenario -> scenario.getStatus().equalsIgnoreCase("passed")).collect(Collectors.toList()).size();
        float pass_percentage = (passedScenariosCount*100.0f)/filteredDocuments.size();
        DecimalFormat df = new DecimalFormat("#.0");
        String passRate = df.format(pass_percentage);
        distinctScenarios.setPassPercentage(passRate);
        return distinctScenarios;
    }


    @RequestMapping("/scenarios/distinctDeviceId")
    public List<Scenarios> distinctdeviceIdsOfScenario(@RequestParam("buildId")ObjectId buildId) {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group(Fields.fields("deviceId","buildId","activity")));
        AggregationResults<Scenarios> scenarios = mongoTemplate.aggregate(aggregation, "scenarios", Scenarios.class);
        try {
            return scenarios.getMappedResults().stream().filter(scenarios1 -> scenarios1.getBuildId().equals(buildId)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<Scenarios>();
        }
    }



}
