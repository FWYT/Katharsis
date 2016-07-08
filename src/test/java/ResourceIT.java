import com.example.helloworld.HelloWorldApplication;
import com.example.helloworld.HelloWorldConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vagrant on 7/6/16.
 */
public class ResourceIT {

    @Test
    public void testGet()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/latest/?include[latest]=summary");

        Response response = target.request().get();

        System.out.println(response.readEntity(String.class));
        System.out.println(response.getStatus());
        assert(response.getStatus() == 200);
    }

    @Test
    public void testGapCount()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/summary/243e99be-ca8d-4462-9476-a26426c88a0a");

        Response response = target.request().get();

        System.out.println(response.readEntity(String.class));
        System.out.println(response.getStatus());
        assert(response.getStatus() == 200);
    }
}
