package com.example.helloworld.endpoints.queries;

import com.example.helloworld.endpoints.resource.Summary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            String sql = "select * from bnr.bnr_out_gaps where scan_id = '"+id+"' order by aisle asc";

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
        int count = 0;
        String aisle = null;
        String lastAisle = "";
        UUID id = null;
        try {
            while (rs.next()) {
                id = UUID.fromString(rs.getString("scan_id"));
                aisle = rs.getString("aisle");

                /*if (!lastAisle.equals("") && !aisle.equals(lastAisle))
                {
                    count = 0;
                }
                */
                count++;


            }
            return new Summary(id, aisle,count);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
