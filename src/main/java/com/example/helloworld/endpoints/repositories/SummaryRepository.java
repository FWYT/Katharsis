package com.example.helloworld.endpoints.repositories;
import com.example.helloworld.endpoints.queries.LatestQuery;
import com.example.helloworld.endpoints.queries.SummaryQuery;
import com.example.helloworld.endpoints.resource.Latest;
import com.example.helloworld.endpoints.resource.Summary;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;

import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */

@JsonApiResourceRepository(Summary.class)
public class SummaryRepository {

    private SummaryQuery repo = new SummaryQuery();

    @JsonApiSave
    public <S extends Latest> S save(S entity) {
        return null;
    }

    @JsonApiFindOne
    public Summary findOne(UUID id) {
        return repo.getGapCount(id);
    }

    @JsonApiFindAll
    public Iterable<Summary> findAll(QueryParams queryParams) {
        return null;//repo.getGapCount();
    }

    @JsonApiFindAllWithIds
    public Iterable<Summary> findAll(Iterable<Long> iterable, QueryParams queryParams) {
        return null;
    }

    @JsonApiDelete
    public void delete(Long id) {
    }

}
