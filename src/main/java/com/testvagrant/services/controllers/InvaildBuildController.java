package com.testvagrant.services.controllers;

import com.testvagrant.services.models.Builds;
import com.testvagrant.services.repositories.BuildsRepository;
import com.testvagrant.services.repositories.DevicesRepository;
import com.testvagrant.services.repositories.IntellisenseRepository;
import com.testvagrant.services.repositories.ScenarioRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvaildBuildController {

    private BuildsRepository buildsRepository;
    private ScenarioRepository scenarioRepository;
    private DevicesRepository devicesRepository;
    private IntellisenseRepository intellisenseRepository;

    public InvaildBuildController(BuildsRepository buildsRepository, ScenarioRepository scenarioRepository, DevicesRepository devicesRepository, IntellisenseRepository intellisenseRepository) {
        this.buildsRepository = buildsRepository;
        this.scenarioRepository = scenarioRepository;
        this.devicesRepository = devicesRepository;
        this.intellisenseRepository = intellisenseRepository;
    }

    @DeleteMapping("/deleteCorruptedData")
    public String deleteCorruptedData() {
        List<Builds> allBuildsByScenarioCount = buildsRepository.findAllBuildsByScenariosCount(0);
        allBuildsByScenarioCount.forEach(build -> {
            scenarioRepository.removeByBuildId(new ObjectId(build.getId()));
            devicesRepository.removeByBuildId(new ObjectId(build.getId()));
            intellisenseRepository.removeByBuildId(new ObjectId(build.getId()));
        } );
        buildsRepository.removeByScenariosCount(0);
        return "OK";
    }

}
