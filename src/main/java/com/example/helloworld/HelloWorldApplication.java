package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.katharsis.locator.SampleJsonServiceLocator;
import io.katharsis.queryParams.DefaultQueryParamsParser;
import io.katharsis.queryParams.QueryParamsBuilder;
import io.katharsis.rs.KatharsisFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.katharsis.rs.KatharsisProperties.RESOURCE_DEFAULT_DOMAIN;
import static io.katharsis.rs.KatharsisProperties.RESOURCE_SEARCH_PACKAGE;


/**
 * Created by vagrant on 7/6/16.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldApplication.class);

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {

        environment.jersey()
                .property(RESOURCE_DEFAULT_DOMAIN, configuration.katharsis.host);
        environment.jersey()
                .property(RESOURCE_SEARCH_PACKAGE, configuration.katharsis.searchPackage);

        KatharsisFeature katharsisFeature = new KatharsisFeature(environment.getObjectMapper(),
                new QueryParamsBuilder(new DefaultQueryParamsParser()),
                new SampleJsonServiceLocator());
        environment.jersey().register(katharsisFeature);


        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck();
        environment.healthChecks().register("template", healthCheck);

    }
}

