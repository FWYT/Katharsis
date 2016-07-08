package com.example.helloworld.endpoints.resource;

import io.katharsis.resource.annotations.*;

/**
 * Created by vagrant on 7/7/16.
 */


@JsonApiResource(type = "section")
public class Section {

    public Section(String section, int count)
    {
        this.section = section;
        this.gapCount = count;
    }

    @JsonApiId
    @JsonApiLookupIncludeAutomatically
    private String section;

    //@JsonApiToMany(lazy = false)
    @JsonApiIncludeByDefault
    @JsonApiLookupIncludeAutomatically
    @JsonApiToOne
    private int gapCount;

    public String getSection()
    {
        return section;
    }

    public int getGapCount()
    {
        return gapCount;
    }


}


