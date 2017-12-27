package com.testvagrant.services.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Intellisense {

    @Id private String id;
    @JsonSerialize(using = ToStringSerializer.class) private ObjectId buildId;
    private String exceptionsense;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getBuildId() {
        return buildId;
    }

    public void setBuildId(ObjectId buildId) {
        this.buildId = buildId;
    }

    public String getExceptionsense() {
        return exceptionsense;
    }

    public void setExceptionsense(String exceptionsense) {
        this.exceptionsense = exceptionsense;
    }
}
