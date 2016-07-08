package com.example.helloworld.endpoints.repositories;

import com.example.helloworld.endpoints.resource.Latest;
import com.example.helloworld.endpoints.resource.Section;
import com.example.helloworld.endpoints.resource.Summary;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;

import java.util.UUID;

/**
 * Created by vagrant on 7/8/16.
 */

@JsonApiRelationshipRepository(source = Latest.class, target = Summary.class)
public class LatestSummaryRelationshipRepository {

    public LatestSummaryRelationshipRepository() {

    }

    @JsonApiSetRelation
    public void setRelation(Latest latest, UUID locationId, String fieldName) {

    }

    @JsonApiAddRelations
    public void addRelations(Latest source, Iterable<UUID> targetUuids, String fieldName) {

    }

    @JsonApiRemoveRelations
    public void removeRelations(Latest source, Iterable<UUID> targetUuids, String fieldName) {

    }


    @JsonApiFindOneTarget
    public Summary findOneTarget(UUID sourceId, String fieldName, QueryParams requestParams) {

        return null;
    }
}
