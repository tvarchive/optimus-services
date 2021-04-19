package com.testvagrant.services.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter @Setter
public class Devices {
    @Id private String id;
    private String platform = "Android";
    private String status = "Available";
    private String deviceName = "Sample Device Name";
    private String platformVersion = "10";
    private String udid = "123456";
    private String runsOn  = "Device";
    private Date created_date = new Date();
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId buildId;
    private byte[] screenshot = new byte[]{0};
}
