package com.example.helloworld.endpoints.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */

@JsonApiResource(type = "latest")
public class Latest implements Serializable {

    public Latest(UUID id, UUID missionId, String workAddress, String mission, String storeId, UUID retailerId, DateTime timeStamp,
                  String correlationId, String image, List<Double> transform, String changerStamp)
    {
        this.id = id;
        this.missionId = missionId;
        this.workAddress = workAddress;
        this.mission = mission;
        this.storeId = storeId;
        this.retailerId = retailerId;
        this.timeStamp = timeStamp;
        this.correlationId = correlationId;
        this.image = image;
        this.transform = transform;
        this.changerStamp = changerStamp;
    }

    @JsonApiId
    private UUID id;

    private UUID missionId;

    private String workAddress;

    private String mission;

    private String storeId;

    private UUID retailerId;

    private DateTime timeStamp;

    private String correlationId;

    private String image;

    private List<Double> transform;

    private String changerStamp;

    public UUID getId()
    {
        return id;
    }

    public UUID getMissionId()
    {
        return missionId;
    }

    public String getWorkAddress()
    {
        return workAddress;
    }

    public String getMission()
    {
        return mission;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public UUID getRetailerId()
    {
        return retailerId;
    }

    public DateTime getTimeStamp()
    {
        return timeStamp;
    }

    public String getCorrelationId()
    {
        return correlationId;
    }

    public String getImage()
    {
        return image;
    }

    public List<Double> getTransform()
    {
        return transform;
    }

    public String getChangerStamp()
    {
        return changerStamp;
    }
}
