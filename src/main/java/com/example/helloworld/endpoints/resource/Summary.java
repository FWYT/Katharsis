package com.example.helloworld.endpoints.resource;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */

@JsonApiResource(type = "summary")
public class Summary {

    public Summary(UUID id, String aisle, int gapCount)
    {
        this.id = id;
        this.aisle = aisle;
        this.gapCount = gapCount;
    }

    @JsonApiId
    private UUID id;

    private String aisle;

    private int gapCount;

    public UUID getId()
    {
        return id;
    }

    public String getAisle()
    {
        return aisle;
    }

    public int getGapCount()
    {
        return gapCount;
    }


}
