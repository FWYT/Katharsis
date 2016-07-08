package com.example.helloworld.endpoints.resource;

import io.katharsis.resource.annotations.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */

@JsonApiResource(type = "summary")
public class Summary {

    public Summary(UUID id, String aisle, int gapCount, List<Section> sections)
    {
        this.id = id;
        this.aisle = aisle;
        this.totalGaps = gapCount;
        this.sections = sections;
    }

    @JsonApiId
    private UUID id;

    private String aisle;

    private int totalGaps;

    /*private int gapCount;
    private String section;
    public int getGapCount()
    {
        return gapCount;
    }
    public String getSection()
    {
        return section;
    }*/

    //@JsonApiToMany(lazy = false)
    //@JsonApiIncludeByDefault
    //@JsonApiLookupIncludeAutomatically
    private List<Section> sections;

    public UUID getId()
    {
        return id;
    }

    public String getAisle()
    {
        return aisle;
    }

    public int getTotalGaps()
    {
        return totalGaps;
    }

    public List<Section> getSections()
    {
        return sections;
    }


}
