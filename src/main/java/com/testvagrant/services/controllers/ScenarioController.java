package com.testvagrant.services.controllers;

import com.mongodb.client.AggregateIterable;
import com.testvagrant.services.models.Scenarios;
import com.testvagrant.services.repositories.ScenarioRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    @RequestMapping("/scenarios/distinct")
    public List<Document> distinctScenarios() {
        List<Bson> bsons = Arrays.asList(
                project(fields(include("scenarioName", "dataRowNumber","status","created_date"), excludeId()))

        );
        AggregateIterable<Document> scenarios1 = mongoTemplate.getCollection("scenarios").aggregate(bsons);
        List<Document> documents = new ArrayList<>();
        scenarios1.iterator().forEachRemaining(document -> {
            if(!isADuplicateDocument(documents, document)) {
                documents.add(document);
            }
        });
        ArrayList<Document> status = documents.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(documen -> documen.getDate("created_date")))),
                ArrayList::new));
//        List<Document> collect = documencuments.stream().re().collect(Collectors.toList());
        return status;
    }

    private boolean isADuplicateDocument(List<Document> documents, Document document) {
        return documents.stream().anyMatch(document1 -> {
            boolean b = document1.get("scenarioName").equals(document.get("scenarioName")) && document1.get("dataRowNumber").equals(document.get("dataRowNumber"));
            return b;
        });
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
