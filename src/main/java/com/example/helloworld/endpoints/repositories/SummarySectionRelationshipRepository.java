package com.example.helloworld.endpoints.repositories;

import com.example.helloworld.endpoints.queries.SummaryQuery;
import com.example.helloworld.endpoints.resource.Section;
import com.example.helloworld.endpoints.resource.Summary;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;

import java.util.UUID;

/**
 * Created by vagrant on 7/7/16.
 */
public class SummarySectionRelationshipRepository implements RelationshipRepository<Summary, UUID, Section, String> {

    private SummaryRepository summaryRepo = new SummaryRepository();
    private SectionRepository sectionRepo = new SectionRepository();
    private SummaryQuery repo = new SummaryQuery();

    @Override
    public void setRelation(Summary summary, String section, String fieldName)
    {

    }

    @Override
    public void setRelations(Summary summary, Iterable<String> section, String fieldName)
    {

    }

    @Override
    public void addRelations(Summary summary, Iterable<String> section, String fieldName)
    {

    }


    @Override
    public void removeRelations(Summary summary, Iterable<String> section, String fieldName)
    {

    }

    @Override
    public Section findOneTarget(UUID id, String fieldName, QueryParams qp)
    {
        return null;
    }

    @Override
    public Iterable<Section> findManyTargets(UUID id, String fieldName, QueryParams qp)
    {
        System.out.println("FIND MANY TARGETS");
        return repo.getSections(id);
    }


}
