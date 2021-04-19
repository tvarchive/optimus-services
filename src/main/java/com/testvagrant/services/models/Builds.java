package com.testvagrant.services.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document
@Getter @Setter
public class Builds {
    @Id private String id;
    private String runMode = "Distribution";
    private LocalDateTime buildStartTime = LocalDateTime.now();
    private LocalDateTime buildEndTime = LocalDateTime.now();
    private int buildScenarios = 0;
    private int buildSuccessRate = 0;
    private int scenariosCount = 0;
    private String scenarioSuccessRate = "0.0";
    private String crashlytics ="";
    private LocalDateTime created_date = LocalDateTime.now();
    private boolean complete = false;
}
