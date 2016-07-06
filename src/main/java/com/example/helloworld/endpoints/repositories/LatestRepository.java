package com.example.helloworld.endpoints.repositories;

import com.example.helloworld.endpoints.queries.LatestQuery;
import com.example.helloworld.endpoints.resource.Latest;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by vagrant on 7/6/16.
 */

@JsonApiResourceRepository(Latest.class)
public class LatestRepository {

    private LatestQuery repo = new LatestQuery();

    @JsonApiSave
    public <S extends Latest> S save(S entity) {
        return null;
    }

    @JsonApiFindOne
    public Latest findOne(UUID id) {
        return repo.getLatest(id);
    }

    @JsonApiFindAll
    public Iterable<Latest> findAll(QueryParams queryParams) {
        return repo.getLatestAll();
    }

    @JsonApiFindAllWithIds
    public Iterable<Latest> findAll(Iterable<Long> iterable, QueryParams queryParams) {
        return null;
    }

    @JsonApiDelete
    public void delete(Long id) {
    }
}
