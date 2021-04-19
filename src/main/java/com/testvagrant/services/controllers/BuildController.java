package com.testvagrant.services.controllers;

import com.testvagrant.services.models.Builds;
import com.testvagrant.services.repositories.BuildsRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class BuildController {

    private MongoTemplate mongoTemplate;
    private BuildsRepository buildsRepository;

    public BuildController(MongoTemplate mongoTemplate, BuildsRepository buildsRepository) {
        this.mongoTemplate = mongoTemplate;
        this.buildsRepository = buildsRepository;
    }

    @RequestMapping("/builds/new")
    public Builds createANewBuild() {
        Builds newBuild = new Builds();
        return buildsRepository.insert(newBuild);
    }
}
