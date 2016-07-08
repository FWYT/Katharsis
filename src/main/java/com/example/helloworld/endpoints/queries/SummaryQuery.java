package com.example.helloworld.endpoints.queries;

import com.example.helloworld.endpoints.resource.Section;
import com.example.helloworld.endpoints.resource.Summary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */
public class SummaryQuery {

    private Connection c;
    private Statement stmt;

    public SummaryQuery()
    {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/customer_db", "postgres", "postgres");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Summary getGapCount(UUID id)
    {
        try {
            stmt = c.createStatement();
            String sql = "select * from bnr.bnr_out_gaps where scan_id = '"+id.toString()+"' order by section asc";

            ResultSet rs = stmt.executeQuery(sql);

            Summary L = mapper(rs);

            return L;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Section> getSections(UUID id)
    {
        try {
            stmt = c.createStatement();
            String sql = "select * from bnr.bnr_out_gaps where scan_id = '"+id.toString()+"' order by section asc";

            System.out.println(sql + "\n\n");
            ResultSet rs = stmt.executeQuery(sql);

            List<Section> L = sectionMapper(rs);

            System.out.println(L);

            return L;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Section> sectionMapper(ResultSet rs)
    {
        int sTotal = 0;

        List<Section> sections = new ArrayList();
        String prevSection = "";
        //one aisle per scan id
        try {
            while (rs.next()) {

                String section = rs.getString("section");
                System.out.println(section);
                if (!prevSection.isEmpty() && !section.equals(prevSection))
                {
                    sections.add(new Section(prevSection,sTotal));
                    sTotal = 0;

                }
                prevSection = section;
                sTotal++;



            }
            sections.add(new Section(prevSection,sTotal));
            return sections;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public Summary mapper(ResultSet rs)
    {
        int total = 0;
        int sTotal = 0;
        String aisle = null;
        UUID id = null;
        List<Section> sections = new ArrayList();
        String prevSection = "";
        //one aisle per scan id
        try {
            while (rs.next()) {
                id = UUID.fromString(rs.getString("scan_id"));
                aisle = rs.getString("aisle");

                String section = rs.getString("section");
                if (!prevSection.isEmpty() && !section.equals(prevSection))
                {
                    sections.add(new Section(prevSection,sTotal));
                    sTotal = 0;

                }
                prevSection = section;
                sTotal++;
                total++;


            }
            sections.add(new Section(prevSection,sTotal));
            return new Summary(id, aisle,total, sections);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
