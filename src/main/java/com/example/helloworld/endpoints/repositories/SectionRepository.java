package com.example.helloworld.endpoints.repositories;

import com.example.helloworld.endpoints.queries.LatestQuery;
import com.example.helloworld.endpoints.resource.Latest;
import com.example.helloworld.endpoints.resource.Section;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;

import java.util.UUID;

/**
 * Created by vagrant on 7/7/16.
 */
@JsonApiResourceRepository(Section.class)
public class SectionRepository {


    @JsonApiSave
    public <S extends Latest> S save(S entity) {
        return null;
    }

    @JsonApiFindOne
    public Latest findOne(UUID id) {
        System.out.println("FIND ONE SECTION");
        return null;
    }

    @JsonApiFindAll
    public Iterable<Latest> findAll(QueryParams queryParams) {
        System.out.println("FIND ALL SECTION");
        return null;
    }

    @JsonApiFindAllWithIds
    public Iterable<Latest> findAll(Iterable<Long> iterable, QueryParams queryParams) {
        System.out.println("FIND ALL SECTION WITH IDS");
        return null;
    }

    @JsonApiDelete
    public void delete(Long id) {
    }
}
