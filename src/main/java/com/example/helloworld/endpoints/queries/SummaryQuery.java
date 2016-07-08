package com.example.helloworld.endpoints.queries;

import com.example.helloworld.HelloWorldApplication;
import com.example.helloworld.endpoints.resource.Section;
import com.example.helloworld.endpoints.resource.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */
public class SummaryQuery {

    private Connection c;
    private Statement stmt;

    private static final Logger log = LoggerFactory.getLogger(SummaryQuery.class);

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
            //String sql = "select * from bnr.bnr_out_gaps where scan_id = '"+id.toString()+"' order by section asc";

            String sql = "select scan_id, aisle, section, count(section) as section_count, sum(count(section)) over () as total " +
                    "from bnr.bnr_out_gaps " +
                    "where scan_id = '"+id.toString()+"' " +
                    "group by section,aisle, scan_id order by (section::integer)";
            ResultSet rs = stmt.executeQuery(sql);

            Summary L = mapper(rs);

            return L;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public Summary mapper(ResultSet rs)
    {
        int total = -1;
        String aisle = null;
        UUID id = null;
        List<Section> sections = new ArrayList();
        //one aisle per scan id

        try {
            while (rs.next()) {
                if (aisle == null && total == -1 && id ==null)
                {
                    aisle = rs.getString("aisle");
                    total = rs.getInt("total");
                    id = UUID.fromString(rs.getString("scan_id"));
                }
                sections.add(new Section(rs.getString("section"), rs.getInt("section_count")));


            }
            return new Summary(id, aisle,total, sections);

        } catch (SQLException e)
        {
            log.error("SQL exception");
        }
        return null;
    }
}
