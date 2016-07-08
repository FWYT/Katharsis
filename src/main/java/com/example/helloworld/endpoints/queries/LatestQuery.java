package com.example.helloworld.endpoints.queries;

import com.example.helloworld.HelloWorldApplication;
import com.example.helloworld.endpoints.resource.Latest;
import com.example.helloworld.endpoints.resource.Summary;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.cs.ISO_8859_7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by vagrant on 7/6/16.
 */
public class LatestQuery {
    private Connection c;
    private Statement stmt;

    private static final Logger log = LoggerFactory.getLogger(LatestQuery.class);

    public LatestQuery()
    {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/customer_db", "postgres", "postgres");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Latest getLatest(UUID id)
    {
        try {
            stmt = c.createStatement();
            String sql = "SELECT s1.* " +
                    "FROM bnr.bnr_out_scan s1 LEFT JOIN bnr.bnr_out_scan  s2 " +
                    "ON (s1.work_address = s2.work_address and s1.retailer_id = s2.retailer_id and s1.store_id = s2.store_id AND s1.time_stamp < s2.time_stamp) " +
                    "WHERE s2.id IS NULL and s1.id = '" + id.toString() + "' " +
                    "order by s1.store_id";

            ResultSet rs = stmt.executeQuery(sql);

            List<Latest> L = mapper(rs);

            if (L.size() != 0) {
                return L.get(0);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Latest> getLatestAll()
    {
        try {
            stmt = c.createStatement();
            String sql = "SELECT s1.* " +
                    "FROM bnr.bnr_out_scan s1 LEFT JOIN bnr.bnr_out_scan  s2 " +
                    "ON (s1.work_address = s2.work_address and s1.retailer_id = s2.retailer_id and s1.store_id = s2.store_id AND s1.time_stamp < s2.time_stamp) " +
                    "WHERE s2.id IS NULL " +
                    "order by s1.store_id";

            ResultSet rs = stmt.executeQuery(sql);

            return mapper(rs);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private List<Latest> mapper(ResultSet rs)
    {
        List<Latest> latest = new ArrayList();
        try {
            UUID id;
            while (rs.next()) {
                id = UUID.fromString(rs.getString("id"));
                UUID missionId = UUID.fromString(rs.getString("mission_id"));
                String workAddress = rs.getString("work_address");
                String mission = rs.getString("mission");
                String storeId = rs.getString("store_id");
                UUID retailerId = UUID.fromString(rs.getString("retailer_id"));

                DateTime timeStamp = new DateTime(rs.getTimestamp("time_stamp"));


                String correlation_id = rs.getString("correlation_id");
                String image = rs.getString("image");

                List<Double> transform;
                if(rs.getArray("transform_array")!= null)
                    transform = Arrays.asList((Double[]) rs.getArray("transform_array").getArray());
                else
                    transform = null;
                String changerStamp = rs.getString("changer_stamp");

                SummaryQuery sq = new SummaryQuery();
                Summary s = sq.getGapCount(id);

                latest.add(new Latest(id,missionId, workAddress, mission, storeId, retailerId, timeStamp, correlation_id, image, transform,
                        changerStamp, s));
            }
        } catch (Exception e)
        {
            log.error("SQL exception");
        }

        return latest;
    }
}
