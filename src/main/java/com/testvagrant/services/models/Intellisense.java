package com.testvagrant.services.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Intellisense {

    @Id private String id;
    @JsonSerialize(using = ToStringSerializer.class) private ObjectId buildId;
    private String exceptionsense;
    private Date created_date = new Date();

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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
