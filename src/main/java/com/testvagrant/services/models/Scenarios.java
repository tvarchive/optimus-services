package com.testvagrant.services.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Scenarios {

    @Id private String id;

    private String scenarioName;
    private Integer dataRowNumber = 0;
    private Integer location;
    private String deviceUdid;
    private String[] tags;
    private Date startTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId buildId;
    private String status = "failed";
    private Boolean completed = false;
    private Date endTime = new Date();
    private Integer timeTaken = 0;
    private String scenarioTimeline = "";
    private String steps = "";
    private byte[] failedOnScreen = new byte[]{};

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public Integer getDataRowNumber() {
        return dataRowNumber;
    }

    public void setDataRowNumber(Integer dataRowNumber) {
        this.dataRowNumber = dataRowNumber;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getDeviceUdid() {
        return deviceUdid;
    }

    public void setDeviceUdid(String deviceUdid) {
        this.deviceUdid = deviceUdid;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public ObjectId getBuildId() {
        return buildId;
    }

    public void setBuildId(ObjectId buildId) {
        this.buildId = buildId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getScenarioTimeline() {
        return scenarioTimeline;
    }

    public void setScenarioTimeline(String scenarioTimeline) {
        this.scenarioTimeline = scenarioTimeline;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFailedOnScreen() {
        return failedOnScreen;
    }

    public void setFailedOnScreen(byte[] failedOnScreen) {
        this.failedOnScreen = failedOnScreen;
    }
}
