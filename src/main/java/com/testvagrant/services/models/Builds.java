package com.testvagrant.services.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Builds {

    @Id private String id;
    private Date buildStartTime;
    private Date buildEndTime;
    private int buildScenarios;
    private int buildSuccessRate;
    private int scenariosCount;
    private String scenarioSuccessRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBuildStartTime() {
        return buildStartTime;
    }

    public void setBuildStartTime(Date buildStartTime) {
        this.buildStartTime = buildStartTime;
    }

    public Date getBuildEndTime() {
        return buildEndTime;
    }

    public void setBuildEndTime(Date buildEndTime) {
        this.buildEndTime = buildEndTime;
    }

    public int getScenariosCount() {
        return scenariosCount;
    }

    public void setScenariosCount(int scenariosCount) {
        this.scenariosCount = scenariosCount;
    }

    public int getBuildScenarios() {
        return buildScenarios;
    }

    public void setBuildScenarios(int buildScenarios) {
        this.buildScenarios = buildScenarios;
    }

    public int getBuildSuccessRate() {
        return buildSuccessRate;
    }

    public void setBuildSuccessRate(int buildSuccessRate) {
        this.buildSuccessRate = buildSuccessRate;
    }

    public String getScenarioSuccessRate() {
        return scenarioSuccessRate;
    }

    public void setScenarioSuccessRate(String scenarioSuccessRate) {
        this.scenarioSuccessRate = scenarioSuccessRate;
    }

}
