package com.testvagrant.services.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Document
@Getter @Setter
public class Scenarios {
    @Id private String id;
    private String featureName = "Sample Feature";
    private String scenarioName = "Sample Scenario";
    private Integer dataRowNumber = 0;
    private Integer location = 0;
    private String[] tags = new String[] {"tag1", "tag2"};
    private LocalDateTime startTime = LocalDateTime.now();
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId buildId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId deviceId;
    private String status = "passed";
    private Boolean completed = false;
    private LocalDateTime endTime = LocalDateTime.now();
    private Integer timeTaken = 0;
  private String scenarioTimeline =
      "{\"interval\":\"1\", \"cpuData\":{\"user\":\"10\", \"Kernel\":\"20\"}}, \"memoryData\":{\"total\":\"10.0\", \"actual\":\"20.0\"}}, \"activity\":\"\", \"screenshotFileName\":\"\", \"screenshotData\":[0]}";
  private String steps =
      "[{\"status\":\"passed\", \"keyword\":\"test\", \"name\":\"sample test step\", \"error_message\":\"\", \"duration\":\"0\"}]";
    private byte[] failedOnScreen = new byte[]{0};
    private String stacktrace = "";
    private String activity = "";
    private Date created_date = new Date();


    @Override
    public String toString() {
        return "{"
                + "\"id\":\"" + id + "\""
                + ", \"featureName\":\"" + featureName + "\""
                + ", \"scenarioName\":\"" + scenarioName + "\""
                + ", \"dataRowNumber\":\"" + dataRowNumber + "\""
                + ", \"location\":\"" + location + "\""
                + ", \"tags\":" + Arrays.toString(tags)
                + ", \"startTime\":" + startTime
                + ", \"buildId\":" + buildId
                + ", \"deviceId\":" + deviceId
                + ", \"status\":\"" + status + "\""
                + ", \"completed\":\"" + completed + "\""
                + ", \"endTime\":" + endTime
                + ", \"timeTaken\":\"" + timeTaken + "\""
                + ", \"scenarioTimeline\":\"" + scenarioTimeline + "\""
                + ", \"steps\":\"" + steps + "\""
                + ", \"failedOnScreen\":" + Arrays.toString(failedOnScreen)
                + ", \"stacktrace\":\"" + stacktrace + "\""
                + ", \"activity\":\"" + activity + "\""
                + ", \"created_date\":" + created_date
                + "}}";
    }
}
